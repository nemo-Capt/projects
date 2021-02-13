package com.project.controller;

import com.project.controller.emailvalidation.EmailValidation;
import com.project.entity.User;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.EditDTO;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.security.TokenProvider;
import com.project.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailValidation emailValidation;

    @Autowired
    public AuthController(UserService service, TokenProvider tokenProvider, AuthenticationManager authenticationManager, EmailValidation emailValidation) {
        this.service = service;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.emailValidation = emailValidation;
    }


    @PostMapping("/signup")
    public ResponseEntity registration(@RequestBody RegistrationUserDTO registrationUserDTO) {
        User user = new User();

        try {
            user.setUsername(registrationUserDTO.getUsername());
            user.setEmail(registrationUserDTO.getEmail());
            user.setPassword(registrationUserDTO.getPassword());
            if (!emailValidation.validate(registrationUserDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Email");
            }
            if (registrationUserDTO.getPassword().length() < 4) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password must be at least 4 characters long");
            }
        } catch (NullPointerException e2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Empty Field");
        }

        try {
            service.register(registrationUserDTO);
        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists");
        }

        return new ResponseEntity<>(null, HttpStatus.OK);

    }


    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> login(@RequestBody SigninRequest signInRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getUsername(),
                            signInRequest.getPassword()
                    )
            );


            String token = tokenProvider.generateToken(authentication);
            User user = service.getUserByName(signInRequest.getUsername());
            return new ResponseEntity<>(new TokenResponse(
                    token,
                    user.getUsername(),
                    user.getRole(),
                    user.getId()
            ), HttpStatus.OK);
        } catch (LockedException e) {
            return new ResponseEntity<>(null, HttpStatus.resolve(510));
        }
    }

}
