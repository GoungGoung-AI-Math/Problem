package com.example.demo.domain.attempt.kafka.mapper;

import com.example.demo.domain.attempt.kafka.event.AttemptAnalysisDto;
import com.example.demo.my.kafka.infra.avrobuild.AnalysisType;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisRequestAvroModel;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import com.example.demo.my.kafka.infra.avrobuild.MessageType;
import org.springframework.stereotype.Component;


@Component
public class AttemptAnalysisDataMapper {
    public AttemptAnalysisRequestAvroModel attemptAnalysisRequestToAvroModel(AttemptAnalysisDto attemptAnalysisDto){
        return AttemptAnalysisRequestAvroModel.newBuilder()
                .setAttemptId(attemptAnalysisDto.getAttemptId())
                .setAnalysisType(AnalysisType.valueOf(
                        attemptAnalysisDto.getAnalysisType().name()
                ))
                .setMessageType(MessageType.valueOf(
                        attemptAnalysisDto.getMessageType().name()
                ))
                .setContent(attemptAnalysisDto.getContent())
                .build();
    }

    public AttemptAnalysisResponseAvroModel attemptAnalysisResponseToAvroModel(AttemptAnalysisDto attemptAnalysisDto){
        return AttemptAnalysisResponseAvroModel.newBuilder()
                .setAttemptId(attemptAnalysisDto.getAttemptId())
                .setAnalysisType(AnalysisType.valueOf(
                        attemptAnalysisDto.getAnalysisType().name()
                ))
                .setMessageType(MessageType.valueOf(
                        attemptAnalysisDto.getMessageType().name()
                ))
                .setContent(attemptAnalysisDto.getContent())
                .build();
    }

    public AttemptAnalysisDto avroModelToAttemptAnalysisDto(AttemptAnalysisResponseAvroModel avroModel){
        return AttemptAnalysisDto.builder()
                .attemptId(avroModel.getAttemptId())
                .analysisType(com.example.demo.common.AnalysisType.valueOf(avroModel.getAnalysisType().name()))
                .messageType(
                        com.example.demo.domain.gpt.dto.MessageType.valueOf(avroModel.getMessageType().name()))
                .content(avroModel.getContent())
                .build();
    }
}
