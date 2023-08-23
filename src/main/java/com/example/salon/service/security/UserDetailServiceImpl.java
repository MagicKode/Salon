package com.example.salon.service.security;

import com.example.salon.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final AuthService authService;

        @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return authService.findByLogin(username);
    }
}
