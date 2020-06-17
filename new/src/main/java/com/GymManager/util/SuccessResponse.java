package com.GymManager.util;

import java.io.Serializable;

public class SuccessResponse implements Serializable {


	private static final long serialVersionUID = 1L;
	private int status;
	private boolean success;
	private String message;
	private Object data;
	
	public SuccessResponse()
	{
	}
	
	public SuccessResponse(int status,boolean success, String message)
	{
		this.message=message;
		this.status=status;
		this.success=success;		
	}
	public void setData(Object data)
	{
		this.data=data;
	}
	public Object getData()
	{
		return data;
	}
	public void setStatus(int status)
	{
		this.status=status;
	}
	public void setSuccess(boolean success)
	{
		this.success=success;
	}
	public void setMessage(String message)
	{
		this.message=message;
	}
	public int getStatus()
	{
		return status;
	}
	public boolean getSuccess()
	{
		return success;
	}
	public String getMessage()
	{
		return message;
	}
	@Override
	public String toString()
	{
		return "status: "+getStatus()+" success: "+getSuccess()+" message: "+getMessage()+" data: "+getData();
	}

}
