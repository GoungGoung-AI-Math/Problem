package com.example.demo.common.exception;

public abstract class DomainException extends RuntimeException{
    public DomainException(String msg, Throwable cause){
        super(msg, cause);
    }
    public DomainException(String msg){
        super(msg);
    }
}
