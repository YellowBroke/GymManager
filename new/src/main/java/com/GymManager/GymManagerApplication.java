package com.GymManager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.GymManager.dao"})
public class GymManagerApplication {
	public static void main(String[] args) {SpringApplication.run(GymManagerApplication.class, args); }
}
