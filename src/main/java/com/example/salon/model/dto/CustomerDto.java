package com.example.salon.model.dto;

import com.example.salon.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String phone;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
