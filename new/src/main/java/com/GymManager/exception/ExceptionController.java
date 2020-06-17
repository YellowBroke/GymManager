package com.GymManager.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.GymManager.util.ResponseGenerator;
import com.GymManager.util.SuccessResponse;


@RestControllerAdvice(basePackages="com.gym.contoller")
public class ExceptionController {
   
	private Logger log=LoggerFactory.getLogger(getClass());
	@ExceptionHandler(value=CrudException.class)
	public SuccessResponse error1(CrudException e)
	{
		log.info(e.getMessage());
		return ResponseGenerator.getErrorResponse(e.getMessage());
	}
	@ExceptionHandler(value=SQLException.class)
	public SuccessResponse error2(SQLException e)
	{
		log.info(e.getMessage());
		return ResponseGenerator.getErrorResponse(e.getMessage());
	}
}
