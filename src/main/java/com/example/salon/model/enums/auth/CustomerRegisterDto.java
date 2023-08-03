package com.example.salon.model.enums.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import static com.example.salon.exception_handler.exception.ValidationCodes.INCORRECT_EMAIL;
import static com.example.salon.exception_handler.exception.ValidationCodes.NOT_EMPTY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CustomerRegisterDto {
    @NotBlank(message = NOT_EMPTY)
    private String login;
    @NotBlank(message = NOT_EMPTY)
    private String password;
    @NotBlank(message = NOT_EMPTY)
    private String passwordConfirmation;
    @Email(message = INCORRECT_EMAIL)
    @NotBlank(message = NOT_EMPTY)
    private String email;
}
