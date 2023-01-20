package com.smv.onlineBankingAPI.web.response;

import lombok.Data;

@Data
public class FailedResponse {
    private int statusCode;
    private String message;

    public FailedResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
