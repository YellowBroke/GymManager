package com.scut.GymManager.dto;

import com.sun.xml.internal.ws.resources.UtilMessages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.transform.Scale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

/**
 * create by YellowBroke on 2020年6月23日 21点56分
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseTimeRequest {

    @ApiModelProperty(value = "星期几", name = "day" , example = "星期一")
    private String day;

    @ApiModelProperty(value = "时", name = "hour", example = "12")
    private int hour;

    @ApiModelProperty(value = "分" , name = "minute", example = "00")
    private int minute;

    @ApiModelProperty(value = "秒", name = "second", example = "00")
    private int second;
//    @ApiModelProperty(value = "时分秒",name = "timeSlot", example = "12:12:12")
//    private Time timeSlot;
}
