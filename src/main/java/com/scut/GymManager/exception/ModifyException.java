package com.scut.GymManager.exception;

public class ModifyException extends Exception {

    String message;

    public ModifyException(String message){
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }
}
