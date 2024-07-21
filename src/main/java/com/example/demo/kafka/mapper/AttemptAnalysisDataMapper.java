package com.example.demo.kafka.mapper;

import com.example.demo.domain.attempt.dto.AttemptAnalysisRequest;
import com.example.demo.my.kafka.infra.avrobuild.AnalysisType;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisRequestAvroModel;
import com.example.demo.my.kafka.infra.avrobuild.MessageType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AttemptAnalysisDataMapper {
    public AttemptAnalysisRequestAvroModel attemptAnalysisRequestToAvroModel(AttemptAnalysisRequest attemptAnalysisRequest){
        return AttemptAnalysisRequestAvroModel.newBuilder()
                .setAttemptId(attemptAnalysisRequest.getAttemptId())
                .setAnalysisType(AnalysisType.valueOf(
                        attemptAnalysisRequest.getAnalysisType().name()
                ))
                .setMessageType(MessageType.valueOf(
                        attemptAnalysisRequest.getContent()
                ))
                .build();
    }
}
