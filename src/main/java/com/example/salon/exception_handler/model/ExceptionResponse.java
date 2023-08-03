package com.example.salon.exception_handler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String errorId;
    private String errorCode;
    private String message;
    private List<FieldViolationResponse> fieldViolations;
}
