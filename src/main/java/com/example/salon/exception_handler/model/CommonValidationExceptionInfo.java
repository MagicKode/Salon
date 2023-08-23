package com.example.salon.exception_handler.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class CommonValidationExceptionInfo {
    public static final String DEFAULT_MESSAGE = "Some fields have a validation error. Details in the 'fieldViolations' list.";
    public static final String DEFAULT_CLIENT_CODE = "VALIDATION_ERROR";
    public static final int DEFAULT_HTTP_CODE = 400;

    @Min(100)
    @Max(599)
    private Integer httpCode;
    private String clientCode;
    private boolean isFormat;

    @NotBlank
    private String message;

    public static CommonValidationExceptionInfo defaultObj() {
        return CommonValidationExceptionInfo.builder()
                .message(DEFAULT_MESSAGE)
                .clientCode(DEFAULT_CLIENT_CODE)
                .httpCode(DEFAULT_HTTP_CODE)
                .build();
    }
}
