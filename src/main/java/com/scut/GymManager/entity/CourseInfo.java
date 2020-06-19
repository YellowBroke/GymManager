package com.scut.GymManager.entity;


import java.util.Date;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
@TableName(value="CourseInfo")
public class CourseInfo {
	@TableId(value = "Course_id",type = IdType.ASSIGN_UUID)
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
	public Date getTime()
	{
		return Time;
	}
	public String toString()
	{
		return "CourseId: "+getCourseId()+" Name: "+getCourseName()+" CourseTimes: "+getCourseTime() +" Nubmer: "+getMaxNumber()+" Classroom: "
				+getClassroom()+" Date: "+getTime();
	}
}
