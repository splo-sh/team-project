package com.codestates.seb43_main_012.qna;

import com.codestates.seb43_main_012.exception.BusinessLogicException;
import com.codestates.seb43_main_012.exception.ExceptionCode;
import com.codestates.seb43_main_012.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/openai/question")
@RequiredArgsConstructor
public class QnAController {

    private final QnAService qnaService;
    private final QnAMapper qnaMapper;

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QnADto.Post dto,
                                       @AuthenticationPrincipal MemberEntity member)
    {
        long conversationId = dto.getConversationId();
        if(conversationId == 0) throw new BusinessLogicException(ExceptionCode.CONV_NOT_FOUND);

        QnA qna = qnaService.requestAnswerPreprocessing(dto);
        QnA savedQnA = qnaService.saveQnA(qna);

        return new ResponseEntity<>(qnaMapper.qnaToQnAResponseDto(savedQnA), HttpStatus.OK);
    }
}
