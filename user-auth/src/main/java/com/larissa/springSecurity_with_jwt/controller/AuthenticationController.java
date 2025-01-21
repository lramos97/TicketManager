package com.larissa.springSecurity_with_jwt.controller;

import com.larissa.springSecurity_with_jwt.dto.AuthenticationDTO;
import com.larissa.springSecurity_with_jwt.dto.TokenResponseDTO;
import com.larissa.springSecurity_with_jwt.dto.RegisterUserDTO;
import com.larissa.springSecurity_with_jwt.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        String token = service.login(data);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterUserDTO data) {
            service.register(data);
            return ResponseEntity.ok().build();
    }
}
