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
/*public class RegisterRequest {

    @ApiModelProperty(value = "用户名/手机号码",name = "phone_number", example = "135********")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456")
    private String password;
}*/


public class CoachInfoRequest {

    @ApiModelProperty(value = "教练id", name = "coach_id")
    private String coach_id;

    @ApiModelProperty(value = "身份证号码", name = "IDCard", example = "440981************")
    private String IDCard;

    @ApiModelProperty(value = "姓名", name = "name")
    private String name;

    @ApiModelProperty(value = "用户名/手机号码", name = "phoneNumber", example = "184********")
    private String phoneNumber;

    @ApiModelProperty(value = "出生日期", name = "birthday", example = "2020-6-18")
    private Date birthday;

    @ApiModelProperty(value = "性别", name = "sex", example = "男")
    private String sex;

    @ApiModelProperty(value = "擅长项目", name = "sportsEvent", example = "跳绳")
    private String sportsEvent;
}
