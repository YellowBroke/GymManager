package com.scut.GymManager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


/**
 * 课程时间
 * create by YellowBroke on 2020年6月23日 20点50分
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("course_time")
public class CourseTime {

    @TableId("course_id")
    private String courseId;

    @TableId("day")
    private String day;

    @TableId("time_slot")
    private Time timeSlot;
}
