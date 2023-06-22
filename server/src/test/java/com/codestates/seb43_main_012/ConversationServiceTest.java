package com.codestates.seb43_main_012;

import com.codestates.seb43_main_012.conversation.Conversation;
import com.codestates.seb43_main_012.conversation.ConversationDto;
import com.codestates.seb43_main_012.conversation.ConversationRepository;
import com.codestates.seb43_main_012.conversation.ConversationService;
import com.codestates.seb43_main_012.member.entity.MemberEntity;
import com.codestates.seb43_main_012.member.repository.MemberRepository;
import com.codestates.seb43_main_012.qna.QnA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles({"local2"})
public class ConversationServiceTest {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ConversationService conversationService;

//    @BeforeEach
//    public void setup() throws InterruptedException {
//        List<Conversation> conversationList = new ArrayList<>();
//        memberRepository.save(new MemberEntity(1L));
//
//        for(int i=0; i<10; i++)
//        {
//            Conversation conversation = new Conversation(Integer.toUnsignedLong(i+1));
//            conversation.setMember(new MemberEntity(1L));
//            conversation.addQnA(new QnA(Integer.toString(i),"qna"));
//            conversationList.add(conversation);
//        }
//
//        conversationRepository.saveAll(conversationList);
//        for(int i=0; i<10; i++) {
//            System.out.println("------------\n");
//        }
//        Thread.sleep(1000);
//    }

    @Test
    public void n플러스1문제() throws Exception
    {
        long beforeTime = System.currentTimeMillis();

        List<ConversationDto.ResponseForAll> response = conversationService.findConversationList("desc",null,1L);

        long afterTime = System.currentTimeMillis();
        long diffTime = afterTime - beforeTime;
        System.out.println("실행시간(ms) : "+ diffTime);

        assertEquals(10000, response.size());
    }
}
