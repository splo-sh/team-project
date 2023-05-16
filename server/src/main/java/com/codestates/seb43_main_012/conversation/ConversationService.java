package com.codestates.seb43_main_012.conversation;

import com.codestates.seb43_main_012.bookmark.*;
import com.codestates.seb43_main_012.category.Category;
import com.codestates.seb43_main_012.category.CategoryRepository;
import com.codestates.seb43_main_012.category.ConversationCategory;
import com.codestates.seb43_main_012.category.ConversationCategoryRepository;
import com.codestates.seb43_main_012.member.repository.MemberRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    private final Long MEMBER_ID = 1L;

    private final ConversationRepository conversationRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final CategoryRepository categoryRepository;
    private final ConversationCategoryRepository conversationCategoryRepository;
    public ConversationService(ConversationRepository conversationRepository,
                               MemberRepository memberRepository,
                               BookmarkRepository bookmarkRepository,
                               CategoryRepository categoryRepository,
                               ConversationCategoryRepository conversationCategoryRepository)
    {
        this.conversationRepository = conversationRepository;
        this.memberRepository = memberRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.categoryRepository = categoryRepository;
        this.conversationCategoryRepository = conversationCategoryRepository;
    }

    public Conversation saveConversation(Conversation conversation)
    {
        return conversationRepository.save(conversation);
    }

    public Conversation createConversation(long memberId)
    {
        Conversation conversation = new Conversation();
        conversation.addMember(memberRepository.findById(memberId).orElse(null));
        //conversation.setMember(new MemberEntity(1L,"a","a","a"));
        return conversationRepository.save(conversation);
    }

    public Conversation updateConversation(long conversationId)
    {
        Optional<Conversation> optional = conversationRepository.findById(conversationId);
        Conversation findConversation = optional.orElseThrow(()->new RuntimeException());

        findConversation.setModifiedAt(LocalDateTime.now());
        return conversationRepository.save(findConversation);
    }

    public Conversation findConversation(long conversationId)
    {
        Optional<Conversation> optional = conversationRepository.findById(conversationId);
        Conversation conversation = optional.orElseThrow(()->new RuntimeException());
        return conversation;
    }

    public List<Conversation> findConversations(String sort)
    {
       if(sort.equals("desc"))
            return conversationRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedAt"));
        else
            return conversationRepository.findAll(Sort.by(Sort.Direction.ASC, "modifiedAt"));
    }

    public List<Bookmark> findBookmarkedConversations(String bookmarkName)
    {
        return bookmarkRepository.findAllByBookmarkName(MEMBER_ID,bookmarkName);
    }

    public Conversation createBookmark(long conversationId, BookmarkDto.Post dto)
    {
        // 북마크 생성
        // 카테고리 생성

        Conversation conversation = findConversation(conversationId);
        conversation.setSaved(true);

        Bookmark bookmark = new Bookmark();
        bookmark.setMemberId(MEMBER_ID);
        bookmark.addConversation(conversation);
        bookmarkRepository.save(bookmark);

        conversationCategoryRepository.deleteAllByConversationId(conversationId);

        List<String> categories = dto.getBookmarks();
        categories.stream().forEach(category -> {
            //중복 조회
            Optional<Category> optional = categoryRepository.findByName(category);
            if(optional.isEmpty())
            {
                Category savedCategory = categoryRepository.save(new Category(MEMBER_ID, category));
                ConversationCategory conversationCategory = new ConversationCategory(conversationId,savedCategory.getId());
                conversationCategoryRepository.save(conversationCategory);
            }
            else
            {
                Category findCategory = optional.orElse(null);
                ConversationCategory conversationCategory = new ConversationCategory(conversationId,findCategory.getId());
                conversationCategoryRepository.save(conversationCategory);
            }
        });
        conversation.setBookmarks(listToString(categories));

        //Optional.ofNullable(collection.getPinned()).ifPresent(pin -> conversation.setPinned(pin));
        //Optional.ofNullable(collection.getPublished()).ifPresent(publish -> conversation.setPublished(publish));
        //Optional.ofNullable(collection.getTitle()).ifPresent(title -> conversation.setTitle(title));

        return conversationRepository.save(conversation);
    }

    public Conversation viewCountUp(long conversationId)
    {
        Conversation conversation = findConversation(conversationId);
        conversation.setViewCount(conversation.getViewCount()+1);
        return conversationRepository.save(conversation);
    }

    public List<Conversation> getSavedConversation(boolean isSaved)
    {
        return conversationRepository.findAllBySaved(isSaved);
    }

    private String listToString(List list)
    {
        if(list.isEmpty()) return null;

        String str = "[\"";

        str += list.get(0);
        for(int i = 1; i<list.size();i++)
        {
            str += "\",\"";
            str += list.get(i);
        }
        str += "\"]";

        return str;
    }
}
