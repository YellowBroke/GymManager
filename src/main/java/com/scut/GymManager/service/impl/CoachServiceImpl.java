package com.scut.GymManager.service.impl;

import com.scut.GymManager.dto.CoachInfoRequest;
import com.scut.GymManager.entity.CoachInfo;
import com.scut.GymManager.exception.CoachModifyException;
import com.scut.GymManager.exception.CreateException;
import com.scut.GymManager.exception.DeleteException;
import com.scut.GymManager.mapper.CoachMapper;
import com.scut.GymManager.service.CoachService;
import com.scut.GymManager.utility.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * create by wxh on 2020年6月18日
 */

@Service
public class CoachServiceImpl implements CoachService{

    @Resource
    private CoachMapper coachMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    @Transactional(rollbackFor = {CreateException.class})
    public void createCoach(CoachInfoRequest coachInfoRequest) throws CreateException {

        String coachId = jwtUtil.extractUidSubject(this.httpServletRequest);

        CoachInfo coachInfo= CoachInfo.builder()
                .coachId(coachId)
                .coachIdCard(coachInfoRequest.getCoachIDCard())
                .coachName(coachInfoRequest.getCoachName())
                .coachPhoneNumber(coachInfoRequest.getCoachPhoneNumber())
                .coachBirth(coachInfoRequest.getCoachBirth())
                .coachSex(coachInfoRequest.getCoachSex())
                .coachSportEvent(coachInfoRequest.getCoachSportEvent())
                .build();

        if (coachMapper.insert(coachInfo) != 1) {
            throw new CreateException("创建失败");
        }
    }

    @Transactional(rollbackFor = {CoachModifyException.class})
    public void modifyCoach(CoachInfo coachInfo) throws CoachModifyException {

        //从请求的header中拿到uid
        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!coachInfo.getCoachId().equals(uid))
            throw new CoachModifyException("你没有操作权限");

        //先从数据库中拿到旧的教练信息
        CoachInfo newCoachInfo = coachMapper.selectById(uid);

        newCoachInfo = CoachInfo.builder()
                .coachId(uid)
                .coachIdCard(coachInfo.getCoachIdCard() == null ? newCoachInfo.getCoachIdCard() : coachInfo.getCoachIdCard())
                .coachName(coachInfo.getCoachName() == null ? newCoachInfo.getCoachName() : coachInfo.getCoachName())
                .coachPhoneNumber(coachInfo.getCoachPhoneNumber() == null ? newCoachInfo.getCoachPhoneNumber() :coachInfo.getCoachPhoneNumber())
                .coachBirth(coachInfo.getCoachBirth() == null ? newCoachInfo.getCoachBirth() : coachInfo.getCoachBirth())
                .coachSex(coachInfo.getCoachSex() == null ? newCoachInfo.getCoachSex() : coachInfo.getCoachSex())
                .coachSportEvent(coachInfo.getCoachSportEvent() == null ? newCoachInfo.getCoachSportEvent() : coachInfo.getCoachSportEvent())
                .build();


        if (coachMapper.updateById(newCoachInfo) != 1) {
            throw new CoachModifyException("修改失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {CoachModifyException.class})
    public void deleteCoach(String coachId) throws DeleteException {

        //提取uid
        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!coachId.equals(uid)) {
            throw new DeleteException("你没有操作权限");
        }

        if(coachMapper.deleteById(coachId) != 1){
            throw new DeleteException("删除失败");
        }
    }
}
