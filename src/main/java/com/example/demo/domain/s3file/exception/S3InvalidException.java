package com.example.demo.domain.s3file.exception;

public class S3InvalidException extends RuntimeException{
    public S3InvalidException(String msg) {
        super(msg);
    }
    public S3InvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
