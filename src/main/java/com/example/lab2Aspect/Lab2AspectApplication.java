package com.example.lab2Aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Lab2AspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab2AspectApplication.class, args);
	}

}
