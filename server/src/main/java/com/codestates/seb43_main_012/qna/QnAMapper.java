package com.codestates.seb43_main_012.qna;

import org.springframework.stereotype.Component;

@Component
public class QnAMapper {
    public QnADto.Response qnaToQnAResponseDto(QnA qna)
    {
        return new QnADto.Response(
                qna.getId(),
                qna.getQuestion(),
                qna.getAnswer(),
                qna.isBookmarkStatus()
        );
    }
}
