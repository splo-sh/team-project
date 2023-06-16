package com.codestates.seb43_main_012.collection;

import com.codestates.seb43_main_012.bookmark.BookmarkDto;
import com.codestates.seb43_main_012.conversation.ConversationDto;
import com.codestates.seb43_main_012.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CollectionPageDto {
    private List<BookmarkDto.Response> bookmarks;
    private List<TagDto.Response> tags;
    private List<ConversationDto.ResponseForAll> conversations;
}
