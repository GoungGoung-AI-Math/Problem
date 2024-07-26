package com.example.demo.domain.problem.controller;

import com.example.demo.domain.problem.dto.ProblemCreationRequest;
import com.example.demo.domain.problem.dto.ProblemCreationResponse;
import com.example.demo.domain.problem.dto.response.ProblemResponse;
import com.example.demo.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-problem-list/{examId}")
    public ResponseEntity<List<ProblemResponse>> getProblemList(
            @PathVariable Long examId)
    {
        List<ProblemResponse> problemList = problemService.getProblems(examId);
        return ResponseEntity.ok(problemList);
    }
}
