package com.codestates.seb43_main_012.conversation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository  extends JpaRepository<Conversation, Long> {
    List<Conversation> findAllByMemberIdAndDeleteStatus(long memberId, boolean deleteStatus);
    List<Conversation> findAllByMemberIdAndSavedAndDeleteStatus(long memberId, boolean isSaved, boolean deleteStatus);
    List<Conversation> findAllByMemberIdAndSavedAndDeleteStatus(long memberId, boolean isSaved, boolean deleteStatus, Sort sort);
    //@EntityGraph(attributePaths = {"categories","tags"})
    List<Conversation> findAllByDeleteStatusAndIdIn(boolean deleteStatus, List<Long> IDs, Sort sort);
    Page<Conversation> findAllByDeleteStatusAndIdIn(boolean deleteStatus, List<Long> IDs, Pageable pageable);


}
