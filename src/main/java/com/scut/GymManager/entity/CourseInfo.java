package com.scut.GymManager.entity;



import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName(value="course_info")
public class CourseInfo {

	@TableId(value = "course_id")
	private String CourseId;
	
	private String coachId;
	
	private String CourseName;
	
	private int CourseTime;
	
	private int MaxNumber;
	
	private int studentNum;
	
	private String Classroom;

	public String toString()
	{
		return "CourseId: "+getCourseId()+" Name: "+getCourseName()+" CourseTimes: "+getCourseTime() +" Nubmer: "+getMaxNumber()+" Classroom: "
				+getClassroom()+" CoachId: "+ getCoachId()+" StudentNum: "+getStudentNum();
	}
}
