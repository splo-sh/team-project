package com.codestates.seb43_main_012.category;

import com.codestates.seb43_main_012.conversation.Conversation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ConversationCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONVERSATION_ID")
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;


    public ConversationCategory(Conversation conversation, Category category)
    {
        this.conversation = conversation;
        this.category = category;
    }
}
