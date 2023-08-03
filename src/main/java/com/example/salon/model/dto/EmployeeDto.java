package com.example.salon.model.dto;

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
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String phone;
    private String email;
}
