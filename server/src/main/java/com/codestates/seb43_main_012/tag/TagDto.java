package com.codestates.seb43_main_012.tag;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class TagDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotNull
        private String tagName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long conversationId;
        private Long tagId;


        @NotNull
        private String tagName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long tagId;
        private String tagName;

    }
}
