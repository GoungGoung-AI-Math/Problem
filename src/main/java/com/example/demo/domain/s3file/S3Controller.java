package com.example.demo.domain.s3file;

import com.example.demo.domain.s3file.dto.PreSignedUrlRequest;
import com.example.demo.domain.s3file.dto.PreSignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
