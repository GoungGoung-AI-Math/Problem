package com.example.demo.domain.attempt.mvc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateMessage {
    private Long userId;
    private Long problemId;
    private String status;
}
