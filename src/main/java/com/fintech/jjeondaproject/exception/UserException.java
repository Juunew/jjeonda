package com.fintech.jjeondaproject.exception;

import com.fintech.jjeondaproject.common.constant.errorType.UserError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserException extends RuntimeException{

    private UserError userError;
    private String message;

    public UserException(UserError userError) {
        this.userError = userError;
        this.message = userError.getMessage();
    }
}
