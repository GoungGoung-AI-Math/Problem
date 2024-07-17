package Problem.Math.AI.domain.s3file;

import Problem.Math.AI.domain.s3file.dto.PreSignedUrlRequest;
import Problem.Math.AI.domain.s3file.dto.PreSignedUrlResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.StandardCharsets;
@ActiveProfiles("local")
@SpringBootTest(value = {
        "aws.s3.bucket-name=solsol-bucket",
        "aws.s3.s3-region=ap-xxxxx-2",
        "aws.s3.url-expires-time=10"
})
@AutoConfigureMockMvc
class S3ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @SpyBean
    S3Service spyS3Service;

    PreSignedUrlRequest request = PreSignedUrlRequest.builder()
            .keyName("gajua_in_seoul")
            .metadata(new HashMap<String, String>(){
                {
                    put("목표는", "인서울");
                    put("가즈아","수학 100점");
                }
            })
            .build();

    PreSignedUrlResponse response = PreSignedUrlResponse.builder()
            .keyName("gajua_in_seoul")
            .metadata(new HashMap<String, String>(){
                {
                    put("목표는", "인서울");
                    put("가즈아", "수학 100점");
                }
            })
            .resignedUrl("https://example.com")
            .build();

    @Test
    public void givenDto_whenPreSignedURlRequest_thenReturnResponse() throws Exception {
        // Mock the service method
        when(spyS3Service.createPresignedUrl(request)).thenReturn(response);

        // Perform the request
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/s3/presigned-url")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(new ObjectMapper().writeValueAsString(request))
                    ).andExpect(status().isOk())
                .andReturn();
        // Deserialize the response body only
        // 한글이 정상적으로 역직렬화되지 않을 때는 StandardCharsets.UTF_8 를 ObjectMapper에 사용해보자
        PreSignedUrlResponse responseBody = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), PreSignedUrlResponse.class);

        assertEquals(response.getKeyName(), responseBody.getKeyName());
        assertEquals(response.getMetadata(), responseBody.getMetadata());
        assertFalse(responseBody.getResignedUrl().isEmpty());
    }
}