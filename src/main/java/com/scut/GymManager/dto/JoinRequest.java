package com.scut.GymManager.dto;

/**
 * create by YellowBroke on 2020年6月16日 20点11分
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinRequest {

    @ApiModelProperty(value = "用户id", name = "vipId" , example = "**********************************************")
    private String vipId;

    @ApiModelProperty(value = "身份证号",name = "idCard", example = "440*****************")
    private String idCard;

    @ApiModelProperty(value = "会员名称",name = "name", example = "wmt")
    private String name;

    @ApiModelProperty(value = "手机号码",name = "phoneNumber", example = "183**********")
    private String phoneNumber;

    @ApiModelProperty(value = "生日",name = "birthday", example = "2020-01-01")
    private String birthday;
}
