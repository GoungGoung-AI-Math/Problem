package com.example.demo.domain.like.exception;

public class LikeException extends RuntimeException{
    public LikeException(String msg, Throwable cause){
        super(msg, cause);
    }
    public LikeException(String msg){
        super(msg);
    }
}