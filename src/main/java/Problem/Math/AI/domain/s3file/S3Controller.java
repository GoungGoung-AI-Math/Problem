package Problem.Math.AI.domain.s3file;

import Problem.Math.AI.domain.s3file.dto.PreSignedUrlRequest;
import Problem.Math.AI.domain.s3file.dto.PreSignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/s3")
public class S3Controller {
    private final S3Service s3Service;

    @PutMapping("/presigned-url")
    public ResponseEntity<PreSignedUrlResponse> getPreSignedUrl(@RequestBody PreSignedUrlRequest preSignedUrlRequest){
        PreSignedUrlResponse response = s3Service.createPresignedUrl(preSignedUrlRequest);
        return ResponseEntity.ok(response);
    }
}
