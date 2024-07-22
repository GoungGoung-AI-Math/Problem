package com.example.demo.domain.attempt.mvc.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AttemptType {
    TEXT("text"),
    IMAGE_URL("url");

    private final String type;
    AttemptType(String type){
        this.type = type;
    }

    @JsonValue
    public String getType(){
        return type;
    }
}
