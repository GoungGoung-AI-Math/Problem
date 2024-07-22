package com.example.demo.domain.gpt.service;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestDto;
import com.example.demo.domain.gpt.dto.VisionReqDto;

public interface GptAsyncService {
    void attemptMarkRequest(AttemptAnalysisRequestDto attempt);
    VisionReqDto attemptMarkResponse();
}
