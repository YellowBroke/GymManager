package com.scut.GymManager.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseRequest {
private String CourseName;
	
	private int CourseTimes;
	
	private int MaxNumber;
	
	private String Classroom;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date Time;
	public CourseRequest(String CourseName,int CourseTimes,int MaxNumber,String Classroom,Date Time)
	{
		this.Classroom=Classroom;
		this.CourseName=CourseName;
		this.CourseTimes=CourseTimes;
		this.MaxNumber=MaxNumber;
		this.Time=Time;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public int getCourseTimes() {
		return CourseTimes;
	}
	public void setCourseTimes(int courseTimes) {
		CourseTimes = courseTimes;
	}
	public int getMaxNumber() {
		return MaxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		MaxNumber = maxNumber;
	}
	public String getClassroom() {
		return Classroom;
	}
	public void setClassroom(String classroom) {
		Classroom = classroom;
	}
	public Date getTime() {
		return Time;
	}
	public void setTime(Date time) {
		Time = time;
	}
}
