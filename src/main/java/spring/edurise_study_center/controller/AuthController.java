package spring.edurise_study_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.edurise_study_center.DTO.response.LoginRequest;
import spring.edurise_study_center.service.auth.AuthService;
import spring.edurise_study_center.service.jwt.JwtService;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired

    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        // Authenticate using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtService.generateToken(loginRequest.getUsername());

        return ResponseEntity.ok(token);
    }


}
