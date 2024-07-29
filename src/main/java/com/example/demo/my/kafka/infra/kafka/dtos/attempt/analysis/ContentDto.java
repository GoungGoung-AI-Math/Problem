<<<<<<< HEAD
package com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis;

import com.example.demo.my.kafka.infra.kafka.dtos.MessageType;
=======
package User.Math.AI.my.kafka.infra.kafka.dtos.attempt.analysis;

import User.Math.AI.my.kafka.infra.kafka.dtos.MessageType;
>>>>>>> 0c9dda4f1906837a3e247cc8d0b3e7b99315661d
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContentDto {
    private MessageType messageType;
    private String content;
}
