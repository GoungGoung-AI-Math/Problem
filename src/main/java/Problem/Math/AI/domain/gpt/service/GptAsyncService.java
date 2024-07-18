package Problem.Math.AI.domain.gpt.service;

import Problem.Math.AI.domain.attempt.dto.AttemptAnalysisRequest;
import Problem.Math.AI.domain.gpt.dto.VisionReqDto;
import org.springframework.stereotype.Service;

@Service
public interface GptAsyncService {
    void attemptMarkRequest(AttemptAnalysisRequest attempt);
    VisionReqDto attemptMarkResponse();
}
