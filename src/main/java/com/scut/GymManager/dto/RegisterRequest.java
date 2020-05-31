package com.scut.GymManager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by YellowBroke on 2020年5月31日 21点51分
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @ApiModelProperty(value = "用户名",name = "username", example = "test")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456")
    private String password;
}
