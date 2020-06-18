package com.scut.GymManager.service;

import com.scut.GymManager.dto.CoachInfoRequest;
import com.scut.GymManager.exception.CoachModifyException;

/**
 * create by wxh on 2020年6月18日
 */

public interface CoachService {

    /**
     * @param coachInfoRequest 修改信息请求
     * @throws CoachModifyException 异常
     */

    void createCoach(CoachInfoRequest coachInfoRequest) throws CoachModifyException;

    void modifyCoach(CoachInfoRequest coachInfoRequest) throws CoachModifyException;

    void deleteCoach(String coach_id) throws CoachModifyException;
}
