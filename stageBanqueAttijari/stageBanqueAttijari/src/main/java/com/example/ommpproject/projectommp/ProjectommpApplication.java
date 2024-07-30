package com.example.ommpproject.projectommp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectommpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectommpApplication.class, args);
	}

}
