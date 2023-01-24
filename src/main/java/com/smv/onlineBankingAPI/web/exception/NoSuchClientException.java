package com.smv.onlineBankingAPI.web.exception;

import lombok.Getter;

@Getter
public class NoSuchClientException extends RuntimeException {
    private final int statusCode;

    public NoSuchClientException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
