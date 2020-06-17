package com.GymManager.entity;


import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="CourseInfo")
public class CourseInfo {
	@TableId
	private String CourseId;
	
	private String CourseName;
	
	private int CourseTimes;
	
	private int MaxNumber;
	
	private String Classroom;
	
	private Date Time;
	
	public CourseInfo(String CourseId,String CourseName,int CourseTimes,int MaxNumber,String Classroom,Date Time)
	{
		this.Classroom=Classroom;
		this.CourseId=CourseId;
		this.CourseName=CourseName;
		this.CourseTimes=CourseTimes;
		this.MaxNumber=MaxNumber;
		this.Time=Time;
	}
	public String getCourseId()
	{
		return CourseId;
	}
	public String getCourseName()
	{
		return CourseName;
	}
	public int getCourseTime()
	{
		return CourseTimes;
	}
	public int getMaxNumber()
	{
		return MaxNumber;
	}
	public String getClassroom()
	{
		return Classroom;
	}
	public Date getDate()
	{
		return Time;
	}
	public String toString()
	{
		return "CourseId: "+getCourseId()+" Name: "+getCourseName()+" CourseTimes: "+getCourseTime() +" Nubmer: "+getMaxNumber()+" Classroom: "
				+getClassroom()+" Date: "+getDate();
	}
}
