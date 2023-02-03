package com.smv.onlineBankingAPI.web.exception;

import lombok.Getter;

@Getter
public class IllegalParameterException extends RuntimeException{
    private final int statusCode;

    public IllegalParameterException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
