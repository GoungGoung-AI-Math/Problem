package Problem.Math.AI.domain.gpt;

import Problem.Math.AI.domain.attempt.dto.AttemptMarkRequest;
import org.springframework.stereotype.Service;

@Service
public interface GptAsyncService {
    void markRequest(AttemptMarkRequest attempt);
}
