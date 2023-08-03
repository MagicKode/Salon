package com.example.salon.exception_handler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldViolationResponse {

    private String fieldName;
    private String errorCode;
    private String message;

}
