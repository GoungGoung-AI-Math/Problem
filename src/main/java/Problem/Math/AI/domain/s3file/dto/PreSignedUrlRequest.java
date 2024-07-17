package Problem.Math.AI.domain.s3file.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class PreSignedUrlRequest {
    private String keyName;
    private Map<String, String> metadata;
}
