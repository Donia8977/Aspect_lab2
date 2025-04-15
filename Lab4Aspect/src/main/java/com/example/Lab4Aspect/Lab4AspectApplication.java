package com.example.Lab4Aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
public class Lab4AspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab4AspectApplication.class, args);
	}

}
