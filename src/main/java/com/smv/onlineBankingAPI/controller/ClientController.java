package com.smv.onlineBankingAPI.controller;

import com.smv.onlineBankingAPI.model.Operation;
import com.smv.onlineBankingAPI.service.AccountTransactions;
import com.smv.onlineBankingAPI.web.exception.NoSuchClientException;
import com.smv.onlineBankingAPI.web.response.BalanceResponse;
import com.smv.onlineBankingAPI.web.response.BaseResponse;
import com.smv.onlineBankingAPI.web.response.FailedResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

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

    @GetMapping(value = "/takeMoney",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse takeMoney(@RequestParam(name = "id") Long clientId,
                                        @RequestParam(name = "cash") BigDecimal cash) {
        return accountTransactions.takeMoney(clientId, cash);
    }

    @GetMapping(value = "/putMoney",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse putMoney(@RequestParam(name = "id") Long clientId,
                                 @RequestParam(name = "cash") BigDecimal cash) {
        return accountTransactions.putMoney(clientId, cash);
    }

    @GetMapping(value = "/getOperationList",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Operation> getOperationList(@RequestParam(name = "id")
                                               Long clientId,
                                           @RequestParam(name = "startDate", required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                   fallbackPatterns = {"yyyy-MM-dd HH:mm:ss.SSS"})
                                               LocalDateTime startDate,
                                           @RequestParam(name = "endDate", required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                   fallbackPatterns = {"yyyy-MM-dd HH:mm:ss.SSS"})
                                               LocalDateTime endDate) {
        return accountTransactions.getOperationList(clientId, startDate, endDate);
    }
}
