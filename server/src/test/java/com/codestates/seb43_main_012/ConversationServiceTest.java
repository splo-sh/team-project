package com.codestates.seb43_main_012;

import com.codestates.seb43_main_012.conversation.Conversation;
import com.codestates.seb43_main_012.conversation.ConversationRepository;
import com.codestates.seb43_main_012.conversation.ConversationService;
import com.codestates.seb43_main_012.member.entity.MemberEntity;
import com.codestates.seb43_main_012.member.repository.MemberRepository;
import com.codestates.seb43_main_012.qna.QnA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles({"local"})
public class ConversationServiceTest {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup() throws InterruptedException {
        List<Conversation> conversationList = new ArrayList<>();
        memberRepository.save(new MemberEntity(1L));

        for(int i=0; i<10; i++)
        {
            Conversation conversation = new Conversation(Integer.toUnsignedLong(i+1));
            conversation.setMember(new MemberEntity(1L));
            conversation.addQnA(new QnA(Integer.toString(i),"qna"));
            conversationList.add(conversation);
        }

        conversationRepository.saveAll(conversationList);
        for(int i=0; i<10; i++) {
            System.out.println("------------\n");
        }
        Thread.sleep(1000);
    }

    @Test
    public void n플러스1문제() throws Exception
    {
        List<String> answers =
                conversationRepository.findAllByMemberIdAndDeleteStatus(1L, false).stream()
                        .map(conversation -> conversation.getQnaList().get(0).getQuestion())
                        .collect(Collectors.toList());

        //answers.stream().forEach(System.out::println);

        assertEquals(10, answers.size());
    }
}
