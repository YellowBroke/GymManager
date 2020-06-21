package com.scut.GymManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor     //添加含有所有已声明字段属性参数
@Builder
@TableName("coach_info")

public class CoachInfo {

    //自动生成id
    @TableId(value = "coach_id", type = IdType.ASSIGN_UUID)
    private  String coachId;

    private String coachIdCard;

    private String coachName;

    private String coachPhoneNumber;

    private Date coachBirth;

    private String coachSex;

    private String coachSportEvent;
}

