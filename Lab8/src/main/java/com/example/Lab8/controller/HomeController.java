package com.example.Lab8.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class HomeController {

    @GetMapping("/all")
    public ResponseEntity<String> allAccess() {
        return ResponseEntity.ok("Public content");
    }

    @GetMapping("/hello")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("Hello from protected endpoint");
    }
}

