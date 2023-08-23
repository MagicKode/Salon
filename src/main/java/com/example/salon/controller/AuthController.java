package com.example.salon.controller;

import com.example.salon.model.enums.auth.TokenResponse;
import com.example.salon.model.enums.auth.UserAuthDto;
import com.example.salon.model.enums.auth.UserRegisterDto;
import com.example.salon.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    public static final String AUTH_URL = "/auth";
    public static final String REGISTER_URL = "/register";

    private final AuthService authService;

    @PostMapping(AUTH_URL)
    public TokenResponse auth(@Valid @RequestBody UserAuthDto userAuthDto) {
        return authService.auth(userAuthDto);
    }

    @PostMapping(REGISTER_URL)
    public void register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        authService.register(userRegisterDto);
    }
}
