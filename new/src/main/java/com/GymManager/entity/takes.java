package com.GymManager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value="Takes")
public class takes {
    @TableId 
	private String CourseId;
	
	private String VIPId;
	
	private int CourseTimes;

	public String getCourseId() {
		return CourseId;
	}

	public void setCourseId(String courseId) {
		CourseId = courseId;
	}

	public String getVIPId() {
		return VIPId;
	}

	public void setVIPId(String vIPId) {
		VIPId = vIPId;
	}

	public int getCourseTimes() {
		return CourseTimes;
	}

	public void setCourseTimes(int courseTimes) {
		CourseTimes = courseTimes;
	}
	
	
}
