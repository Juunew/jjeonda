package com.fintech.jjeondaproject.exception;

import com.fintech.jjeondaproject.common.constant.errorType.BankError;

public class BankException extends RuntimeException {
	
	private BankError bankError;
    private String message;

    public BankException(BankError bankError) {
        this.bankError = bankError;
        this.message = bankError.getMessage();
    }
}	


