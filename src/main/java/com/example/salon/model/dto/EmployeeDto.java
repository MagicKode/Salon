package com.example.salon.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private String id;
    @NotBlank(message = "firstName should not be empty")
    private String firstName;
    @NotBlank(message = "secondName should not be empty")
    private String lastName;
    @NotBlank(message = "login should not be empty")
    private String login;
    @NotBlank(message = "password should not be empty")
    private String password;
    @NotBlank(message = "phone should not be empty")
    private String phone;
    @NotBlank(message = "Email should not be empty")
    private String email;
}
