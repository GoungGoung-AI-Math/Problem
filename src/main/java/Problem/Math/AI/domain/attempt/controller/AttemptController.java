package Problem.Math.AI.domain.attempt.controller;

import Problem.Math.AI.domain.attempt.dto.AttemptMarkRequest;
import Problem.Math.AI.domain.attempt.dto.SimpleMarkResponse;
import Problem.Math.AI.domain.attempt.service.AttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SimpleMarkResponse> sendAttemptToMarking(@RequestBody AttemptMarkRequest attempt){
        SimpleMarkResponse response = attemptService.markAttemptSolution(attempt);
        log.info("id : {}, status : {}",response.getProblemId(), response.getStatus().getStatus());
        return ResponseEntity.ok(response);
    }
}
