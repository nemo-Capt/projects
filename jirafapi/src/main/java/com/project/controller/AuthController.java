package com.project.controller;

import com.project.entity.User;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.security.TokenProvider;
import com.project.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService service, TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.service = service;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public void registration(@RequestBody RegistrationUserDTO registrationUserDTO) {
        User user = new User();
        user.setUsername(registrationUserDTO.getUsername());
        user.setEmail(registrationUserDTO.getEmail());
        user.setPassword(registrationUserDTO.getPassword());

        service.register(registrationUserDTO);
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
