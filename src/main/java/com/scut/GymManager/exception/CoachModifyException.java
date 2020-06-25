package com.scut.GymManager.exception;

public class CoachModifyException extends Exception{
    private String message;

    public CoachModifyException(String message) { this.message = message; }

    @Override
    public String getMessage() {
        return message;
    }
}

