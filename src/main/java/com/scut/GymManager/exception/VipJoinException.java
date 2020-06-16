package com.scut.GymManager.exception;

public class VipJoinException extends Exception{

    private String message;

    public VipJoinException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
