package com.scut.GymManager.service.impl;

import com.scut.GymManager.dto.CoachInfoRequest;
import com.scut.GymManager.entity.CoachInfo;
import com.scut.GymManager.exception.CoachModifyException;
import com.scut.GymManager.mapper.CoachMapper;
import com.scut.GymManager.service.CoachService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * create by wxh on 2020年6月18日
 */

@Service
public class CoachServiceImpl implements CoachService{

    @Resource
    private CoachMapper coachMapper;
    private CoachInfoRequest coachInfoRequest;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = {CoachModifyException.class})
    public void createCoach(CoachInfoRequest coachInfoRequest) throws CoachModifyException {

        CoachInfo coachInfo= CoachInfo.builder()
                .coach_id(coachInfoRequest.getCoach_id())
                .IDCard(coachInfoRequest.getIDCard())
                .name(coachInfoRequest.getName())
                .phoneNumber(coachInfoRequest.getPhoneNumber())
                .birthday(coachInfoRequest.getBirthday())
                .sex(coachInfoRequest.getSex())
                .sportsEvent(coachInfoRequest.getSportsEvent())
                .build();

        if (coachMapper.insert(coachInfo) != 1) {
            throw new CoachModifyException("创建失败");
        }
    }

    @Transactional(rollbackFor = {CoachModifyException.class})
    public void modifyCoach(CoachInfoRequest coachInfoRequest) throws CoachModifyException {

        CoachInfo coachInfo= CoachInfo.builder()
                .coach_id(coachInfoRequest.getCoach_id())
                .IDCard(coachInfoRequest.getIDCard())
                .name(coachInfoRequest.getName())
                .phoneNumber(coachInfoRequest.getPhoneNumber())
                .birthday(coachInfoRequest.getBirthday())
                .sex(coachInfoRequest.getSex())
                .sportsEvent(coachInfoRequest.getSportsEvent())
                .build();

        if (coachMapper.updateById(coachInfo) != 1) {
            throw new CoachModifyException("修改失败");
        }
    }

    @Transactional(rollbackFor = {CoachModifyException.class})
    public void deleteCoach(String coach_id) throws CoachModifyException {

        CoachInfo coachInfo= CoachInfo.builder()
                .coach_id(coachInfoRequest.getCoach_id())
                .build();

        if(coachMapper.deleteById(coach_id) != 1){
            throw new CoachModifyException("删除失败");
        }
    }
}
