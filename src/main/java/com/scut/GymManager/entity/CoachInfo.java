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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("coach_info")

public class CoachInfo {

    @TableId(value = "coach_id", type = IdType.ASSIGN_UUID)
    private  String coachId;

    private String coachIdCard;

    private String coachName;

    private String coachPhoneNumber;

    private String coachBirth;

    private String coachSex;

    private String coachSportEvent;
}

