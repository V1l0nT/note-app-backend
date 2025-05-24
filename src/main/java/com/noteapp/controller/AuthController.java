package com.noteapp.controller;

import com.noteapp.dto.AuthRequest;
import com.noteapp.dto.AuthResponse;
import com.noteapp.model.User;
import com.noteapp.security.JwtUtils;
import com.noteapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);
        
        return ResponseEntity.ok(new AuthResponse(
            token,
            userDetails.getUsername(),
            userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toArray(String[]::new)
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        User user = userService.createUser(request.getUsername(), request.getPassword());
        
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        
        return ResponseEntity.ok(new AuthResponse(
            token,
            user.getUsername(),
            user.getRoles().toArray(String[]::new)
        ));
    }
} 