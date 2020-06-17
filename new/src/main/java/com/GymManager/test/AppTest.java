package com.GymManager.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.GymManager.entity.CourseInfo;
import com.GymManager.exception.CrudException;
import com.GymManager.service.CourseInfoService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest 
{
    @Autowired
    private CourseInfoService c;
    
  //  @Test
    public void test1() throws CrudException
    {
    	Date date=new Date();
    	CourseInfo courseInfo=new CourseInfo("22345","数据结构",10,50,"203",date);
    	c.createCourse(courseInfo);
    }
 //   @Test
    public void test2() throws CrudException
    {
    	c.deleteCourse("22345");
    }
//   @Test
    public void test3() throws CrudException
    {
    	Date date=new Date();
    	CourseInfo courseInfo=new CourseInfo("22345","数据结构",10,50,"203",date);
    	c.updateCourse(courseInfo);
    }
  //  @Test
    public void test4() throws CrudException
    {
    	List<CourseInfo> l=c.viewTable();
    	for(CourseInfo ss:l) {
    	System.out.println(ss.getDate().toString());
    	}
    }
 //   @Test
    public void test5()
    {
    	List<CourseInfo> l=c.viewCoachCourseTable("2");
    	for(CourseInfo ss:l) {
        	System.out.println(ss.getCourseName().toString());
        	}
    }
 //   @Test
    public void test6()
    {
    	List<CourseInfo> l=c.viewVIPCourseTable("1");
    	for(CourseInfo ss:l) {
        	System.out.println(ss.getCourseName().toString());
        	}
    }
}
