package com.example.Lab4Aspect.aspect;

import com.example.Lab4Aspect.annotation.RateLimit;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component

public class RateLimiterAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    public RateLimiterAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Around("@annotation(rateLimit)")
    public Object enforceRateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        String userId = args.length > 0 ? args[0].toString() : "global";

        String key = "rate-limit:" + methodName + ":" + userId;

        Long currentCount = redisTemplate.opsForValue().increment(key);

        if (currentCount == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(rateLimit.timeWindowSeconds()));
        }

        if (currentCount != null && currentCount > rateLimit.maxRequests()) {
            throw new RuntimeException("Rate limit exceeded. Try again later.");
        }

        return joinPoint.proceed();
    }
}

