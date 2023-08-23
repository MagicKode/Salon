package com.example.salon.exception_handler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class ValidationExceptionInfo {
    private static final String DEFAULT_MESSAGE = "Поле '%s' неверно! Пожалуйста, проверить правильность введенной информации.";
    public static final Boolean DEFAULT_IS_FORMAT = true;
    public static final String DEFAULT_CLIENT_CODE = "VALIDATION_ERROR";
    public static final int DEFAULT_HTTP_CODE = 400;

    @Min(100)
    @Max(599)
    private Integer httpCode;
    private String clientCode;
    private boolean isFormat;

    @NotBlank
    private String message;

    public static ValidationExceptionInfo defaultObj() {
        return ValidationExceptionInfo.builder()
                .message(DEFAULT_MESSAGE)
                .isFormat(DEFAULT_IS_FORMAT)
                .clientCode(DEFAULT_CLIENT_CODE)
                .httpCode(DEFAULT_HTTP_CODE)
                .build();
    }
}
