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
@Data                   //不用手写Getter，Setter等方法
@NoArgsConstructor      //创建无参构造函数
//@AllArgsConstructor     //添加含有所有已声明字段属性参数
@Builder
public class CoachInfoRequest {

    @ApiModelProperty(value = "教练id", name = "coachId")
    private String coachId;

    @ApiModelProperty(value = "身份证号码", name = "coachIDCard", example = "440981************")
    private String coachIDCard;

    @ApiModelProperty(value = "姓名", name = "coachName",example = "wxh")
    private String coachName;

    @ApiModelProperty(value = "用户名/手机号码", name = "coachPhoneNumber", example = "184********")
    private String coachPhoneNumber;

    @ApiModelProperty(value = "出生日期", name = "coachBirth", example = "2020-06-18")
    private Date coachBirth;

    @ApiModelProperty(value = "性别", name = "coachSex", example = "男")
    private String coachSex;

    @ApiModelProperty(value = "擅长项目", name = "coachSportEvent", example = "跳绳")
    private String coachSportEvent;
}
