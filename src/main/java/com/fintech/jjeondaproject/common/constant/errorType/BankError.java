package com.fintech.jjeondaproject.common.constant.errorType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BankError {

    BANK_NOT_FOUND(HttpStatus.NOT_FOUND, "게좌를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
