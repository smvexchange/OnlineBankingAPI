package com.smv.onlineBankingAPI.web.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {
    private int statusCode;

    public BaseResponse(int statusCode) {
        this.statusCode = statusCode;
    }
}
