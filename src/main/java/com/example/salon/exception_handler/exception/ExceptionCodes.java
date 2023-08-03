package com.example.salon.exception_handler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCodes implements ExceptionErrorCode {

    USER_NOT_FOUND("user_not_found"),
    NOT_FOUND("not_found"),
    WRONG_DATA("wrong_data"),
    INCORRECT_PASSWORD_CONFIRMED("incorrect_password_confirmed"),
    INVALID_TOKEN("invalid_token"),
    ENTITY_IS_ALREADY_EXISTS("entity_is_already_exists"),
    ENTITY_IS_ALREADY_DELETED("entity_is_already_deleted");

    private final String code;
}
