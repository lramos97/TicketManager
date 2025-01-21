package com.larissa.springSecurity_with_jwt.service;

import com.larissa.springSecurity_with_jwt.dto.AuthenticationDTO;
import com.larissa.springSecurity_with_jwt.dto.RegisterUserDTO;
import com.larissa.springSecurity_with_jwt.entity.User;
import com.larissa.springSecurity_with_jwt.exceptions.UserAlreadyExistsException;
import com.larissa.springSecurity_with_jwt.repository.UserRepository;
import com.larissa.springSecurity_with_jwt.security.authentication.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(AuthenticationDTO data){
        System.out.println("Trying to authenticate the user: " + data.login());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("\n" + "Successful authentication for: " + data.login());
        return jwtTokenService.generateToken((User) auth.getPrincipal());
    }

    public void register(RegisterUserDTO data) {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new UserAlreadyExistsException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        userRepository.save(newUser);
    }
}

