package Problem.Math.AI.domain.problem.controller;

import Problem.Math.AI.domain.problem.dto.ProblemCreationRequest;
import Problem.Math.AI.domain.problem.dto.ProblemCreationResponse;
import Problem.Math.AI.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    public ProblemCreationResponse createProblem(@RequestBody ProblemCreationRequest request){
        return null;
    }

}
