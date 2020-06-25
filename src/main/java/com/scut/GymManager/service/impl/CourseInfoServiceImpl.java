package com.scut.GymManager.service.impl;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.GymManager.dto.CourseTimeRequest;
import com.scut.GymManager.entity.CourseTime;
import com.scut.GymManager.mapper.CourseTimeMapper;
import com.scut.GymManager.utility.JwtUtil;
import com.scut.GymManager.utility.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.scut.GymManager.mapper.CourseInfoMapper;
import com.scut.GymManager.dto.CourseInfoResponse;
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
	private CourseTimeMapper courseTimeMapper;

	@Resource
	private JwtUtil jwtUtil;

	@Resource
	private UUIDUtil uuidUtil;

	@Resource
	private HttpServletRequest httpServletRequest;

	@Override
	public void createCourse(CourseRequest courseRequest) throws CrudException {

		String identity = jwtUtil.extractIdentitySubject(this.httpServletRequest);

		if (!identity.equals("Coach"))
			throw new CrudException("非教练不能创建课程");

		String courseId = uuidUtil.get32UUIDString();

		CourseInfo courseInfo=CourseInfo.builder()
				.CourseId(courseId)
				.CourseName(courseRequest.getCourseName())
				.coachName(courseRequest.getCoachName())
				.CourseTime(courseRequest.getCourseTime())
				.MaxNumber(courseRequest.getMaxNumber())
				.Classroom(courseRequest.getClassroom())
				.coachId(courseRequest.getCoachId())
				.studentNum(courseRequest.getStudentNum())
				.build();
		int x=courseInfoMapper.insert(courseInfo);
		if(x == 0) throw new CrudException("insert 出错");

		//获取课程时间列表
		List<CourseTimeRequest> timeList = courseRequest.getTimeList();

		//插入课程时间
		for (CourseTimeRequest courseTimeRequest : timeList) {
            Time time = new Time(courseTimeRequest.getHour(), courseTimeRequest.getMinute(), courseTimeRequest.getSecond());

            CourseTime courseTime = CourseTime.builder()
                    .courseId(courseId)
                    .day(courseTimeRequest.getDay())
                    .timeSlot(time)
                    .build();

            if (courseTimeMapper.insert(courseTime) != 1)
                throw new CrudException("课程时间插入异常");
        }
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
	public List<CourseInfoResponse> viewTable()  {
		
		List<CourseInfo> courseInfoList = courseInfoMapper.selectList(null);

		List<CourseInfoResponse> courseInfoResponseList = new ArrayList<>();

		for (CourseInfo courseInfo : courseInfoList) {
			QueryWrapper<CourseTime> courseTimeQueryWrapper = new QueryWrapper<CourseTime>().eq("course_id", courseInfo.getCourseId());

			//从数据库中拿到该课程的时间列表
			List<CourseTime> courseTimeList = courseTimeMapper.selectList(courseTimeQueryWrapper);

			List<CourseTimeRequest> courseTimeRequestList = new ArrayList<>();

			//构造返回时间列表
			for (CourseTime courseTime : courseTimeList) {

				CourseTimeRequest courseTimeRequest = CourseTimeRequest.builder()
						.day(courseTime.getDay())
						.hour(courseTime.getTimeSlot().getHours())
						.minute(courseTime.getTimeSlot().getMinutes())
						.second(courseTime.getTimeSlot().getSeconds())
						.build();

				courseTimeRequestList.add(courseTimeRequest);
			}


			CourseInfoResponse courseInfoResponse = CourseInfoResponse.builder()
					.coachId(courseInfo.getCoachId())
					.courseId(courseInfo.getCourseId())
					.coachName(courseInfo.getCoachName())
					.Classroom(courseInfo.getClassroom())
					.CourseName(courseInfo.getCourseName())
					.CourseTime(courseInfo.getCourseTime())
					.MaxNumber(courseInfo.getMaxNumber())
					.studentNum(courseInfo.getStudentNum())
					.timeList(courseTimeRequestList)
					.build();

			courseInfoResponseList.add(courseInfoResponse);
		}

		return courseInfoResponseList;
	}

	@Override
	public List<CourseInfoResponse> viewCoachCourseTable(String coachId) {
        
		List<CourseInfoResponse> lct = new ArrayList<>();
		List<CourseInfo> lc=courseInfoMapper.searchCoachList(coachId);
		List<CourseTime> lt=courseInfoMapper.searchCoachTime(coachId);
		for (CourseInfo x:lc)
		{
			CourseInfoResponse cir=new CourseInfoResponse();
			cir.setCourseId(x.getCourseId());
			cir.setCoachId(x.getCoachId());
			cir.setCoachName(x.getCoachName());
			cir.setCourseName(x.getCourseName());
			cir.setCourseTime(x.getCourseTime());
			cir.setMaxNumber(x.getMaxNumber());
			cir.setStudentNum(x.getStudentNum());
			cir.setClassroom(x.getClassroom());
			List<CourseTimeRequest> l1=new ArrayList<>();
			for(CourseTime y:lt)
			{
				if(x.getCourseId().equals(y.getCourseId()))
				{
					CourseTimeRequest z=new CourseTimeRequest();
					z.setDay(y.getDay());
					z.setHour(y.getTimeSlot().getHours());
					z.setMinute(y.getTimeSlot().getMinutes());
					z.setSecond(y.getTimeSlot().getSeconds());
					l1.add(z);
				}
			}
			cir.setTimeList(l1);
			lct.add(cir);
		}
		return lct;
	}

	@Override
	public List<CourseInfoResponse> viewVIPCourseTable(String vipId) {
		List<CourseInfoResponse> lct=new ArrayList<>();
	List<CourseInfo> lc=courseInfoMapper.searchVIPList(vipId);
	List<CourseTime> lt=courseInfoMapper.searchVIPTime(vipId);
		for(CourseInfo x:lc) {
		CourseInfoResponse cir=new CourseInfoResponse();
		cir.setCourseId(x.getCourseId());
		cir.setCoachId(x.getCoachId());
		cir.setCoachName(x.getCoachName());
		cir.setCourseName(x.getCourseName());
		cir.setCourseTime(x.getCourseTime());
		cir.setMaxNumber(x.getMaxNumber());
		cir.setStudentNum(x.getStudentNum());
		cir.setClassroom(x.getClassroom());
		List<CourseTimeRequest> l1=new ArrayList<>();
		for(CourseTime y:lt)
		{
			if(x.getCourseId().equals(y.getCourseId()))
			{
				CourseTimeRequest z=new CourseTimeRequest();
				z.setDay(y.getDay());
				z.setHour(y.getTimeSlot().getHours());
				z.setMinute(y.getTimeSlot().getMinutes());
				z.setSecond(y.getTimeSlot().getSeconds());
				l1.add(z);
			}
		}
		cir.setTimeList(l1);
		lct.add(cir);
	}
		return lct;
}


	@Override
	public IPage<CourseInfo> getCourseInfoByPage(Long pageNO, Long pageSize) {
		Page<CourseInfo> page = new Page<>(pageNO,pageSize);
		return courseInfoMapper.selectPage(page, null);
	}

	@Override
	public List<CourseInfoResponse> getCourseInfoByName(String courseName) {

		QueryWrapper<CourseInfo> wrapper = new QueryWrapper<CourseInfo>().eq("course_name", courseName);

		List<CourseInfo> list = courseInfoMapper.selectList(wrapper);

		List<CourseInfoResponse> courseInfoResponseList = new ArrayList<>();


		for (CourseInfo courseInfo : list) {

			QueryWrapper<CourseTime> courseTimeQueryWrapper = new QueryWrapper<CourseTime>().eq("course_id", courseInfo.getCourseId());

			//从数据库中拿到该课程的时间列表
			List<CourseTime> courseTimeList = courseTimeMapper.selectList(courseTimeQueryWrapper);

			List<CourseTimeRequest> courseTimeRequestList = new ArrayList<>();

			//构造返回时间列表
			for (CourseTime courseTime : courseTimeList) {

				CourseTimeRequest courseTimeRequest = CourseTimeRequest.builder()
						.day(courseTime.getDay())
						.hour(courseTime.getTimeSlot().getHours())
						.minute(courseTime.getTimeSlot().getMinutes())
						.second(courseTime.getTimeSlot().getSeconds())
						.build();

				courseTimeRequestList.add(courseTimeRequest);
			}


			CourseInfoResponse courseInfoResponse = CourseInfoResponse.builder()
					.coachId(courseInfo.getCoachId())
					.courseId(courseInfo.getCourseId())
					.coachName(courseInfo.getCoachName())
					.Classroom(courseInfo.getClassroom())
					.CourseName(courseInfo.getCourseName())
					.CourseTime(courseInfo.getCourseTime())
					.MaxNumber(courseInfo.getMaxNumber())
					.studentNum(courseInfo.getStudentNum())
					.timeList(courseTimeRequestList)
					.build();

			courseInfoResponseList.add(courseInfoResponse);
		}

		return courseInfoResponseList;
	}
}
