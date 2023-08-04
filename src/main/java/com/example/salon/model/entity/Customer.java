package com.example.salon.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {
    @Id
    private String id;
    @NotBlank(message = "FirstName should not be empty")
    private String firstName;
    @NotBlank(message = "lastName should not be empty")
    private String lastName;
    @NotBlank(message = "phone should not be empty")
    private String phone;
    @NotBlank(message = "login should not be empty")
    private String login;
    @NotBlank(message = "password should not be empty")
    private String password;
    @NotBlank(message = "email should not be empty")
    private String email;
    @NotBlank(message = "createdAt should not be empty")
    private Date createdAt;
    @NotBlank(message = "lastModified should not be empty")
    private Date lastModified;
    private User user;

    //todo ссылка на user
}
