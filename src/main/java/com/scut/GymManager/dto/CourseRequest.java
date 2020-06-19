package com.scut.GymManager.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseRequest {
private String CourseName;
	
	private int courseTime;
	
	private String coachId;
	
	private int MaxNumber;
	
	private String Classroom;
	
	private int studentNum;
	public CourseRequest(String CourseName,int CourseTimes,int MaxNumber,String Classroom,String coachId,int studentNum)
	{
		this.Classroom=Classroom;
		this.CourseName=CourseName;
		this.courseTime=CourseTimes;
		this.MaxNumber=MaxNumber;
		this.coachId=coachId;
		this.studentNum=studentNum;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public int getCourseTimes() {
		return courseTime;
	}
	public void setCourseTimes(int courseTimes) {
		courseTime = courseTimes;
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
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
}
