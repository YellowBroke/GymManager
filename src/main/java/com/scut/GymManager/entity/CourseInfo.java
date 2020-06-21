package com.scut.GymManager.entity;


import java.util.Date;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@TableName(value="course_info")
public class CourseInfo {
    // 自动随机生成id
	@TableId(value = "course_id",type = IdType.ASSIGN_UUID)
	private String CourseId;
	
	private String coachId;
	
	private String CourseName;
	
	private int CourseTime;
	
	private int MaxNumber;
	
	private int studentNum;
	
	private String Classroom;
	

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
		return CourseTime;
	}
	public int getMaxNumber()
	{
		return MaxNumber;
	}
	public String getClassroom()
	{
		return Classroom;
	}
	public String getCoachId()
	{
		return coachId;
	}
	public int getStudentNum()
	{
		return studentNum;
	}
	public String toString()
	{
		return "CourseId: "+getCourseId()+" Name: "+getCourseName()+" CourseTimes: "+getCourseTime() +" Nubmer: "+getMaxNumber()+" Classroom: "
				+getClassroom()+" CoachId: "+ getCoachId()+" StudentNum: "+getStudentNum();
	}
}
