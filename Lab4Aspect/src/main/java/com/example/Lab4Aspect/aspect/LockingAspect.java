package com.example.Lab4Aspect.aspect;


import com.example.Lab4Aspect.annotation.WithLock;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component

public class LockingAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    public LockingAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Around("@annotation(withLock)")
    public Object applyLock(ProceedingJoinPoint joinPoint, WithLock withLock) throws Throwable {
        String key = "lock:" + withLock.key();

        Boolean acquired = redisTemplate.opsForValue()
                .setIfAbsent(key, "LOCKED", Duration.ofSeconds(withLock.timeoutSeconds()));

        if (Boolean.TRUE.equals(acquired)) {
            try {
                return joinPoint.proceed();
            } finally {
                redisTemplate.delete(key);
            }
        } else {
            throw new RuntimeException("Resource is currently locked. Please try again later.");
        }
    }
}

