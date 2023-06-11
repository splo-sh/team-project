package com.codestates.seb43_main_012.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByMemberIdAndConversationId(long memberId, long conversationId);

    void deleteByMemberIdAndConversationId(long memberId, long conversationId);
}
