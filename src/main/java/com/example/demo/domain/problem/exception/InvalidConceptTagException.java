package com.example.demo.domain.problem.exception;

import com.example.demo.common.exception.DomainException;

public class InvalidConceptTagException extends DomainException {

    public InvalidConceptTagException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidConceptTagException(String msg) {
        super(msg);
    }
}
