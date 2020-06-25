package com.scut.GymManager.controller;

import com.scut.GymManager.dto.AttendClassRequest;
import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.dto.SuccessResponse;
import com.scut.GymManager.dto.TakesRequest;
import com.scut.GymManager.entity.Takes;
import com.scut.GymManager.entity.VipInfo;
import com.scut.GymManager.exception.*;
import com.scut.GymManager.service.VipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * create by YellowBroke on 2020年6月16日 20点28分
 */

@Api(value = "会员接口", tags = "会员接口")
@RestController
@Slf4j
@RequestMapping(value = "/api/vip")
public class VipController {

    @Resource
    private VipService vipService;

    @ApiOperation("会员入会")
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> join(@RequestBody JoinRequest joinRequest) {

        try {
            vipService.join(joinRequest);
            log.info("用户 {} 入会成功", joinRequest.getName());
            return ResponseEntity.ok(new SuccessResponse(true, "入会成功"));
        } catch (VipJoinException e) {
            log.info("用户{} 注册失败", joinRequest.getName());
            return ResponseEntity.ok(new SuccessResponse(false,e.getMessage()));
        }
    }

    @ApiOperation("会员上课登记")
    @RequestMapping(value = "/attendClass",method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> attendClass(@RequestBody AttendClassRequest attendClassRequest) {

        try {
            vipService.attendClass(attendClassRequest);
            log.info("上课登记成功");
            return ResponseEntity.ok(new SuccessResponse(true, "上课登记成功"));
        } catch (OnClassException e) {
            log.info("上课登记失败");
            return ResponseEntity.ok(new SuccessResponse(false,e.getMessage()));
        }
    }

    @ApiOperation("会员下课登记")
    @RequestMapping(value = "/finishClass/{lastOnClassId}", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> finishClass(@PathVariable String lastOnClassId) {

        try {
            vipService.finishClass(lastOnClassId);
            log.info("下课登记成功");
            return ResponseEntity.ok(new SuccessResponse(true, "下课登记成功"));
        } catch (FinishClassException e) {
            log.info("下课登记失败");
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("修改会员信息")
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> modifyVipInfo(@RequestBody VipInfo vipInfo) {

        try {
            vipService.modifyVipInfo(vipInfo);
            log.info("会员 {} 信息修改成功", vipInfo.getVipName());
            return ResponseEntity.ok(new SuccessResponse(true, "会员信息修改成功"));
        } catch (ModifyException e) {
            log.info("会员 {} 信息修改失败", vipInfo.getVipName());
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("会员注销")
    @RequestMapping(value = "/vipDelete/{phoneNumber}", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> deleteVip(@PathVariable("phoneNumber")String phoneNumber) {

        try {
            vipService.deleteVip(phoneNumber);
            log.info("用户注销成功");
            return ResponseEntity.ok(new SuccessResponse(true, "注销成功"));
        } catch (VipDeleteException e) {
            log.info("用户注销失败");
            return ResponseEntity.ok(new SuccessResponse(false,e.getMessage()));
        }
    }

    @ApiOperation("转卡")
    @RequestMapping(value = "/vipTransfer", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> transferCard(@RequestBody String oldPhone,String newPhone) {

        try {
            vipService.transferCard(oldPhone,newPhone);
            log.info("用户转卡成功");
            return ResponseEntity.ok(new SuccessResponse(true, "转卡成功"));
        } catch (VipTransferCardException e) {
            log.info("用户注销失败");
            return ResponseEntity.ok(new SuccessResponse(false,e.getMessage()));
        }

    }

    @ApiOperation("会员卡充值")
    @RequestMapping(value = "/vipRenewal", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> renewal(@RequestBody String vipID,int time) {

        try {
            vipService.renewal(vipID,time);
            log.info("用户续费成功");
            return ResponseEntity.ok(new SuccessResponse(true, "续费成功"));
        } catch (VipRenewalException e) {
            log.info("用户续费失败");
            return ResponseEntity.ok(new SuccessResponse(false,e.getMessage()));
        }
    }

    @ApiOperation("会员选课")
    @RequestMapping(value = "/courseChosen", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> courseChosen(@RequestBody TakesRequest takesRequest) {

        try {
            vipService.courseChosen(takesRequest);
            log.info("选课成功");
            return ResponseEntity.ok(new SuccessResponse(true, "选课成功"));
        } catch (CourseChosenException e) {
            log.info("选课失败");
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("管理员通过手机号码查询会员信息")
    @RequestMapping(value = "/queryByPhone/{phoneNumber}",method = RequestMethod.GET)
    public ResponseEntity<VipInfo> queryVipInfoByPhone(@PathVariable String phoneNumber) {

        try {
            return ResponseEntity.ok(vipService.getVipInfoByPhone(phoneNumber));
        } catch (QueryException e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @ApiOperation("查看个人信息")
    @RequestMapping(value = "/getVipInfo",method = RequestMethod.GET)
    public ResponseEntity<VipInfo> getVipInfo() {
        return ResponseEntity.ok(vipService.getVipInfo());
    }

}
