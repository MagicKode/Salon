package com.example.salon.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Validated
public class CustomerDto {
    private String id;
    @NotBlank(message = "FirstName should not be empty")
    private String firstName;
    @NotBlank(message = "LastName should not be empty")
    private String lastName;
    @NotBlank(message = "Phone should not be empty")
    private String phone;
    @NotBlank(message = "Email should not be empty")
    private String email;
}
