package com.scut.GymManager.service;

import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.exception.VipJoinException;

public interface VipService {

    /**
     * 会员入会
     * @param joinRequest 入会请求
     */
    public void join(JoinRequest joinRequest) throws VipJoinException;
}
