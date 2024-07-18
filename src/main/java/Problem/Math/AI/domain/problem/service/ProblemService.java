package Problem.Math.AI.domain.problem.service;

import Problem.Math.AI.domain.problem.dto.*;
import Problem.Math.AI.domain.problem.entity.ConceptTag;
import Problem.Math.AI.domain.problem.entity.OfficialSolution;
import Problem.Math.AI.domain.problem.entity.Problem;
import Problem.Math.AI.domain.problem.entity.ProblemConceptTag;
import Problem.Math.AI.domain.problem.exception.InvalidConceptTagException;
import Problem.Math.AI.domain.problem.repository.ConceptTagRepository;
import Problem.Math.AI.domain.problem.repository.OfficialSolutionRepository;
import Problem.Math.AI.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final ConceptTagRepository conceptTagRepository;
    private final OfficialSolutionRepository officialSolutionRepository;

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
        Set<ProblemConceptTag> conceptTags = createProblemConceptTag(request.getConceptTags());
        Problem problem = Problem.toEntity(request, conceptTags, officialSolution);
        Problem savedProblem = problemRepository.save(problem);
        return new ProblemCreationResponse(savedProblem.getUserId());
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
    public Set<ProblemConceptTag> createProblemConceptTag(List<Long> ids){
        return findConceptTag(ids).stream()
                .map(tag -> ProblemConceptTag
                        .builder().conceptTag(tag).build())
                .collect(Collectors.toSet());
    }

}
