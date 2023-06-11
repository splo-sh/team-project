package com.codestates.seb43_main_012.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationCategoryRepository extends JpaRepository<ConversationCategory,Long> {
    void deleteByConversationIdAndCategoryId(long conversationId, long bookmarkId);
    List<ConversationCategory> findAllByConversationId(long conversationId);
    Optional<ConversationCategory> findByConversationIdAndCategoryName(long conversationId, String bookmarkName);
    List<ConversationCategory> findAllByCategoryName(String categoryName);
    List<ConversationCategory> findAllByCategoryId(long categoryId);
}
