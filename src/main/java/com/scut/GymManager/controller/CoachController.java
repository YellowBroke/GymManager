package com.scut.GymManager.controller;

import com.scut.GymManager.dto.CoachInfoRequest;
import com.scut.GymManager.dto.SuccessResponse;
import com.scut.GymManager.entity.CoachInfo;
import com.scut.GymManager.exception.CoachModifyException;
import com.scut.GymManager.exception.CreateException;
import com.scut.GymManager.exception.DeleteException;
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
@RequestMapping(value = "/api/coach")
public class CoachController {

    @Resource
    private CoachService coachService;

    @ApiOperation("创建教练")
    @RequestMapping(value = "/createCoach", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createCoach(@RequestBody CoachInfoRequest coachInfoRequest) {

        try {
            coachService.createCoach(coachInfoRequest);
            log.info("教练 {} 创建成功", coachInfoRequest.getName());
            return ResponseEntity.ok(new SuccessResponse(true, "创建教练成功"));
        } catch (CreateException e) {
            log.info("教练 {} 创建失败", coachInfoRequest.getName());
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("修改教练信息")
    @RequestMapping(value = "/modifyCoach", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> modifyCoach(@RequestBody CoachInfo coachInfo) {

        try {
            coachService.modifyCoach(coachInfo);
            log.info("教练信息修改成功");
            return ResponseEntity.ok(new SuccessResponse(true, "教练信息修改成功"));
        } catch (CoachModifyException e) {
            log.info("教练信息修改失败");
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("删除教练信息")
    @RequestMapping(value = "/deleteCoach/{coachId}", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> deleteCoach(@PathVariable String coachId) {

        try {
            coachService.deleteCoach(coachId);
            log.info("教练删除成功");
            return ResponseEntity.ok(new SuccessResponse(true, "教练信息删除成功"));
        } catch (DeleteException e) {
            log.info("教练删除失败");
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }


    @ApiOperation("管理员通过电话号码查看教练信息")
    @RequestMapping(value = "/queryByPhone/{phoneNumber}",method = RequestMethod.GET)
    public ResponseEntity<CoachInfo> queryByPhone(@PathVariable String phoneNumber) {

        return ResponseEntity.ok(coachService.queryCoachInfoByPhone(phoneNumber));
    }


    @ApiOperation("教练查看个人信息")
    @RequestMapping(value = "/getCoachInfo" ,method = RequestMethod.GET)
    public ResponseEntity<CoachInfo> getCoachInfo() {

        return ResponseEntity.ok(coachService.getCoachInfo());
    }

}
