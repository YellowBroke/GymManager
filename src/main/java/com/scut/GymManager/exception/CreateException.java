package com.scut.GymManager.exception;

public class CreateException extends Exception {

    String message;

    public CreateException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
