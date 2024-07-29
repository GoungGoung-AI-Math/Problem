package com.example.demo.domain.s3file.exception;

import com.example.demo.common.exception.DomainException;

public class S3InvalidException extends DomainException {
    public S3InvalidException(String msg) {
        super(msg);
    }
    public S3InvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
