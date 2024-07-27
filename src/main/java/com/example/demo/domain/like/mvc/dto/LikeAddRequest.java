package com.example.demo.domain.like.mvc.dto;

import com.example.demo.my.kafka.infra.kafka.dtos.DomainType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeAddRequest {
    private Long giverId; // 따봉 주는 사람
    private Long receiverId; // 따봉 받는 사람, 글쓴이
    private DomainType type;
    private Long domainId;
}
