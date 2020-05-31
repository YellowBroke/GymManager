package com.scut.GymManager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * create by YellowBroke on 2020年5月31日 19点38分
 */

@Data
@AllArgsConstructor
public class SuccessResponse {

    @ApiModelProperty(value = "状态码", name = "status", example = "200")
    private Short status = 200;

    @ApiModelProperty(value = "请求是否成功", name = "success", example = "true")
    private Boolean success;

    @ApiModelProperty(value = "描述信息", name = "message", example = "请求成功")
    private String message;

    public SuccessResponse(Boolean success, String message){
        this.success = success;
        this.message = message;
    }

}

