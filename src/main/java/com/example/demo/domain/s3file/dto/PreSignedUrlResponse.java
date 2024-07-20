package com.example.demo.domain.s3file.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PreSignedUrlResponse {
    private String keyName;
    private Map<String, String> metadata;
    private String resignedUrl;

    public PreSignedUrlResponse (PreSignedUrlRequest request, String url){
        this.keyName = request.getKeyName();
        this.metadata = request.getMetadata();
        this.resignedUrl = url;
    }
}
