package com.smv.onlineBankingAPI.web.exception;

import lombok.Getter;

@Getter
public class NoSuchAccountException extends RuntimeException {
    private final int statusCode;

    public NoSuchAccountException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
