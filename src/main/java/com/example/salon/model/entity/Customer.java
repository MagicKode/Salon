package com.example.salon.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

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

    @CreatedDate
    private DateTime createdAt;
    @LastModifiedDate
    private DateTime lastModified;

    //todo ссылка на user
}
