package com.scut.GymManager.exception;


public class VipRenewalException extends Exception {
    private String message;

    public VipRenewalException(String message){this.message=message;}

    @Override
    public String getMessage(){return message;}
}
