package com.scut.GymManager.service.impl;

import com.scut.GymManager.dto.RegisterRequest;
import com.scut.GymManager.entity.UserBasic;
import com.scut.GymManager.exception.UserRegisterException;
import com.scut.GymManager.mapper.UserBasicMapper;
import com.scut.GymManager.service.UserBasicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * create by YellowBroke on 2020年5月31日 21点59分
 */
@Service
public class UserBasicServiceImpl implements UserBasicService {

    @Resource
    private UserBasicMapper userBasicMapper;

    @Override
    @Transactional(rollbackFor = {UserRegisterException.class})
    public void userRegister(RegisterRequest registerRequest) throws UserRegisterException {

        UserBasic userBasic= UserBasic.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();

        if (userBasicMapper.insert(userBasic) != 1){
            throw new UserRegisterException("注册失败");
        }

    }


}
