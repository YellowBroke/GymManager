package com.scut.GymManager.exception;

/**
 * create by YellowBroke on 2020年6月24日 09点19分
 */
public class CourseChosenException extends Exception {

    private String message;

    public CourseChosenException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
