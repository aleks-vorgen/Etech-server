package com.etech.controller;

import com.etech.model.dto.AuthenticationRequestDto;
import com.etech.model.User;
import com.etech.model.dto.RegistrationRequestDto;
import com.etech.security.jwt.JwtTokenProvider;
import com.etech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
            );
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getPermission());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("lastname", user.getLastname());
            response.put("firstname", user.getFirstname());
            response.put("middlename", user.getMiddlename());
            response.put("email", user.getEmail());
            response.put("token", token);
            response.put("role", user.getPermission().getPermission());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto requestDto) {
        User regUser = new User();
        regUser.setUsername(requestDto.getUsername());
        regUser.setEmail(requestDto.getEmail());
        regUser.setPassword(requestDto.getPassword());

        return ResponseEntity.ok(userService.register(regUser).getUsername());
    }
}