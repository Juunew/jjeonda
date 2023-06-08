package com.fintech.jjeondaproject.common.constant.errorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BookError {

    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 가계부 입니다.");

    private final HttpStatus status;
    private final String message;
}
