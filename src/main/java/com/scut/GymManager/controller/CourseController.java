package com.scut.GymManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.GymManager.dto.CourseInfoResponse;
import com.scut.GymManager.dto.CourseNameRequest;
import com.scut.GymManager.utility.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scut.GymManager.dto.CourseRequest;
import com.scut.GymManager.entity.CourseInfo;
import com.scut.GymManager.exception.CrudException;
import com.scut.GymManager.service.CourseInfoService;
import com.scut.GymManager.utility.JwtUtil;
import com.scut.GymManager.utility.ResponseGenerator;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RequestMapping("/Course")
@Api(tags="课程接口")
@RestController
public class CourseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private JwtUtil jwtUtil;

	@Resource
	private CourseInfoService courseInfoService;


	@ApiOperation("创建课程")
	@PostMapping("/create")
	public SuccessResponse createCourse(@RequestBody CourseRequest courseRequest) throws CrudException
	{
		courseInfoService.createCourse(courseRequest);
		return ResponseGenerator.getSuccessResponse();
	}
	@ApiOperation("修改课程信息")
	@PostMapping("/update")
	public SuccessResponse updateCourse(@RequestBody CourseInfo courseInfo) throws CrudException
	{
		courseInfoService.updateCourse(courseInfo);
		return ResponseGenerator.getSuccessResponse();
	}
	@ApiOperation("删除课程")
	@PostMapping("/delete")
	public SuccessResponse deleteCourse(@RequestParam String CourseId) throws CrudException
	{
		courseInfoService.deleteCourse(CourseId);
		return ResponseGenerator.getSuccessResponse();
	}
	@ApiOperation("查看所有课程")
	@GetMapping("/viewTable")
	public SuccessResponse viewTable() throws CrudException
	{
		return ResponseGenerator.getSuccessResponse(courseInfoService.viewTable());
	}

	@ApiOperation("查看教练课程表")
	@GetMapping("/viewCoachCourse")
	public SuccessResponse viewCoachCourseTable(HttpServletRequest request)
	{
		String CoachId=jwtUtil.extractUidSubject(request);
		return ResponseGenerator.getSuccessResponse(courseInfoService.viewCoachCourseTable(CoachId));
	}



	@ApiOperation("查看会员课程表")
	@GetMapping("/viewVIPCourse")
	public SuccessResponse viewVIPCourseTable(HttpServletRequest request)
	{
		String VIPId=jwtUtil.extractUidSubject(request);
		return ResponseGenerator.getSuccessResponse(courseInfoService.viewVIPCourseTable(VIPId));
	}

	@ApiOperation("分页查询课程信息")
	@RequestMapping(value = "/query/{pageNo}/{pageSize}",method = RequestMethod.GET)
	public ResponseEntity<IPage<CourseInfo>> getCourseInfoByPage(@PathVariable Long pageNo,
																 @PathVariable Long pageSize) {

		return ResponseEntity.ok(courseInfoService.getCourseInfoByPage(pageNo,pageSize));
	}

	@ApiOperation("通过课程名称查看课程信息")
	@RequestMapping(value = "/queryByName",method = RequestMethod.POST)
	public ResponseEntity<List<CourseInfoResponse>> getCourseInfoByName(@RequestBody CourseNameRequest courseNameRequest) {

		return ResponseEntity.ok(courseInfoService.getCourseInfoByName(courseNameRequest.getCourseName()));

	}
}

