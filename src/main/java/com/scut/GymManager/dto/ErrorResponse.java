package com.scut.GymManager.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 统一错误返回
 * create by YellowBroke on 2020年6月8日 15点50分
 */
@Data
@Builder
public class ErrorResponse {

    private String timestamp;

    private Integer status;

    private String error;

    private String message;
}
