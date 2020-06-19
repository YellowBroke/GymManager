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

	@Select("SELECT CourseInfo.Course_id,CourseInfo.Course_name,CourseInfo.Course_times,CourseInfo.Max_number,CourseInfo.Classroom,CourseInfo.Time"
			+ " FROM CourseInfo,Teaches WHERE Teaches.Coach_id=#{UserId} and Teaches.Course_id=CourseInfo.Course_id")
	List<CourseInfo> searchCoachList(@Param("UserId") String userId); 
	
	@Select("SELECT CourseInfo.Course_id,CourseInfo.Course_name,CourseInfo.Course_times,CourseInfo.Max_number,CourseInfo.Classroom,CourseInfo.Time"
			+ " FROM CourseInfo,Takes WHERE Takes.VIP_id=#{UserId} and Takes.Course_id=CourseInfo.Course_id")
	List<CourseInfo> searchVIPList(@Param("UserId") String userId);
}
