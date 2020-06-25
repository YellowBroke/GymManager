package com.scut.GymManager.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class CourseNameRequest {

    @ApiModelProperty(value = "课程名称",name = "courseName" , example = "健身操")
    private String courseName;

}
