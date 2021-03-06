package com.scut.GymManager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.GymManager.dto.AttendClassRequest;
import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.dto.TakesRequest;
import com.scut.GymManager.entity.Takes;
import com.scut.GymManager.entity.VipInfo;
import com.scut.GymManager.exception.*;

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

    /**
     * 修改会员信息
     * @param vipInfo
     * @throws ModifyException
     */
    public void modifyVipInfo(VipInfo vipInfo) throws ModifyException;

    /**
     * 会员注销
     * @param phoneNumber
     * @throws VipDeleteException
     */
    public void deleteVip(String phoneNumber) throws VipDeleteException;

    /**
     * 会员转卡
     * @param oldPhone
     * @param newPhone
     * @throws VipTransferCardException
     */
    public void transferCard(String oldPhone,String newPhone) throws VipTransferCardException;

    /**
     * 会员卡充值
     * @param vipID
     * @param time
     * @throws VipRenewalException
     */
    public void renewal(String vipID,int time)throws VipRenewalException;

    /**
     * 会员选课
     * @param takesRequest
     * @throws CourseChosenException
     */
    public void courseChosen(TakesRequest takesRequest) throws CourseChosenException;

    /**
     * 管理员查看会员信息
     * @param PhoneNumber
     * @return
     * @throws QueryException
     */
    public VipInfo getVipInfoByPhone(String PhoneNumber) throws QueryException;

    /**
     * 查看本人的会员信息
     * @return
     */
    public VipInfo getVipInfo();
}
