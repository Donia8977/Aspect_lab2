package com.example.Lab4Aspect.service;

import com.example.Lab4Aspect.annotation.RateLimit;
import com.example.Lab4Aspect.annotation.WithLock;
import com.example.Lab4Aspect.model.User;
import com.example.Lab4Aspect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        System.out.println("Fetching from DB...");
        return userRepository.findById(id).orElseThrow();
    }

    @CacheEvict(value = "users", key = "#id")
    public void evictUserCache(Long id) {
        System.out.println("Cache evicted for user ID " + id);
    }

    @RateLimit(maxRequests = 3, timeWindowSeconds = 60)
    public String limitedService(Long id) {
        return "User " + id + ": Access granted";
    }

    @WithLock(key = "user-lock", timeoutSeconds = 10)
    public String lockedService() throws InterruptedException {
        Thread.sleep(10000);
        return "Finished long-running process";
    }
}

