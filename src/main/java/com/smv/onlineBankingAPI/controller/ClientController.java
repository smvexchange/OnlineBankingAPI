package com.smv.onlineBankingAPI.controller;

import com.smv.onlineBankingAPI.service.AccountTransactions;
import com.smv.onlineBankingAPI.web.response.BalanceResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ClientController {

    private final AccountTransactions accountTransactions;

    public ClientController(AccountTransactions accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    @GetMapping(value = "/getBalance",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public BalanceResponse getBalance(@RequestParam(name = "id") Long clientId) {
        return accountTransactions.getBalance(clientId);
    }
}
