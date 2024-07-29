package com.example.demo.domain.problem.exception;

import com.example.demo.common.exception.DomainException;

public class ProblemException extends DomainException {
    public ProblemException(String msg, Throwable cause){
        super(msg, cause);
    }
    public ProblemException(String msg){
        super(msg);
    }
}
