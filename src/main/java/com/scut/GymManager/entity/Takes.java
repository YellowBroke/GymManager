package com.scut.GymManager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选课信息
 * create by YellowBroke on 2020年6月24日 09点17分
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("takes")
public class Takes {

    @TableId("course_id")
    private String courseId;

    @TableId("vip_id")
    private String vipId;
}
