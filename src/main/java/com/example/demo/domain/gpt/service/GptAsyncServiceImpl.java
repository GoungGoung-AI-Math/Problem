package com.example.demo.domain.gpt.service;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisRequestDto;
import com.example.demo.domain.gpt.dto.VisionReqDto;
import org.springframework.stereotype.Service;

@Service
public class GptAsyncServiceImpl implements GptAsyncService{
    @Override
    public void attemptMarkRequest(AttemptAnalysisRequestDto attempt) {

    }

    @Override
    public VisionReqDto attemptMarkResponse() {
        return null;
    }
}
