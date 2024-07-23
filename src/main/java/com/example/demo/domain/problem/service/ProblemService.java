package com.example.demo.domain.problem.service;

import com.example.demo.domain.problem.dto.*;
import com.example.demo.domain.problem.entity.*;
import com.example.demo.domain.problem.exception.InvalidConceptTagException;
import com.example.demo.domain.problem.exception.ProblemException;
import com.example.demo.domain.problem.repository.ConceptTagRepository;
import com.example.demo.domain.problem.repository.ProblemConceptTagRepository;
import com.example.demo.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final ConceptTagRepository conceptTagRepository;
    private final ProblemConceptTagRepository problemConceptTagRepository;

    /**
     * 문제를 만들 때 순서
     * 1. Official Solution을 저장
     * 2. ProblemContentTag를 저장
     * 3. Problem을 저장 -> (1), (2)를 연관시켜서 저장
     *
     * 조건
     * 1. tag가 이미 존재하는 태그여야한다.
     * 2. 부족하면 나중에 추가로 tag를 달 수 있도록 해야함.
     *
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProblemCreationResponse createProblem(ProblemCreationRequest request){
        OfficialSolution officialSolution = OfficialSolution.toEntity(request.getOfficialSolution());
        Set<SolutionContentEntity> solutionContentEntitySet = SolutionContentEntity.toEntity(officialSolution, request.getOfficialSolution().getImgSolutions());
        officialSolution.setSolutionContents(solutionContentEntitySet);

        Problem problem = Problem.toEntity(request, officialSolution);
        Problem savedProblem = problemRepository.save(problem);
        createProblemConceptTag(request.getConceptTags(), problem);
        return new ProblemCreationResponse(savedProblem.getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ConceptTag> findConceptTag(List<Long> ids){
        List<ConceptTag> conceptTags = conceptTagRepository.findByIdIn(ids);
        if(ids.size() != conceptTags.size()){
            throw new InvalidConceptTagException("존재하지 않는 개념 태그가 있습니다. 태그를 추가해서 사용해주세요.");
        }
        return conceptTags;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createProblemConceptTag(List<Long> ids, Problem problem){
        List<ProblemConceptTag> problemConceptTags = findConceptTag(ids).stream()
                .map(tag -> ProblemConceptTag
                        .builder()
                        .problem(problem)
                        .conceptTag(tag).build())
                .toList();
        try{
            problemConceptTagRepository.saveAll(problemConceptTags);
        } catch (RuntimeException e){
            throw new ProblemException(e.getMessage(), e);
        }


    }

}
