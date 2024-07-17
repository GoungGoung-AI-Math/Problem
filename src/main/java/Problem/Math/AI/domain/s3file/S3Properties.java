package Problem.Math.AI.domain.s3file;

import Problem.Math.AI.domain.s3file.beans.BucketName;
import Problem.Math.AI.domain.s3file.beans.S3Region;
import Problem.Math.AI.domain.s3file.beans.UrlExpiresTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws.s3")
public class S3Properties {
    private String bucketName;
    private String s3Region;
    private Long urlExpiresTime; // 분 단위

    @Bean
    public BucketName bucketName(){
        return ()->bucketName;
    }

    @Bean
    public S3Region region(){
        return ()->Region.of(s3Region);
    }

    @Bean
    public UrlExpiresTime urlExpiresTime(){
        return ()->urlExpiresTime;
    }
}
