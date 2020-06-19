package com.scut.GymManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDateTime;

/**
 * create by YellowBroke on 2020年6月18日 21点20分
 */

@Data
@Builder
@TableName(value = "on_lesson")
public class OnLesson {

    @TableId(value = "record_id")
    private String recordId;

    private String courseId;

    private String classroom;

    private String vipId;

    private LocalDateTime startTime;

    private LocalDateTime leaveTime;
}
