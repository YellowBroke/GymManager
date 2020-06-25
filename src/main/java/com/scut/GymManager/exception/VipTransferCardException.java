package com.scut.GymManager.exception;

public class VipTransferCardException extends Exception{
    private String message;

    public VipTransferCardException(String message){this.message=message;}

    @Override
    public String getMessage(){return message;}
}
