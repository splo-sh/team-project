package com.codestates.seb43_main_012.qna;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {

    List<QnA> findAllByConversationId(long conversationId);

    //@Query("select q from QnA q join fetch q.conversation c join fetch c.categories where q.question like %?1% or q.answer like %?2%")
    @EntityGraph(attributePaths = {"conversation","conversation.categories"})
    List<QnA> findAllByQuestionContainingOrAnswerContaining(String keyword1, String keyword2);
}
