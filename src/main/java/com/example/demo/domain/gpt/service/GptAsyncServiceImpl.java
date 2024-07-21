package com.example.demo.domain.gpt.service;

import com.example.demo.domain.attempt.dto.AttemptAnalysisDto;
import com.example.demo.domain.gpt.dto.VisionReqDto;
import org.springframework.stereotype.Service;

@Service
public class GptAsyncServiceImpl implements GptAsyncService{
    @Override
    public void attemptMarkRequest(AttemptAnalysisDto attempt) {

    }

    @Override
    public VisionReqDto attemptMarkResponse() {
        return null;
    }
}
