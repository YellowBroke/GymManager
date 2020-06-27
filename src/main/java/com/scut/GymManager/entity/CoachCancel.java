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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("coach_cancel")

public class CoachCancel {

    @TableId(value = "coach_id")
    private String coachId;

    private String coachIdCard;

    private String coachName;

    private String coachPhoneNumber;

    private String coachBirth;

    private String coachSex;

    private String coachSportEvent;
}