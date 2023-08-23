package com.example.salon.model.enums.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserRegisterDto {
    @NotBlank(message = "login should not be empty")
    private String login;
    @NotBlank(message = "password should not be empty")
    private String password;
    @NotBlank(message = "passwordConfirmation should not be empty")
    private String passwordConfirmation;
    @Email(message = "INCORRECT email")
    @NotBlank(message = "email should not be empty")
    private String email;
}
