package com.codestates.seb43_main_012.bookmark;

import com.codestates.seb43_main_012.conversation.Conversation;
import com.codestates.seb43_main_012.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookmarkId;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private MemberEntity member;
    @Setter
    private long memberId;

    @ManyToOne
    @JoinColumn(name = "CONVERSATION_ID")
    private Conversation conversation;

    public void addConversation(Conversation conversation)
    {
        this.conversation = conversation;
    }
    //public void addMember(MemberEntity member) {this.member = member;}
}
