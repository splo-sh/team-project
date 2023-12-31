package com.codestates.seb43_main_012.collection;

import com.codestates.seb43_main_012.category.ConversationCategoryDto;
import com.codestates.seb43_main_012.tag.ConversationTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class CollectionDto {

    @NoArgsConstructor
    @Getter
    public static class Post
    {
        private List<String> bookmarks;
        private List<String> tags;
        private Boolean pinned;
        private String title;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response
    {
        @Setter
        private long conversationId;
        private List<ConversationCategoryDto> bookmarks;
        private List<ConversationTag> tags;
        private Boolean pinned;
        private String title;
    }


}
