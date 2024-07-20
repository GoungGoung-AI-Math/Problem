package com.example.demo.domain.gpt.service;

import com.example.demo.domain.attempt.dto.AttemptAnalysisRequest;
import com.example.demo.domain.gpt.dto.VisionReqDto;

public interface GptAsyncService {
    void attemptMarkRequest(AttemptAnalysisRequest attempt);
    VisionReqDto attemptMarkResponse();
}
