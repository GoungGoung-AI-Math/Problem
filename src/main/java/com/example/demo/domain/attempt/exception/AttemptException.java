package com.example.demo.domain.attempt.exception;

import com.example.demo.common.exception.DomainException;

public class AttemptException extends DomainException {
    public AttemptException(String msg, Throwable cause){
        super(msg, cause);
    }
    public AttemptException(String msg){
        super(msg);
    }
}
