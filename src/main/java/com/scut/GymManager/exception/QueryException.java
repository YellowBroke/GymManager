package com.scut.GymManager.exception;

public class QueryException extends Exception {

    private String message;

    public QueryException(String message) {
        this.message =message;
    }

    public String getMessage() {

        return message;
    }


}
