package Problem.Math.AI.domain.s3file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
