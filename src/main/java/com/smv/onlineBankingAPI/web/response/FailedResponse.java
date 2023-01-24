package com.smv.onlineBankingAPI.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FailedResponse extends BaseResponse {
    private String errorMessage;

    public FailedResponse(String errorMessage) {
        super(0);
        this.errorMessage = errorMessage;
    }

    public FailedResponse(int statusCode, String errorMessage) {
        super(statusCode);
        this.errorMessage = errorMessage;
    }

}
