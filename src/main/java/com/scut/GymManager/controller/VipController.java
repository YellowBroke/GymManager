package com.scut.GymManager.controller;

import com.scut.GymManager.dto.AttendClassRequest;
import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.dto.SuccessResponse;
import com.scut.GymManager.entity.VipInfo;
import com.scut.GymManager.exception.FinishClassException;
import com.scut.GymManager.exception.ModifyException;
import com.scut.GymManager.exception.OnClassException;
import com.scut.GymManager.exception.VipJoinException;
import com.scut.GymManager.service.VipService;
import io.swagger.annotations.Api;
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

}
