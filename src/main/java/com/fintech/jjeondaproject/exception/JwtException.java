package com.fintech.jjeondaproject.exception;

import com.fintech.jjeondaproject.common.constant.errorType.JwtError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtException extends RuntimeException{

    private JwtError jwtError;
    private String message;

    public JwtException(JwtError jwtError) {
        this.jwtError = jwtError;
        this.message = jwtError.getMessage();
    }
}
