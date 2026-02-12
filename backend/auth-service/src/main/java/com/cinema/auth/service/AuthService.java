package com.cinema.auth.service;

import com.cinema.auth.dto.AuthResponse;
import com.cinema.auth.dto.LoginRequest;
import com.cinema.auth.dto.RegisterRequest;
import com.cinema.auth.entity.User;
import com.cinema.auth.repository.UserRepository;
import com.cinema.common.security.jwt.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        
        user = userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getEmail());
        
        return new AuthResponse(token, user.getEmail(), user.getFullName(), user.getRole().name());
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        String token = jwtUtil.generateToken(user.getEmail());
        
        return new AuthResponse(token, user.getEmail(), user.getFullName(), user.getRole().name());
    }
}
