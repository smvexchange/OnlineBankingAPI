package com.smv.onlineBankingAPI.web.exception;

import lombok.Getter;

@Getter
public class NotEnoughMoneyException extends RuntimeException {
    private final int statusCode;

    public NotEnoughMoneyException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
