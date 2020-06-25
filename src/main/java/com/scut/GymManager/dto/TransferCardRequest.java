package com.scut.GymManager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferCardRequest {

    @ApiModelProperty(value = "转卡方手机号码" , name = "oldPhone" ,example = "135********")
    private String oldPhone;

    @ApiModelProperty(value = "受卡方手机号码" , name = "newPhone" ,example = "135********")
    private String newPhone;
}
