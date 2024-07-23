package com.example.demo.domain.question.mvc.service;

import com.example.demo.domain.question.mvc.dto.QuestionCreateRequest;
import com.example.demo.domain.question.mvc.entity.Question;
import com.example.demo.domain.question.mvc.repository.QuestionRepository;
import com.querydsl.core.QueryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Long createQuestion(QuestionCreateRequest questionCreateRequest){
        try{
            Question question = Question.toEntity(questionCreateRequest);
            return questionRepository.save(question).getId();
        } catch (RuntimeException e){
            log.error(e.getMessage(), e);
            throw new QueryException(e.getMessage(), e);
        }
    }
}
