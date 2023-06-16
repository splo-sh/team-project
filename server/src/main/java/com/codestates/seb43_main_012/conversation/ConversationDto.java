package com.codestates.seb43_main_012.conversation;

import com.codestates.seb43_main_012.category.ConversationCategoryDto;
import com.codestates.seb43_main_012.member.dto.MemberDto;
import com.codestates.seb43_main_012.qna.QnADto;
import com.codestates.seb43_main_012.tag.ConversationTag;
import com.codestates.seb43_main_012.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


public class ConversationDto {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Post
    {
        private Conversation conversation;
        private String question;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Patch
    {
        private String title;
        private Boolean pinned;
    }


    @AllArgsConstructor
    @Getter
    public static class ResponseForPatch
    {
        private long conversationId;
        private String title;
        private String modifiedAt;
        private boolean pinned;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response
    {
        @Setter
        private long conversationId;
        private MemberDto.ResponseForConversation member;
        private String title;
        private List<QnADto.Response> qnaList;
        private List<ConversationCategoryDto> categoriesThisConversation;
        private List<ConversationCategoryDto> otherCategories;
        private List<ConversationTag> tags;
        private Boolean saved;
        private Boolean pinned;
        private Boolean published;
        private int viewCount;
        private int activityLevel;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class ResponseForAll
    {
        @Setter
        private long conversationId;
        private MemberDto.ResponseForConversation member;
        private String title;
        private String answerSummary;
        private String modifiedAt;
        private List<ConversationCategoryDto> bookmarks;
        private List<Tag> tags;
        private Boolean saved;
        private Boolean pinned;
        private Boolean published;
        private int viewCount;
        private int activityLevel;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class ModifiedAt
    {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;
        private int second;
        private int nano;
    }
}
