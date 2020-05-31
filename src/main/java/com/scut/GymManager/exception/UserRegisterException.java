package com.scut.GymManager.exception;

public class UserRegisterException extends Exception{

    private String message;

    public UserRegisterException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
