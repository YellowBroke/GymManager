package com.scut.GymManager.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

	@ApiModelProperty(value = "教练名称",name = "coachName",example = "张三")
	private String coachName;

	@ApiModelProperty(value = "课程名称",name = "CourseName" , example = "健身操")
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

	private List<CourseTimeRequest> timeList;
}
