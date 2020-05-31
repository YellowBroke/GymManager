package com.scut.GymManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GymManagerApplication {

	public static void main(String[] args) {SpringApplication.run(GymManagerApplication.class, args); }

}
