package com.scut.GymManager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.scut.GymManager.entity.CourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@Mapper
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

	//查询教练所教的课程
		@Select("SELECT course_id,course_name,Course_time,Max_number,classroom,student_num,coach_id"
				+ " FROM course_info WHERE coach_id=#{UserId}")
		List<CourseInfo> searchCoachList(@Param("UserId") String userId); 
		//查询用户所选的课程
		@Select("SELECT course_info.course_id,course_info.course_name,course_info.course_time,course_info.Max_number,course_info.Classroom,course_info.student_num,course_info.coach_id"
				+ " FROM course_info,Takes WHERE Takes.VIP_id=#{UserId} and Takes.Course_id=course_info.Course_id")
		List<CourseInfo> searchVIPList(@Param("UserId") String userId);
}
