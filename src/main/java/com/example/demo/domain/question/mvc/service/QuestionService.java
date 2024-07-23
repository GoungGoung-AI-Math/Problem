package com.example.demo.domain.question.mvc.service;

import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.exception.ProblemException;
import com.example.demo.domain.problem.repository.ProblemRepository;
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
    private final ProblemRepository problemRepository;
    public Long createQuestion(QuestionCreateRequest questionCreateRequest){
        Problem problem = problemRepository.findById(questionCreateRequest.getProblemId())
                .orElseThrow(()-> new ProblemException("존재하지 않는 문제입니다."));
        try{
            Question question = Question.toEntity(questionCreateRequest, problem);
            return questionRepository.save(question).getId();
        } catch (RuntimeException e){
            log.error(e.getMessage(), e);
            throw new QueryException(e.getMessage(), e);
        }
    }
}
