package com.example.demo.domain.s3file;

import com.example.demo.domain.s3file.beans.BucketName;
import com.example.demo.domain.s3file.beans.S3Region;
import com.example.demo.domain.s3file.beans.UrlExpiresTime;
import com.example.demo.domain.s3file.dto.PreSignedUrlRequest;
import com.example.demo.domain.s3file.dto.PreSignedUrlResponse;
import com.example.demo.domain.s3file.exception.S3InvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final BucketName bucketName;
    private final S3Region s3Region;
    private final UrlExpiresTime urlExpiresTime;

    /* Create a presigned URL to use in a subsequent PUT request */
    public PreSignedUrlResponse createPresignedUrl(PreSignedUrlRequest request) {
        try (S3Presigner presigner = S3Presigner.builder().region(s3Region.getRegion()).build()) {

            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName.getBucketName())
                    .key(request.getKeyName())
                    .metadata(request.getMetadata())
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(urlExpiresTime.getTime()))  // The URL expires in 10 minutes.
                    .putObjectRequest(objectRequest)
                    .build();

            PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
            String myURL = presignedRequest.url().toString();
            log.info("Presigned URL to upload a file to: [{}]", myURL);
            log.info("HTTP method: [{}]", presignedRequest.httpRequest().method());

            return new PreSignedUrlResponse(request,presignedRequest.url().toExternalForm());
        } catch (RuntimeException e){
            throw new S3InvalidException(e.getMessage(), e);
        }
    }
}
