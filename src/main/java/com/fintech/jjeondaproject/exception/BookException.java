package com.fintech.jjeondaproject.exception;

import com.fintech.jjeondaproject.common.constant.errorType.BookError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookException extends RuntimeException{

    private BookError bookError;
    private String message;

    public BookException(BookError bookError) {
        this.bookError = bookError;
        this.message = bookError.getMessage();
    }
}
