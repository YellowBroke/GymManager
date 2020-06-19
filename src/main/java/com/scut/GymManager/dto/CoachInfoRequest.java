package com.scut.GymManager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * create by wxh on 2020年6月18日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachInfoRequest {

    @ApiModelProperty(value = "教练id", name = "coach_id")
    private String coach_id;

    @ApiModelProperty(value = "身份证号码", name = "IDCard", example = "440981************")
    private String IDCard;

    @ApiModelProperty(value = "姓名", name = "name",example = "wxh")
    private String name;

    @ApiModelProperty(value = "用户名/手机号码", name = "phoneNumber", example = "184********")
    private String phoneNumber;

    @ApiModelProperty(value = "出生日期", name = "birthday", example = "2020-06-18")
    private Date birthday;

    @ApiModelProperty(value = "性别", name = "sex", example = "男")
    private String sex;

    @ApiModelProperty(value = "擅长项目", name = "sportsEvent", example = "跳绳")
    private String sportsEvent;
}
