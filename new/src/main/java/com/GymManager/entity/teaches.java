package com.GymManager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value="Teaches")
public class teaches {
     
	@TableId
	private String CourseId;
	
	private String CoachId;
	
	private int StudentNum;
	
	private int CourseTimes;

	public String getCoachId() {
		return CoachId;
	}

	public void setCoachId(String coachId) {
		CoachId = coachId;
	}

	public int getStudentNum() {
		return StudentNum;
	}

	public void setStudentNum(int studentNum) {
		StudentNum = studentNum;
	}

	public int getCourseTimes() {
		return CourseTimes;
	}

	public void setCourseTimes(int courseTimes) {
		CourseTimes = courseTimes;
	}
	
	public void setCourseId(String courseId)
	{
		this.CourseId=courseId;
	}
	public String getCourseId()
	{
		return CourseId;
	}
}
