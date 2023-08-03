package com.example.salon.exception_handler.service;

import com.example.salon.exception_handler.configuration.ExceptionConfigurationProperties;
import com.example.salon.exception_handler.exception.ExceptionCodes;
import com.example.salon.exception_handler.exception.ValidationCodes;
import com.example.salon.exception_handler.model.CommonValidationExceptionInfo;
import com.example.salon.exception_handler.model.ExceptionCodeInfo;
import com.example.salon.exception_handler.model.ExceptionResponse;
import com.example.salon.exception_handler.model.FieldViolationResponse;
import com.example.salon.exception_handler.model.ValidationExceptionInfo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExceptionResponseMapper {

    private final ExceptionConfigurationProperties exceptionConfigurationProperties;

    public ResponseEntity<ExceptionResponse> handleUnknownException(Exception exception, UUID uuid) {
        ExceptionCodeInfo codeInfo = exceptionConfigurationProperties.getClassExceptionMap()
                .getOrDefault(exception.getClass(), exceptionConfigurationProperties.getDefaultExceptionConfiguration());

        return convert(codeInfo.getMessage(), codeInfo.getClientCode(), codeInfo.getHttpCode(), uuid);
    }

    public ResponseEntity<ExceptionResponse> handleValidationGlobalException(UUID uuid, String code) {
        final ValidationExceptionInfo errorInfo = exceptionConfigurationProperties.getValidationInfoConfiguration(code);
        return convert(errorInfo.getMessage(), errorInfo.getClientCode(), errorInfo.getHttpCode(), uuid);
    }

    public ResponseEntity<ExceptionResponse> handleValidationException(UUID uuid, List<FieldViolationResponse> list) {
        final CommonValidationExceptionInfo errorInfo = exceptionConfigurationProperties.getDefaultCommonValidationExceptionConfiguration();
        return convert(errorInfo.getMessage(), errorInfo.getClientCode(), errorInfo.getHttpCode(), uuid, list);
    }

    public ResponseEntity<ExceptionResponse> handleException(UUID uuid, String code, List<Object> args) {
        final ExceptionCodeInfo codeInfo = exceptionConfigurationProperties.getConfiguration(code);

        final String outputMessage = codeInfo.isFormat()
                ? String.format(codeInfo.getMessage(), args.toArray())
                : codeInfo.getMessage();

        return convert(outputMessage, codeInfo.getClientCode(), codeInfo.getHttpCode(), uuid);
    }

    public ResponseEntity<ExceptionResponse> handleUniqueConstraintException(UUID uuid, @NotNull String tableName, @NotNull String constraint) {
        final ExceptionCodeInfo codeInfo = exceptionConfigurationProperties
                .getConfiguration(ExceptionCodes.ENTITY_IS_ALREADY_EXISTS.getCode());
        String entityName = UniqueConstraintDataService.getTableAndEntityNames().get(tableName);
        List<String> constraintFields = UniqueConstraintDataService.getAllUniqueConstraintsFromDb().get(entityName).get(constraint);
        List<FieldViolationResponse> fieldViolationResponses = new ArrayList<>();
        constraintFields.forEach(x -> fieldViolationResponses.add(createFieldViolationResponse(x, ValidationCodes.UNIQUE_FIELD)));
        final String outputMessage = String.format(codeInfo.getMessage(), entityName);
        return convert(outputMessage, codeInfo.getClientCode(), codeInfo.getHttpCode(), uuid, fieldViolationResponses);
    }

    private ResponseEntity<ExceptionResponse> convert(String message, String errorCode, int httpCode, UUID uuid) {
        return convert(message, errorCode, httpCode, uuid, Collections.emptyList());
    }

    private ResponseEntity<ExceptionResponse> convert(String message, String errorCode, int httpCode, UUID uuid, List<FieldViolationResponse> list) {
        ExceptionResponse errorResponse = ExceptionResponse.builder()
                .errorId(uuid.toString())
                .errorCode(errorCode)
                .message(message)
                .fieldViolations(list)
                .build();

        return ResponseEntity.status(httpCode)
                .body(errorResponse);
    }

    public FieldViolationResponse createFieldViolationResponse(String fieldName, String code) {
        final ValidationExceptionInfo errorInfo = exceptionConfigurationProperties.getValidationInfoConfiguration(code);
        return FieldViolationResponse.builder()
                .fieldName(fieldName)
                .errorCode(errorInfo.getClientCode())
                .message(errorInfo.isFormat()
                        ? String.format(errorInfo.getMessage(), fieldName)
                        : errorInfo.getMessage())
                .build();
    }
}
