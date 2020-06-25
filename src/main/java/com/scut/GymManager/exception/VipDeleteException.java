package com.scut.GymManager.exception;

public class VipDeleteException extends Exception{

    private String message;

    public VipDeleteException(String message){this.message=message;}

    @Override
    public String getMessage(){return message;}
}
