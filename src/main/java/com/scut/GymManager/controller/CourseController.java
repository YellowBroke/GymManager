package com.scut.GymManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.scut.GymManager.dto.SuccessResponse;
import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scut.GymManager.dto.CourseRequest;
import com.scut.GymManager.entity.CourseInfo;
import com.scut.GymManager.exception.CrudException;
import com.scut.GymManager.service.CourseInfoService;
import com.scut.GymManager.utility.JwtUtil;
import com.scut.GymManager.utility.ResponseGenerator;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/Course")
@Api(tags="CourseController")
@RestController
public class CourseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private JwtUtil jwtUtil;


	@Resource
	private CourseInfoService courseInfoService;


//	@ApiOperation("createCourse")
//	@PostMapping("/create")
//	public SuccessResponse createCourse(@RequestBody CourseRequest courseRequest) throws CrudException
//	{
//		courseInfoService.createCourse(courseRequest);
//		return ResponseGenerator.getSuccessResponse();
//	}
//	@ApiOperation("updateCourse")
//	@PostMapping("/update")
//	public SuccessResponse updateCourse(@RequestBody CourseInfo courseInfo) throws CrudException
//	{
//		courseInfoService.updateCourse(courseInfo);
//		return ResponseGenerator.getSuccessResponse();
//	}
//	@ApiOperation("deleteCourse")
//	@PostMapping("/delete")
//	public SuccessResponse deleteCourse(@RequestParam String CourseId) throws CrudException
//	{
//		courseInfoService.deleteCourse(CourseId);
//		return ResponseGenerator.getSuccessResponse();
//	}
//	@ApiOperation("viewCourse")
//	@GetMapping("/viewTable")
//	public SuccessResponse viewTable() throws CrudException
//	{
//		return ResponseGenerator.getSuccessResponse(courseInfoService.viewTable());
//	}
//	@ApiOperation("viewCoachCourse")
//	@GetMapping("/viewCoachCourse")
//	public SuccessResponse viewCoachCourseTable(HttpServletRequest request)
//	{
//		String CoachId=jwtUtil.extractUidSubject(request);
//		return ResponseGenerator.getSuccessResponse(courseInfoService.viewCoachCourseTable(CoachId));
//	}


	@ApiOperation("viewCoachCourse")
	@GetMapping("/viewCoachCourse")
	public ResponseEntity<SuccessResponse> viewCoachCourseTable(HttpServletRequest request) {
		String CoachId = jwtUtil.extractUidSubject(request);
		return ResponseEntity.ok(new SuccessResponse(true, "11"));
	}
}



//
//	@ApiOperation("viewVIPCourse")
//	@GetMapping("/viewVIPCourse")
//	public SuccessResponse viewVIPCourseTable(HttpServletRequest request)
//	{
//		String VIPId=jwtUtil.extractUidSubject(request);
//		return ResponseGenerator.getSuccessResponse(courseInfoService.viewVIPCourseTable(VIPId));
//	}
//}
