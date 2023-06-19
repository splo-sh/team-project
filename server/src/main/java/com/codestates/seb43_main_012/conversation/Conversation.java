package com.codestates.seb43_main_012.conversation;

import com.codestates.seb43_main_012.category.ConversationCategory;
import com.codestates.seb43_main_012.member.entity.MemberEntity;
import com.codestates.seb43_main_012.qna.QnA;
import com.codestates.seb43_main_012.tag.ConversationTag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;
    private String title;
    @Column(length=10000)
    private String answerSummary;
    private String createdAt = String.valueOf(LocalDateTime.now());
    private String modifiedAt = String.valueOf(LocalDateTime.now());
    @OneToMany(mappedBy = "conversation", cascade = {CascadeType.MERGE})
    private List<QnA> qnaList = new ArrayList<>();
    private Boolean saved = false;
    private Boolean pinned = false;
    private Boolean published = false;

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.PERSIST)
    private List<ConversationCategory> categories = new ArrayList<>();
    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.PERSIST)
    private List<ConversationTag> tags = new ArrayList<>();
    private int viewCount;
    private int activityLevel;
    @JsonIgnore
    private boolean deleteStatus = false;
    @JsonIgnore
    private boolean bookmarked = false;
    @JsonIgnore
    private boolean tagged = false;

    public void addMember(MemberEntity member)
    {
        this.member = member;
    }
    public void addQnA(QnA qna)
    {
        this.qnaList.add(qna);
        qna.setConversation(this);
    }
    public void addTag(ConversationTag tag)
    {
        this.tags.add(tag);
    }

    public Conversation(long id)
    {
        this.id = id;
    }
}
