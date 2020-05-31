package com.scut.GymManager.service;

import com.scut.GymManager.dto.RegisterRequest;
import com.scut.GymManager.exception.UserRegisterException;

/**
 * create by YellowBroke on 2020年5月31日 21点50分
 */
public interface UserBasicService {

    /**
     *
     * @param registerRequest 注册请求信息
     * @throws UserRegisterException 用户名已存在
     */
    void userRegister(RegisterRequest registerRequest) throws UserRegisterException;
}
