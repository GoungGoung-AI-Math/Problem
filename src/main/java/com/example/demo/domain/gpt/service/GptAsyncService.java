package com.example.demo.domain.gpt.service;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisDto;
import com.example.demo.domain.gpt.dto.VisionReqDto;

public interface GptAsyncService {
    void attemptMarkRequest(AttemptAnalysisDto attempt);
    VisionReqDto attemptMarkResponse();
}
