package com.switchfully.codecoach.security.authentication.user;

public class SigningFailedException extends RuntimeException{
    public SigningFailedException(String msg){
        super(msg);
    }
}