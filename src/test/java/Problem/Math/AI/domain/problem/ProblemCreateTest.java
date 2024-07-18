package Problem.Math.AI.domain.problem;

import Problem.Math.AI.common.dto.ContentRequest;
import Problem.Math.AI.domain.problem.dto.OfficialSolutionCreationRequest;
import Problem.Math.AI.domain.problem.dto.ProblemCreationRequest;
import Problem.Math.AI.domain.problem.dto.ProblemCreationResponse;
import Problem.Math.AI.domain.problem.entity.ConceptTag;
import Problem.Math.AI.domain.problem.entity.OfficialSolution;
import Problem.Math.AI.domain.problem.entity.Problem;
import Problem.Math.AI.domain.problem.entity.ProblemConceptTag;
import Problem.Math.AI.domain.problem.repository.ConceptTagRepository;
import Problem.Math.AI.domain.problem.repository.OfficialSolutionRepository;
import Problem.Math.AI.domain.problem.repository.ProblemRepository;
import Problem.Math.AI.domain.problem.service.ProblemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
class ProblemCreateTest {

    @Autowired
    ProblemService problemService;

    @MockBean
    ProblemRepository problemRepository;

    @MockBean
    ConceptTagRepository conceptTagRepository;

    @MockBean
    OfficialSolutionRepository officialSolutionRepository;

    ProblemCreationRequest request = ProblemCreationRequest.builder()
            .userId(1L)
            .name("20xx년 x월 전국연합학력평가 19번")
            .imgUrl("https://dsfsfsffds")
            .difficulty(3.67)
            .answer(4)
            .conceptTags(List.of(1L, 2L))
            .officialSolution(OfficialSolutionCreationRequest
                    .builder()
                    .source("인천 교육청")
                    .textSolution("이거를 저렇게 해서 저거를 이렇게 하면~~")
                    .imgSolutions(List.of(new ContentRequest("https://asb"),
                            new ContentRequest("https://bd"),
                            new ContentRequest("https://as")))
                    .build())
            .build();

    @Test
    public void givenRequest_whenCreateProblem_thenReturnResponse(){
        when(conceptTagRepository.findByIdIn(any(List.class))).thenReturn(List.of(new ConceptTag(1L, "미적분"), new ConceptTag(2L, "확률")));
        when(problemRepository.save(any(Problem.class))).thenAnswer(invocation -> Problem.builder().id(1L).build());
        ProblemCreationResponse response = problemService.createProblem(request);
        Assertions.assertEquals(new ProblemCreationResponse(1L).getProblemId(), response.getProblemId());
    }

}
