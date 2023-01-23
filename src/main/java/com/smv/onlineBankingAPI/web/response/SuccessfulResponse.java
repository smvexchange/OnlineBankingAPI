package com.smv.onlineBankingAPI.web.response;

import lombok.Data;

@Data
public class SuccessfulResponse {
    private int statusCode;

    public SuccessfulResponse(int statusCode) {
        this.statusCode = statusCode;
    }
}
