package com.example.demo.domain.review.exception;

import com.example.demo.common.exception.DomainException;

public class ReviewException extends DomainException {
    public ReviewException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ReviewException(String msg) {
        super(msg);
    }
}
