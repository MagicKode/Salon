package com.example.salon.model.dto;

import com.example.salon.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String id;
    private String login;
    private String password;
    private String email;
    private Set<Role> roles;
}
