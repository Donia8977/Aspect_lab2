package com.example.lab2Aspect.Aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Parameter;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.lab2Aspect.controllers.ProductController.*(..))")
    public void logRequestBody(JoinPoint joinPoint) {
        logger.info("LoggingAspect triggered for method: {}", joinPoint.getSignature());

        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();


        for (int i = 0; i < args.length; i++) {
            if (parameters[i].isAnnotationPresent(RequestBody.class)) {
                logger.info("The Request Body: {}", args[i]);
            }
        }
    }
}
