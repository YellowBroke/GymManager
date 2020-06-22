package com.scut.GymManager.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * create by YellowBroke on 2020年6月18日 21点14分
 */

@Data
@Builder
public class AttendClassRequest {

    @ApiModelProperty(value = "课程id" , name = "courseId" ,example = "******************************")
    private String courseId;

    @ApiModelProperty(value = "会员id" , name = "vipId",example = "***********************")
    private String vipId;

    @ApiModelProperty(value = "教室", name = "classroom" ,example = "A1-201")
    private String classroom;

}
