package com.scut.GymManager.exception;

public class OnClassException extends Exception{

    private String message;

    public OnClassException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
