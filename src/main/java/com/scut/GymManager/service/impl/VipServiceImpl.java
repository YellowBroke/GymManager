package com.scut.GymManager.service.impl;

import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.dto.JwtResponse;
import com.scut.GymManager.entity.VipCard;
import com.scut.GymManager.entity.VipInfo;
import com.scut.GymManager.exception.VipJoinException;
import com.scut.GymManager.mapper.VipCardMapper;
import com.scut.GymManager.mapper.VipInfoMapper;
import com.scut.GymManager.service.VipService;
import com.scut.GymManager.utility.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class VipServiceImpl implements VipService {

    @Resource
    private VipInfoMapper vipInfoMapper;

    @Resource
    private VipCardMapper vipCardMapper;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private JwtUtil jwtUtil;


    @Override
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
                .idCard(joinRequest.getIdCard())
                .name(joinRequest.getName())
                .phoneNumber(joinRequest.getPhoneNumber())
                .birthday(joinRequest.getBirthday())
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
}
