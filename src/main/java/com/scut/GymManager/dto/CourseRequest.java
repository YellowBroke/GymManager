package com.scut.GymManager.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
private String CourseName;

	@ApiModelProperty(value = "开课次数",name = "CourseTime" , example = "10")
	private int CourseTime;

	@ApiModelProperty(value = "教练id" , name = "coachId" ,example = "************************")
	private String coachId;

	@ApiModelProperty(value = "最大人数", name = "MaxNumber" , example = "25")
	private int MaxNumber;

	@ApiModelProperty(value = "教室", name = "Classroom", example = "A1-201")
	private String Classroom;

	@ApiModelProperty(value = "选课人数", name = "studentNum" , example = "0")
	private int studentNum;

	public CourseRequest(String CourseName,int CourseTimes,int MaxNumber,String Classroom,String coachId,int studentNum)
	{
		this.Classroom=Classroom;
		this.CourseName=CourseName;

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
