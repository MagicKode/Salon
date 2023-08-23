package com.example.salon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
public class Employee {
    @Id
    private String id;
    @NotBlank(message = "firstName should not be empty")
    private String firstName;
    @NotBlank(message = "lastName should not be empty")
    private String lastName;
    @NotBlank(message = "login should not be empty")
    private String login;
    @NotBlank(message = "password should not be empty")
    private String password;
    @NotBlank(message = "phone should not be empty")
    private String phone;
    @NotBlank(message = "email should not be empty")
    private String email;
    @NotBlank(message = "createdAt should not be empty")
    private Date createdAt;
    @NotBlank(message = "updatedAt should not be empty")
    private Date updatedAt;
    private User user;
}

// todo связать сущности между собой с помощью добавления таблицы assignments параметрами : Ключа и время записи, как доп поле.

// add new entity Assigmence