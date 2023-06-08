package com.fintech.jjeondaproject.exception;

import com.fintech.jjeondaproject.common.response.ResBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = UserException.class)
    public ResBody<?> userExceptionHandler(UserException e) {
        log.info("User Error = {}", e.toString());
        return ResBody.error(e.getMessage());
    }

    @ExceptionHandler(value = BookException.class)
    public ResBody<?> bookExceptionHandler(BookException e) {
        log.info("Book Error = {}", e.toString());
        return ResBody.error(e.getMessage());
    }
}
