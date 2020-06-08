package com.scut.GymManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json web token
 * create by YellowBroke on 2020年6月8日 16点05分
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;
}
