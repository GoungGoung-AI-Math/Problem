package Problem.Math.AI.domain.problem.controller;

import Problem.Math.AI.domain.problem.dto.ProblemCreationRequest;
import Problem.Math.AI.domain.problem.dto.ProblemCreationResponse;
import Problem.Math.AI.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    public ResponseEntity<ProblemCreationResponse> createProblem(@RequestBody ProblemCreationRequest request){
        ProblemCreationResponse response = problemService.createProblem(request);
        return ResponseEntity.ok(response);
    }

}
