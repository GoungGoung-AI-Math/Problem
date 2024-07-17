package Problem.Math.AI.domain.s3file;

import Problem.Math.AI.domain.s3file.beans.BucketName;
import Problem.Math.AI.domain.s3file.beans.S3Region;
import Problem.Math.AI.domain.s3file.beans.UrlExpiresTime;
import Problem.Math.AI.domain.s3file.dto.PreSignedUrlRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

class S3ServiceTest {
    S3Service s3Service;
    private final BucketName bucketName = ()->"openai-test-bucket-solsol";
    private final S3Region s3Region = ()-> Region.of("ap-northeast-2");
    private final UrlExpiresTime urlExpiresTime = () -> 10L;
    PreSignedUrlRequest validRequest = PreSignedUrlRequest.builder()
            .keyName("gajua_in_seoul")
            .metadata(new HashMap<String, String>(){
                {
                    put("목표는", "인서울");
                    put("가즈아","수학 100점");
                }
            })
            .build();
    @Test
    public void givenValidDto_whenRequest_thenReturnPreSignedUrl(){
        s3Service = new S3Service(bucketName, s3Region, urlExpiresTime);
        String url = s3Service.createPresignedUrl(validRequest);
        Assertions.assertFalse(url.isEmpty());
    }
}