package com.smv.onlineBankingAPI.controller;

import com.smv.onlineBankingAPI.model.AccountOperation;
import com.smv.onlineBankingAPI.service.AccountOperationService;
import com.smv.onlineBankingAPI.web.response.BalanceResponse;
import com.smv.onlineBankingAPI.web.response.BaseResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
public class AccountController {

    private final AccountOperationService accountOperationService;

    public AccountController(AccountOperationService accountOperationService) {
        this.accountOperationService = accountOperationService;
    }

    @GetMapping(value = "/getBalance",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BalanceResponse getBalance(@RequestParam(name = "id") Long clientId) {
        return accountOperationService.getBalance(clientId);
    }

    @GetMapping(value = "/takeMoney",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse takeMoney(@RequestParam(name = "id") Long clientId,
                                  @RequestParam(name = "cash") BigDecimal cash) {
        return accountOperationService.takeMoney(clientId, cash);
    }

    @GetMapping(value = "/putMoney",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse putMoney(@RequestParam(name = "id") Long clientId,
                                 @RequestParam(name = "cash") BigDecimal cash) {
        return accountOperationService.putMoney(clientId, cash);
    }

    @GetMapping(value = "/getOperationList",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<AccountOperation> getOperationList(@RequestParam(name = "id")
                                                  Long clientId,
                                                  @RequestParam(name = "startDate", required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                          fallbackPatterns = {"yyyy-MM-dd HH:mm:ss.SSS"})
                                                  LocalDateTime startDate,
                                                  @RequestParam(name = "endDate", required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                          fallbackPatterns = {"yyyy-MM-dd HH:mm:ss.SSS"})
                                                  LocalDateTime endDate) {
        return accountOperationService.getOperationList(clientId, startDate, endDate);
    }

    @GetMapping(value = "/transferMoney",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse transferMoney(@RequestParam(name = "senderId") Long senderId,
                                      @RequestParam(name = "receiverId") Long receiverId,
                                      @RequestParam(name = "cash") BigDecimal cash) {
        return accountOperationService.transferMoney(senderId, receiverId, cash);
    }
}
