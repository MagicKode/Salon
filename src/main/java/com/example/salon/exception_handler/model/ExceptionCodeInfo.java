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
public class ExceptionCodeInfo {
    public static final String DEFAULT_CLIENT_CODE = "INTERNAL_ERROR";
    public static final String DEFAULT_MESSAGE = "Internal service error";
    public static final Integer DEFAULT_HTTP_CODE = 500;


    @Min(100)
    @Max(599)
    private Integer httpCode;
    private String clientCode;
    private boolean isFormat;

    @NotBlank
    private String message;

    public static ExceptionCodeInfo defaultObj() {
        return ExceptionCodeInfo.builder()
                .httpCode(DEFAULT_HTTP_CODE)
                .clientCode(DEFAULT_CLIENT_CODE)
                .message(DEFAULT_MESSAGE)
                .build();
    }
}
