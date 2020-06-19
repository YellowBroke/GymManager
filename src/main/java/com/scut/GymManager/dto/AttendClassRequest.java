package com.scut.GymManager.dto;

import lombok.Builder;
import lombok.Data;

/**
 * create by YellowBroke on 2020年6月18日 21点14分
 */

@Data
@Builder
public class AttendClassRequest {

    private String courseId;

    private String vipId;

    private String classroom;

}
