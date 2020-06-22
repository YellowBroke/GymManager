package com.scut.GymManager.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.scut.GymManager.utility.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scut.GymManager.mapper.CourseInfoMapper;
import com.scut.GymManager.dto.CourseRequest;
import com.scut.GymManager.entity.CourseInfo;
import com.scut.GymManager.exception.CrudException;
import com.scut.GymManager.service.CourseInfoService;



@Service
public class CourseInfoServiceImpl implements CourseInfoService {

	private Logger log=LoggerFactory.getLogger(getClass());

	@Resource
	private CourseInfoMapper courseInfoMapper;

	@Resource
	private JwtUtil jwtUtil;

	@Resource
	private HttpServletRequest httpServletRequest;

	@Override
	public void createCourse(CourseRequest courseRequest) throws CrudException {

		String identity = jwtUtil.extractIdentitySubject(this.httpServletRequest);

		if (!identity.equals("Coach"))
			throw new CrudException("非教练不能创建课程");

		CourseInfo courseInfo=CourseInfo.builder()
				.CourseName(courseRequest.getCourseName())
				.CourseTime(courseRequest.getCourseTime())
				.MaxNumber(courseRequest.getMaxNumber())
				.Classroom(courseRequest.getClassroom())
				.coachId(courseRequest.getCoachId())
				.studentNum(courseRequest.getStudentNum())
				.build();
		int x=courseInfoMapper.insert(courseInfo);
		if(x==0) throw new CrudException("insert 出错");
	}

	@Override
	public void updateCourse(CourseInfo courseInfo) throws CrudException {

		String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

		if (!courseInfo.getCoachId().equals(uid))
			throw new CrudException("你没有该操作权限");

		int x=courseInfoMapper.updateById(courseInfo);
		if(x==0) throw new CrudException("update 出错");
	}

	@Override
	public void deleteCourse(String CourseId) throws CrudException {

		String uid = jwtUtil.extractUidSubject(this.httpServletRequest);

		if (!uid.equals("1"))
			throw new CrudException("你没有操作权限");

		int x=courseInfoMapper.deleteById(CourseId);
		if(x==0) throw new CrudException("delete 出错");
	}

	@Override
	public List<CourseInfo> viewTable()  {
		
		return courseInfoMapper.selectList(null);
	}

	@Override
	public List<CourseInfo> viewCoachCourseTable(String coachId) {

		return courseInfoMapper.searchCoachList(coachId);
	}

	@Override
	public List<CourseInfo> viewVIPCourseTable(String vipId) {
		return courseInfoMapper.searchVIPList(vipId);
	}

}
