package com.scut.GymManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class GymManagerApplication {

	public static void main(String[] args) {SpringApplication.run(GymManagerApplication.class, args); }

}
