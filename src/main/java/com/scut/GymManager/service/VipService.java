package com.scut.GymManager.service;

import com.scut.GymManager.dto.AttendClassRequest;
import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.entity.VipInfo;
import com.scut.GymManager.exception.FinishClassException;
import com.scut.GymManager.exception.ModifyException;
import com.scut.GymManager.exception.OnClassException;
import com.scut.GymManager.exception.VipJoinException;

public interface VipService {

    /**
     * 会员入会
     * @param joinRequest 入会请求
     */
    public void join(JoinRequest joinRequest) throws VipJoinException;

    /**
     * 会员上课登记
     * @param attendClassRequest
     */
    public void attendClass(AttendClassRequest attendClassRequest) throws OnClassException;

    /**
     * 会员下课登记
     * @param lastOnLessonId 会员卡上的上一次上课记录id
     * @throws FinishClassException
     */
    public void finishClass(String lastOnLessonId) throws FinishClassException;

    public void modifyVipInfo(VipInfo vipInfo) throws ModifyException;
}
