package com.smv.onlineBankingAPI.web.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceResponse {
    BigDecimal balance;

    public BalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }
}
