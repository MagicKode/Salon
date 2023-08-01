package com.example.salon.service.security;

import com.example.salon.exception_handler.exception.BusinessException;
import com.example.salon.exception_handler.exception.ExceptionCodes;
import com.example.salon.jwt.JwtProvider;
import com.example.salon.model.entity.User;
import com.example.salon.model.enums.Role;
import com.example.salon.model.enums.auth.AuthDto;
import com.example.salon.model.enums.auth.CustomerRegisterDto;
import com.example.salon.model.enums.auth.TokenResponse;
import com.example.salon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public TokenResponse auth(AuthDto authDto) {
        final User user = findByLoginAndPassword(authDto.getLogin(), authDto.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return TokenResponse.builder()
                .token(token)
                .build();
    }

    public void register(CustomerRegisterDto customerRegisterDto) {
        if (!customerRegisterDto.getPassword().equals(customerRegisterDto.getPasswordConfirmation())) {
            throw BusinessException.builder()
                    .code(ExceptionCodes.INCORRECT_PASSWORD_CONFIRMED)
                    .build();
        }
        final User user = User.builder()
                .login(customerRegisterDto.getLogin())
                .email(customerRegisterDto.getEmail())
                .password(passwordEncoder.encode(customerRegisterDto.getPassword()))
                .roles(Set.of(Role.EMPLOYEE))
                .build();
    }

    public User findByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }


    public User findByLoginAndPassword(String login, String password) {
        return Optional.ofNullable(userRepository.findUserByLogin(login))
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> BusinessException.builder()
                        .code(ExceptionCodes.USER_NOT_FOUND)
                        .build());
    }
}
