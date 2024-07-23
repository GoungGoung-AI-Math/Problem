package com.example.demo.domain.question.exception;

import com.example.demo.common.exception.DomainException;

public class QuestionException extends DomainException {
    public QuestionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public QuestionException(String msg) {
        super(msg);
    }
}
