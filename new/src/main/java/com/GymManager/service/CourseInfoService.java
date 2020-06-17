package com.GymManager.service;

import java.util.List;

import com.GymManager.entity.CourseInfo;
import com.GymManager.exception.CrudException;


public interface CourseInfoService {
   
	void createCourse(CourseInfo courseInfo) throws CrudException;
	
	void updateCourse(CourseInfo courseInfo) throws CrudException;
	
	void deleteCourse(String CourseId) throws CrudException;
	
	List<CourseInfo> viewTable() throws CrudException;
	
	List<CourseInfo> viewCoachCourseTable(String coachId);
	
	List<CourseInfo> viewVIPCourseTable(String VIPId);
}
