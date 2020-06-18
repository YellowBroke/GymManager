package com.scut.GymManager.service;

import java.util.List;

import com.scut.GymManager.dto.CourseRequest;
import com.scut.GymManager.entity.CourseInfo;
import com.scut.GymManager.exception.CrudException;


public interface CourseInfoService {
   
	void createCourse(CourseRequest courseRequest) throws CrudException;
	
	void updateCourse(CourseInfo courseInfo) throws CrudException;
	
	void deleteCourse(String CourseId) throws CrudException;
	
	List<CourseInfo> viewTable() throws CrudException;
	
	List<CourseInfo> viewCoachCourseTable(String coachId);
	
	List<CourseInfo> viewVIPCourseTable(String VIPId);
}
