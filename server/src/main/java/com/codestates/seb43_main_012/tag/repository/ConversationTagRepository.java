package com.codestates.seb43_main_012.tag.repository;

import com.codestates.seb43_main_012.tag.entitiy.ConversationTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationTagRepository extends JpaRepository<ConversationTag, Long> {
    //void deleteAllByConversationId(long conversationId);

//    @Query("select ct from ConversationTag ct where ct.conversationId in ?1")
//    List<ConversationTag> findAllByConversationIdIn(List<Long> conversationIds);

    void deleteByConversationIdAndTagId(long conversationId, long tagId);
    List<ConversationTag> findAllByConversationId(long conversationId);
    List<ConversationTag> findAllByConversationIdIn(List<Long> conversationIDs);
    Optional<ConversationTag> findByConversationIdAndTagName(long conversationId, String tagName);
    List<ConversationTag> findAllByTagName(String tagName);
}
