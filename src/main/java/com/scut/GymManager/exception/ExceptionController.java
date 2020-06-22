package com.scut.GymManager.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.scut.GymManager.utility.ResponseGenerator;
import com.scut.GymManager.utility.SuccessResponse;


@RestControllerAdvice(basePackages="com.scut.GymManager.contoller")
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
