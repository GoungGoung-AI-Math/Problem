package com.example.demo.domain.question.exception;

import com.example.demo.common.exception.DomainException;

public class AnswerException extends DomainException {
    public AnswerException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AnswerException(String msg) {
        super(msg);
    }
}
