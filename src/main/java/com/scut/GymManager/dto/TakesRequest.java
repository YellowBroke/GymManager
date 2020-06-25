package com.scut.GymManager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TakesRequest {

    @ApiModelProperty(value = "会员id", name = "vipId" , example = "7745481ad4sa84875s4da548sa4d")
    private String vipId;

    private List<String> courseId;

}
