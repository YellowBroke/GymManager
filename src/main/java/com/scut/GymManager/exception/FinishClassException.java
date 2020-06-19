package com.scut.GymManager.exception;

public class FinishClassException extends Exception{

    private String message;

    public FinishClassException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
