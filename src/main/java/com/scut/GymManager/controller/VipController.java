package com.scut.GymManager.controller;

import com.scut.GymManager.dto.JoinRequest;
import com.scut.GymManager.dto.SuccessResponse;
import com.scut.GymManager.exception.VipJoinException;
import com.scut.GymManager.service.VipService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
