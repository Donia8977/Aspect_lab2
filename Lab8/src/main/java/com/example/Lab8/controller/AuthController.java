package com.example.Lab8.controller;
import com.example.Lab8.dto.JwtResponse;
import com.example.Lab8.dto.LoginRequest;
import com.example.Lab8.dto.RegisterRequest;
import com.example.Lab8.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(
             @RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
             @RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }
}

