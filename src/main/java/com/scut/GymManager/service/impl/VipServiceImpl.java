package com.scut.GymManager.service.impl;

import com.scut.GymManager.dto.AttendClassRequest;
import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.dto.JwtResponse;
import com.scut.GymManager.entity.OnLesson;
import com.scut.GymManager.entity.VipCard;
import com.scut.GymManager.entity.VipInfo;
import com.scut.GymManager.exception.*;
import com.scut.GymManager.mapper.OnLessonMapper;
import com.scut.GymManager.mapper.VipCardMapper;
import com.scut.GymManager.mapper.VipInfoMapper;
import com.scut.GymManager.service.VipService;
import com.scut.GymManager.utility.JwtUtil;
import com.scut.GymManager.utility.UUIDUtil;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class VipServiceImpl implements VipService {

    @Resource
    private VipInfoMapper vipInfoMapper;

    @Resource
    private VipCardMapper vipCardMapper;

    @Resource
    private OnLessonMapper onLessonMapper;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UUIDUtil uuidUtil;


    @Override
    @Transactional(rollbackFor = {VipJoinException.class})
    public void join(JoinRequest joinRequest) throws VipJoinException {

//        String identity = jwtUtil.extractIdentitySubject(this.httpServletRequest);
//        if (!identity.equals("Vip")){
//            throw new VipJoinException("你没有该权限");
//        }
        //判断入会请求是否本人发出
        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!joinRequest.getVipId().equals(uid)){
            throw new VipJoinException("会员入会失败");
        }

        VipInfo vipInfo = VipInfo.builder()
                .vipId(joinRequest.getVipId())
                .vipIdCard(joinRequest.getIdCard())
                .vipName(joinRequest.getName())
                .vipPhoneNumber(joinRequest.getPhoneNumber())
                .vipBirthday(joinRequest.getBirthday())
                .build();

        VipCard vipCard = VipCard.builder()
                .vipId(joinRequest.getVipId())
                .validTime(0)
                .cardStatus(false)
                .classStatus(false)
                .build();

        if (vipInfoMapper.insert(vipInfo) != 1 || vipCardMapper.insert(vipCard) != 1){
            throw new VipJoinException("会员入会异常，请重试！");
        }
    }

    @Override
    @Transactional(rollbackFor = {OnClassException.class})
    public void attendClass(AttendClassRequest attendClassRequest) throws OnClassException {

        String recordId = uuidUtil.get32UUIDString();

        OnLesson onLesson = OnLesson.builder()
                .recordId(recordId)
                .courseId(attendClassRequest.getCourseId())
                .classroom(attendClassRequest.getClassroom())
                .vipId(attendClassRequest.getVipId())
                .startTime(LocalDateTime.now())
                .build();

        VipCard card = vipCardMapper.selectById(attendClassRequest.getVipId());

        card.setLastOnClassId(recordId);
        card.setClassStatus(true);

        if (onLessonMapper.insert(onLesson) != 1 || vipCardMapper.updateById(card) != 1)
            throw new OnClassException("上课登记失败");


    }

    @Override
    public void finishClass(String lastOnLessonId) throws FinishClassException {

        //这里模拟刷卡场景，会员卡滴卡时外设读取会员卡中的userId和lastOnLessonId传入，这里模拟利用请求的头获得userId
        String vipId = jwtUtil.extractUidSubject(this.httpServletRequest);

        VipCard card = vipCardMapper.selectById(vipId);

        //改变会员卡的上课状态和保存的上一次上课记录id
        card.setClassStatus(false);
        card.setLastOnClassId(null);

        OnLesson record = onLessonMapper.selectById(lastOnLessonId);

        //设置离开时间
        record.setLeaveTime(LocalDateTime.now());

        if (vipCardMapper.updateById(card) != 1 || onLessonMapper.updateById(record) != 1)
            throw new FinishClassException("下课登记失败");
    }

    @Override
    public void modifyVipInfo(VipInfo vipInfo) throws ModifyException {

        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!vipInfo.getVipId().equals(uid))
            throw new ModifyException("你没有操作权限");

        VipInfo newVipInfo = vipInfoMapper.selectById(uid);

        newVipInfo = VipInfo.builder()
                .vipId(uid)
                .vipIdCard(vipInfo.getVipIdCard() == null ? newVipInfo.getVipIdCard() : vipInfo.getVipIdCard())
                .vipName(vipInfo.getVipName() == null ? newVipInfo.getVipName() : vipInfo.getVipName())
                .vipPhoneNumber(vipInfo.getVipPhoneNumber() == null ? newVipInfo.getVipPhoneNumber() : vipInfo.getVipPhoneNumber())
                .vipBirthday(vipInfo.getVipBirthday() == null ? newVipInfo.getVipBirthday() : vipInfo.getVipBirthday())
                .build();

        if (vipInfoMapper.updateById(newVipInfo) != 1)
            throw new ModifyException("用户信息修改失败");


    }
}
