package com.example.demo.domain.gpt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("image_url")
public class ReqImageContent extends ReqContent {

    private final MessageType type = MessageType.IMAGE_URL;
    @JsonProperty("image_url")
    private EncodedImage encodedImage;

    @Override
    public MessageType getType() {
        return type;
    }

    // Getters and setters
    @Override
    @JsonIgnore
    public String getValue() {
        return encodedImage.getUrl();
    }
}