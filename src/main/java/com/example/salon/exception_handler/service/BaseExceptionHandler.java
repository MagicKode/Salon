package com.example.salon.exception_handler.service;

import com.example.salon.exception_handler.exception.AbstractException;
import com.example.salon.exception_handler.model.ExceptionResponse;
import com.example.salon.exception_handler.model.FieldViolationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BaseExceptionHandler {

    @Autowired
    private ExceptionResponseMapper exceptionResponseMapper;

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<ExceptionResponse> handleThrowable(Exception exception) {
        UUID uuid = UUID.randomUUID();
        log.error("Exception occurred ({}): {}", uuid, exception.getMessage(), exception);
        return exceptionResponseMapper.handleUnknownException(exception, uuid);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponse> handleValidationError(MethodArgumentNotValidException exception) {
        UUID uuid = UUID.randomUUID();
        log.error("Constraint validation exception ({}): {}", uuid, exception.getLocalizedMessage(), exception);
        final ObjectError globalError = exception.getBindingResult().getGlobalError();
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        if (fieldErrors.isEmpty() && globalError == null) {
            return exceptionResponseMapper.handleUnknownException(exception, uuid);
        } else if (globalError != null) {
            final String messageCode = globalError.getDefaultMessage();
            return exceptionResponseMapper.handleValidationGlobalException(uuid, messageCode);
        }

        List<FieldViolationResponse> fieldViolations = fieldErrors.stream()
                .map(e -> exceptionResponseMapper.createFieldViolationResponse(e.getField(), e.getDefaultMessage()))
                .toList();

        return exceptionResponseMapper.handleValidationException(uuid, fieldViolations);
    }

    @ExceptionHandler(value = {AbstractException.class})
    public ResponseEntity<ExceptionResponse> handleBusinessErrors(AbstractException exception) {
        UUID uuid = UUID.randomUUID();
        ResponseEntity<ExceptionResponse> responseEntity = exceptionResponseMapper.handleException(
                uuid, exception.getErrorCode(), exception.getArgs());
        String message = Optional.ofNullable(responseEntity.getBody()).orElse(new ExceptionResponse()).getMessage();
        log.error("Business exception ({}): {}, {}", uuid, exception.getMessage(), message, exception);
        return responseEntity;
    }
}
