package com.example.demo.domain.attempt.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    PENDING("pending"),
    SUCCESS("success"),
    FAIL("fail");

    private final String status;

    Status (String status){
        this.status = status;
    }

    @JsonValue
    public String getStatus(){
        return status;
    }

}
