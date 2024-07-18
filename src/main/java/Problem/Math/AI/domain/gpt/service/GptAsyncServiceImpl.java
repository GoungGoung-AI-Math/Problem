package Problem.Math.AI.domain.gpt.service;

import Problem.Math.AI.domain.attempt.dto.AttemptAnalysisRequest;
import Problem.Math.AI.domain.gpt.dto.VisionReqDto;
import org.springframework.stereotype.Service;

@Service
public class GptAsyncServiceImpl implements GptAsyncService{
    @Override
    public void attemptMarkRequest(AttemptAnalysisRequest attempt) {

    }

    @Override
    public VisionReqDto attemptMarkResponse() {
        return null;
    }
}
