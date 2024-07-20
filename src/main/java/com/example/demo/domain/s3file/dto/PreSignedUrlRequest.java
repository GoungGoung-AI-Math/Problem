package com.example.demo.domain.s3file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PreSignedUrlRequest {
    @JsonProperty("key-name")
    private String keyName;
    private Map<String, String> metadata;
}
