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
public class CourseInfoResponse {

    private String courseId;

    private String CourseName;

    private int CourseTime;

    private String coachId;

    private int MaxNumber;

    private String Classroom;

    private int studentNum;

    private List<CourseTimeRequest> timeList;
}
