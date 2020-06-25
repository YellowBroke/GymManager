package com.scut.GymManager.exception;

public class DeleteException extends Exception {

    String message;

    public DeleteException(String message){
        this.message= message;

    }

    public String getMessage() {
        return message;

    }


}


