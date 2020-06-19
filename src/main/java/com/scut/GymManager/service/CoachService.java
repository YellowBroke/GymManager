package com.scut.GymManager.service;

import com.scut.GymManager.dto.CoachInfoRequest;
import com.scut.GymManager.entity.CoachInfo;
import com.scut.GymManager.exception.CoachModifyException;
import com.scut.GymManager.exception.CreateException;
import com.scut.GymManager.exception.DeleteException;

/**
 * create by wxh on 2020年6月18日
 */

public interface CoachService {


    /**
     * 创建教练
     * @param coachInfoRequest
     * @throws CreateException
     */
    void createCoach(CoachInfoRequest coachInfoRequest) throws CreateException;

    /**
     *修改教练信息
     * @param coachInfo
     * @throws CoachModifyException
     */
    void modifyCoach(CoachInfo coachInfo) throws CoachModifyException;

    /**
     *删除教练
     * @param coach_id
     * @throws DeleteException
     */
    void deleteCoach(String coach_id) throws DeleteException;
}
