package com.example.demo.common.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ContentType {
    TEXT("text"),
    IMAGE_URL("image_url");

    private final String type;
    ContentType(String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return type;
    }
}