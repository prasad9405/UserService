package com.admin.userservice.exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(){

    }
    public BadCredentialsException(String message){
        super(message);
    }
}
