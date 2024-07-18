package Problem.Math.AI.domain.attempt.controller;

import Problem.Math.AI.domain.attempt.dto.AttemptMarkRequest;
import Problem.Math.AI.domain.attempt.service.AttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attempt")
public class AttemptController {

    private final AttemptService attemptService;

    @PostMapping
    public void sendAttemptToMarking(@RequestBody AttemptMarkRequest attempt){
        attemptService.markAttemptSolution(attempt);
    }
}
