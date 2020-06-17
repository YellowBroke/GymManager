package com.scut.GymManager.utility;

public class ResponseGenerator {
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;
    
    public static SuccessResponse getSuccessResponse()
    {
    	SuccessResponse s=new SuccessResponse();
    	s.setStatus(RESULT_CODE_SUCCESS);
    	s.setMessage(DEFAULT_SUCCESS_MESSAGE);
    	s.setSuccess(true);
    	return s;
    }
    public static SuccessResponse getSuccessResponse(Object o)
    {
    	SuccessResponse s=new SuccessResponse();
    	s.setStatus(RESULT_CODE_SUCCESS);
    	s.setMessage(DEFAULT_SUCCESS_MESSAGE);
    	s.setSuccess(true);
    	s.setData(o);
    	return s;
    }
    public static SuccessResponse getErrorResponse()
    {
    	SuccessResponse s=new SuccessResponse();
    	s.setStatus(RESULT_CODE_SERVER_ERROR);
    	s.setMessage(DEFAULT_FAIL_MESSAGE);
    	s.setSuccess(false);
    	return s;
    }
    public static SuccessResponse getErrorResponse(String message)
    {
    	SuccessResponse s=new SuccessResponse();
    	s.setStatus(RESULT_CODE_SERVER_ERROR);
    	s.setMessage(message);
    	s.setSuccess(false);
    	return s;
    }
}
