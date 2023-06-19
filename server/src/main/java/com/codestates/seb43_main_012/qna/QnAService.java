package com.codestates.seb43_main_012.qna;

import com.codestates.seb43_main_012.conversation.Conversation;
import com.codestates.seb43_main_012.conversation.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QnAService {

    @Value("${apikey}")
    private String API_KEY;
    private final int MAX_TOKENS = 500;
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private final QnARepository qnaRepository;
    private final ConversationRepository conversationRepository;


    public QnA saveQnA(QnA qna)
    {
        return qnaRepository.save(qna);
    }

    public List<QnA> findQnAs(long conversationId)
    {
        return qnaRepository.findAllByConversationId(conversationId);
    }

    public List<Map<String, String>> buildMessage(long conversationId)
    {
        List<QnA> qnaList = findQnAs(conversationId);

        List<Map<String, String >> messages = new ArrayList<>();
        qnaList.stream().forEach(qna ->
                {
                    Map<String, String> userMessage = new HashMap<>();
                    userMessage.put("role","user");
                    userMessage.put("content", qna.getQuestion());
                    messages.add(userMessage);
                    Map<String, String> assistantMessage = new HashMap<>();
                    assistantMessage.put("role","assistant");
                    assistantMessage.put("content", qna.getAnswer());
                    messages.add(assistantMessage);
                }
        );

        return messages;
    }

    public List<Long> findConversationIDs(String query, long memberId)
    {
        List<QnA> qnaList = qnaRepository.findAllByQuestionContainingOrAnswerContaining(query, query);

        List<Long> IDs = new ArrayList<>();

        qnaList.stream().forEach(qna -> {
                if(qna.getConversation().getMember().getId() == memberId)
                    IDs.add(qna.getConversation().getId());
        });

        return IDs;
    }

    @Transactional
    public QnA requestAnswerPreprocessing(QnADto.Post dto)
    {
        Conversation conversation = conversationRepository.findById(dto.getConversationId()).get();
        return requestAnswer(conversation, dto.getQuestion());
    }

    public QnA requestAnswer(Conversation conversation, String question)
    {
        // set header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        // 질문
        List<Map<String, String>> messages = buildMessage(conversation.getId());
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", question);
        messages.add(message);

        // request 빌드
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);
        requestBody.put("max_tokens", MAX_TOKENS);

        // api 호출, 답변 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        //RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        //ResponseEntity<Map> response = restTemplate.postForEntity(API_ENDPOINT, requestEntity, Map.class);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(API_ENDPOINT, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {});

        // 질문-답변 인스턴스 생성
        List<Object> a = (List<Object>) response.getBody().get("choices");
        Map<String, Object> b = (Map<String, Object>) a.get(0);
        Map<String, Object> c = (Map<String, Object>) b.get("message");
        String answer = (String) c.get("content");
        QnA qna = new QnA(question,answer);


        if(conversation.getTitle() == null)
        {
            conversation.setTitle(question);
            conversation.setAnswerSummary(answer);
        }

        conversation.setActivityLevel(conversation.getActivityLevel()+1);
        conversation.setModifiedAt(String.valueOf(LocalDateTime.now()));
        conversation.addQnA(qna);

        System.out.println("requestAnswer 메서드 종료");
        return qna;
    }
}
