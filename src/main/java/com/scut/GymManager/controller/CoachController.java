package com.scut.GymManager.controller;

import com.scut.GymManager.dto.CoachInfoRequest;
import com.scut.GymManager.dto.SuccessResponse;
import com.scut.GymManager.exception.CoachModifyException;
import com.scut.GymManager.service.CoachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * create by wxh on 2020年6月18日
 */

@Api(value = "教练信息接口", tags = "教练信息接口")
@RestController
@Slf4j
//@CrossOrigin
@RequestMapping(value = "/api/coach")

public class CoachController {

    @Resource
    private CoachService coachService;

    @ApiOperation("创建教练")
    @RequestMapping(value = "/createCoach", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createCoach(@RequestBody CoachInfoRequest coachInfoRequest) {

        try {
            coachService.createCoach(coachInfoRequest);
            return ResponseEntity.ok(new SuccessResponse(true, "创建教练成功"));
        } catch (CoachModifyException e) {
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("修改教练信息")
    @RequestMapping(value = "/modifyCoach", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> modifyCoach(@RequestBody CoachInfoRequest coachInfoRequest) {

        try {
            coachService.modifyCoach(coachInfoRequest);
            return ResponseEntity.ok(new SuccessResponse(true, "教练信息修改成功"));
        } catch (CoachModifyException e) {
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("删除教练信息")
    @RequestMapping(value = "/deleteCoach", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> deleteCoach(@RequestBody CoachInfoRequest coachInfoRequest) {

        try {
            coachService.deleteCoach(coachInfoRequest.getCoach_id());
            return ResponseEntity.ok(new SuccessResponse(true, "教练信息删除成功"));
        } catch (CoachModifyException e) {
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }
}
