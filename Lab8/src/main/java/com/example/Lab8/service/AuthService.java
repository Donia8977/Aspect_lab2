package com.example.Lab8.service;



import com.example.Lab8.dto.JwtResponse;
import com.example.Lab8.dto.LoginRequest;
import com.example.Lab8.dto.RegisterRequest;
import com.example.Lab8.entity.User;
import com.example.Lab8.repository.UserRepository;
import com.example.Lab8.security.JwtUtil;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authMgr;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthService(AuthenticationManager authMgr, JwtUtil jwtUtil,
                       UserRepository userRepo,
                       PasswordEncoder encoder) {
        this.authMgr = authMgr;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public ResponseEntity<JwtResponse> loginUser(LoginRequest req) {
        Authentication auth = authMgr.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(), req.getPassword()));
        String token = jwtUtil.generateJwtToken(auth);
        User user = (User) auth.getPrincipal();
        JwtResponse body = new JwtResponse(token, user.getId(),
                user.getUsername());
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .path("/")
                .maxAge(3600)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(body);
    }

    public ResponseEntity<?> registerUser(RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username taken");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(encoder.encode(req.getPassword()));
        userRepo.save(u);
        return ResponseEntity.ok("User registered");
    }
}

