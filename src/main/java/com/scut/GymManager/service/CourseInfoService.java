package com.scut.GymManager.service;

import java.util.List;

import com.scut.GymManager.dto.CourseRequest;
import com.scut.GymManager.entity.CourseInfo;
import com.scut.GymManager.exception.CrudException;


public interface CourseInfoService {

	/**
	 * 创建课程
	 * @param courseRequest
	 * @throws CrudException
	 */
	void createCourse(CourseRequest courseRequest) throws CrudException;

	/**
	 * 更新课程信息
	 * @param courseInfo
	 * @throws CrudException
	 */
	void updateCourse(CourseInfo courseInfo) throws CrudException;

	/**
	 * 删除课程
	 * @param CourseId
	 * @throws CrudException
	 */
	void deleteCourse(String CourseId) throws CrudException;


	List<CourseInfo> viewTable() throws CrudException;

	/**
	 * 查看课程表（教练）
	 * @param coachId
	 * @return
	 */
	List<CourseInfo> viewCoachCourseTable(String coachId);

	/**
	 * 查看课程表（会员）
	 * @param VIPId
	 * @return
	 */
	List<CourseInfo> viewVIPCourseTable(String VIPId);
}
