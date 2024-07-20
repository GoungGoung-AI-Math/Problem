package com.example.demo.domain.problem.controller;

import com.example.demo.domain.problem.dto.ProblemCreationRequest;
import com.example.demo.domain.problem.dto.ProblemCreationResponse;
import com.example.demo.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
