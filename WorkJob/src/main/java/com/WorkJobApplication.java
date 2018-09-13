package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.example.workjob.dao")
public class WorkJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkJobApplication.class, args);
	}
	
	
}
