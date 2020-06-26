package com.scut.GymManager.service.impl;

import com.scut.GymManager.dto.AttendClassRequest;
import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.dto.TakesRequest;
import com.scut.GymManager.entity.*;
import com.scut.GymManager.exception.*;
import com.scut.GymManager.mapper.*;
import com.scut.GymManager.service.VipService;
import com.scut.GymManager.utility.JwtUtil;
import com.scut.GymManager.utility.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class VipServiceImpl implements VipService {

    @Resource
    private VipInfoMapper vipInfoMapper;

    @Resource
    private VipCardMapper vipCardMapper;

    @Resource
    private OnLessonMapper onLessonMapper;

    @Resource
    private TakesMapper takesMapper;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private UserBasicMapper userBasicMapper;

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
                .cardStatus(true)
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
    @Transactional(rollbackFor = {FinishClassException.class})
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
    @Transactional(rollbackFor = {ModifyException.class})
    public void modifyVipInfo(VipInfo vipInfo) throws ModifyException {

        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!vipInfo.getVipId().equals(uid))
            throw new ModifyException("你没有操作权限");

        VipInfo newVipInfo = vipInfoMapper.selectById(uid);

        newVipInfo = VipInfo.builder()
                .vipId(uid)
                .vipIdCard(vipInfo.getVipIdCard() == null ? newVipInfo.getVipIdCard() : vipInfo.getVipIdCard())
                .vipName(vipInfo.getVipName() == null ? newVipInfo.getVipName() : vipInfo.getVipName())
                .vipPhoneNumber(newVipInfo.getVipPhoneNumber())//不允许修改手机号码
                .vipBirthday(vipInfo.getVipBirthday() == null ? newVipInfo.getVipBirthday() : vipInfo.getVipBirthday())
                .build();

        if (vipInfoMapper.updateById(newVipInfo) != 1)
            throw new ModifyException("用户信息修改失败");


    }


    @Override
    @Transactional(rollbackFor = {VipDeleteException.class})
    public void deleteVip(String phoneNumber) throws VipDeleteException{

        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        //通过手机找id
        String vipId=userBasicMapper.getUserIdByName(phoneNumber);

        if (!vipId.equals(uid))
            throw new VipDeleteException("你没有该权限");

        VipCard vipCard=vipCardMapper.selectById(vipId);
        if(vipCard==null){
            throw new VipDeleteException("找不到该会员，请重试！");
        }//找不到这个card

        if(!vipCard.isCardStatus()){
            throw new VipDeleteException("该用户已经注销");
        }//卡状态已经是false了

        vipCard.setCardStatus(false);

        if (vipCardMapper.updateById(vipCard) != 1)
        throw new VipDeleteException("会员注销失败");


    }

    @Override
    @Transactional(rollbackFor = {VipTransferCardException.class})
    public void transferCard(String oldPhone,String newPhone) throws VipTransferCardException{

        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!uid.equals("1"))
            throw new VipTransferCardException("只有管理员才能进行转卡");

        //通过手机找id
        String oldUid = userBasicMapper.getUserIdByName(oldPhone);
        String newUid = userBasicMapper.getUserIdByName(newPhone);


        VipCard oldVipCard = vipCardMapper.selectById(oldUid);
        VipCard newVipCard = vipCardMapper.selectById(newUid);

        //用户可能不存在
        if (oldVipCard == null){
            throw new VipTransferCardException("转卡发起方用户不存在，请检查手机号码是否正确");
        }


        if (newVipCard == null){
            throw new VipTransferCardException("受赠方用户不存在，请检查手机号码是否正确");
        }

        //转卡发起方没次数了或者卡被注销了
        if(oldVipCard.getValidTime() <= 0 || !oldVipCard.isCardStatus()){
            throw new VipTransferCardException("转卡发起方不可发起转卡！");
        }


        if(!newVipCard.isCardStatus()){
            throw new VipTransferCardException("受赠用户会员卡已被注销");
        }

        //改变卡的有效状态，注销转卡方的会员卡
        oldVipCard.setCardStatus(false);
        newVipCard.setValidTime(newVipCard.getValidTime()+oldVipCard.getValidTime());
        oldVipCard.setValidTime(0);

        if (vipCardMapper.updateById(oldVipCard) != 1 || vipCardMapper.updateById(newVipCard) != 1 )
            throw new VipTransferCardException("转卡失败");

    }

    @Override
    @Transactional(rollbackFor = {VipRenewalException.class})
    public void renewal(String vipId,int time) throws VipRenewalException{

        VipCard vipCard=vipCardMapper.selectById(vipId);
        //用户可能不存在
        if (vipCard == null){
            throw new VipRenewalException("用户不存在，请重试！");
        }

        int currentTime = vipCard.getValidTime();
        vipCard.setValidTime(currentTime + time);
        if (vipCardMapper.updateById(vipCard) != 1)
            throw new VipRenewalException("充值失败请重试");


    }

    @Override
    @Transactional(rollbackFor = {CourseChosenException.class})
    public void courseChosen(TakesRequest takesRequest) throws CourseChosenException {

        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!uid.equals(takesRequest.getVipId()))
            throw new CourseChosenException("您不能为别人选课");

        for (String courseId : takesRequest.getCourseId()) {

            Takes takes = Takes.builder()
                    .courseId(courseId)
                    .vipId(takesRequest.getVipId())
                    .build();

            if (takesMapper.insert(takes) != 1)
                throw new CourseChosenException("选课失败");
        }
    }

    @Override
    public VipInfo getVipInfoByPhone(String phoneNumber) throws QueryException{

        String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

        if (!uid.equals("1"))
            throw new QueryException("你没有该权限");

        return vipInfoMapper.selectById(userBasicMapper.getUserIdByName(phoneNumber));
    }

    @Override
    public VipInfo getVipInfo() {
        return vipInfoMapper.selectById(jwtUtil.extractUidSubject(this.httpServletRequest));
    }
}
