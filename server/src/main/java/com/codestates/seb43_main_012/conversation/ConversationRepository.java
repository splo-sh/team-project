package com.codestates.seb43_main_012.conversation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ConversationRepository  extends JpaRepository<Conversation, Long> {
    List<Conversation> findAllBySaved(boolean isSaved);

}
