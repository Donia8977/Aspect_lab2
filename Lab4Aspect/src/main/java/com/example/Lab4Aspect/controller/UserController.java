package com.example.Lab4Aspect.controller;

import com.example.Lab4Aspect.model.User;
import com.example.Lab4Aspect.repository.UserRepository;
import com.example.Lab4Aspect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setAge(updatedUser.getAge());

        User saved = userRepository.save(user);

        service.evictUserCache(id);

        return saved;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        service.evictUserCache(id);
        return ResponseEntity.ok().build();
    }



//    @GetMapping("/limited")
//    public String rateLimitTest() {
//        return service.limitedService();
//    }

    @GetMapping("/users/{id}/limited")
    public String limitedByUser(@PathVariable Long id) {
        return service.limitedService(id);
    }


    @GetMapping("/locked")
    public String testLock() throws InterruptedException {
        return service.lockedService();
    }
}

