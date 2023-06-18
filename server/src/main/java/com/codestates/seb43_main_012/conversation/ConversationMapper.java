package com.codestates.seb43_main_012.conversation;

import com.codestates.seb43_main_012.category.Category;
import com.codestates.seb43_main_012.category.ConversationCategory;
import com.codestates.seb43_main_012.category.ConversationCategoryDto;
import com.codestates.seb43_main_012.member.dto.MemberDto;
import com.codestates.seb43_main_012.member.entity.MemberEntity;
import com.codestates.seb43_main_012.qna.QnADto;
import com.codestates.seb43_main_012.qna.QnAMapper;
import com.codestates.seb43_main_012.tag.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ConversationMapper {

    private final QnAMapper qnaMapper;

    public ConversationDto.Response responseForGetOneConversation(Conversation conversation, List<Category> categories)
    {
        List<QnADto.Response> qnaResponseList = new ArrayList<>();
        conversation.getQnaList().forEach(qna ->
                    qnaResponseList.add(qnaMapper.qnaToQnAResponseDto(qna))
        );

        return new ConversationDto.Response(
                conversation.getId(),
                new MemberDto.ResponseForConversation(conversation.getMember().getId(),conversation.getMember().getUsername()),
                conversation.getTitle(),
                qnaResponseList,
                conversationCategoriesToCategoryResponseDtos(conversation.getCategories()),
                categoriesToCategoryResponseDtos(categories),
                conversationTagsToConversationTagDtos(conversation.getTags()),
                conversation.getSaved(),
                conversation.getPinned(),
                conversation.getPublished(),
                conversation.getViewCount(),
                conversation.getActivityLevel()
        );
    }

    private List<ConversationTagDto> conversationTagsToConversationTagDtos(List<ConversationTag> conversationTags)
    {
        List<ConversationTagDto> conversationTagDtos = new ArrayList<>();
        conversationTags.forEach(conversationTag ->
                conversationTagDtos.add(new ConversationTagDto(
                        conversationTag.getTag().getId(),
                        conversationTag.getTag().getName())
                )
        );
        return conversationTagDtos;
    }

    public ConversationDto.ResponseForPatch conversationToPatchResponseDto(Conversation conversation)
    {
        return new ConversationDto.ResponseForPatch(
                conversation.getId(),
                conversation.getTitle(),
                conversation.getModifiedAt(),
                conversation.getPinned()
        );
    }



    private ConversationDto.ResponseForAll conversationToConversationResponseDto(Conversation conv)
    {
        MemberEntity member = conv.getMember();

        return new ConversationDto.ResponseForAll(
                        conv.getId(),
                        memberToMemberResponseDto(member),
                        conv.getTitle(),
                        conv.getAnswerSummary(),
                        conv.getModifiedAt(),
                        conversationCategoriesToCategoryResponseDtos(conv.getCategories()),
                        conversationTagsToTagResponseDtos(conv.getTags()),
                        conv.getSaved(),
                        conv.getPinned(),
                        conv.getPublished(),
                        conv.getViewCount(),
                        conv.getActivityLevel()
                );
    }

    private MemberDto.ResponseForConversation memberToMemberResponseDto(MemberEntity member)
    {
        return new MemberDto.ResponseForConversation(member.getId(), member.getUsername());
    }

    private List<TagDto.Response> conversationTagsToTagResponseDtos(List<ConversationTag> conversationTags)
    {
        List<TagDto.Response> responses = new ArrayList<>();

        conversationTags.forEach(convTag ->
                responses.add(conversationTagToTagResponseDto(convTag))
        );
        return responses;
    }

    private TagDto.Response conversationTagToTagResponseDto(ConversationTag conversationTag)
    {
        Tag tag = conversationTag.getTag();
        return new TagDto.Response(tag.getId(), tag.getName());
    }

    public List<ConversationDto.ResponseForAll> conversationsToConversationResponseDtos(List<Conversation> conversations)
    {
        List<ConversationDto.ResponseForAll> responses = new ArrayList<>();

        conversations
                .forEach(conv ->
                    {
                        ConversationDto.ResponseForAll response = conversationToConversationResponseDto(conv);
                        responses.add(response);
                    }
        );

        return responses;
    }

    public Map<String, String> simpleMessageResponse(String message)
    {
        Map<String,String> response = new HashMap<>();
        response.put("message",message);

        return response;
    }
    public Map<String, Object> postBookmarkResponse(long bookmarkId, String bookmarkName)
    {
        Map<String,Object> response = new HashMap<>();

        response.put("bookmarkId", bookmarkId);
        response.put("bookmarkName", bookmarkName);

        return response;
    }
    public Map<String, Object> postTagResponse(long tagId, String tagName)
    {
        Map<String,Object> response = new HashMap<>();

        response.put("tagId", tagId);
        response.put("tagName", tagName);

        return response;
    }


    private List<ConversationCategoryDto> conversationCategoriesToCategoryResponseDtos(List<ConversationCategory> conversationCategories)
    {
        List<ConversationCategoryDto> responses = new ArrayList<>();
        conversationCategories.forEach(category -> responses.add(conversationCategoryToCategoryResponseDto(category)));

        return responses;
    }

    private ConversationCategoryDto conversationCategoryToCategoryResponseDto(ConversationCategory conversationCategory)
    {
        return new ConversationCategoryDto(
                conversationCategory.getCategory().getId(),
                conversationCategory.getCategory().getName()
        );
    }

    private List<ConversationCategoryDto> categoriesToCategoryResponseDtos(List<Category> categories)
    {
        List<ConversationCategoryDto> responses = new ArrayList<>();
        categories.forEach(category -> responses.add(categoryToCategoryResponseDto(category)));

        return responses;
    }

    private ConversationCategoryDto categoryToCategoryResponseDto(Category category)
    {
        ConversationCategoryDto response = new ConversationCategoryDto(
                category.getId(),
                category.getName()
        );

        return response;
    }
}
