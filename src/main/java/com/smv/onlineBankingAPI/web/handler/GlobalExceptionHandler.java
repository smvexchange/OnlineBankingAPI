package com.smv.onlineBankingAPI.web.handler;

import com.smv.onlineBankingAPI.web.exception.NoSuchClientException;
import com.smv.onlineBankingAPI.web.exception.NotEnoughMoneyException;
import com.smv.onlineBankingAPI.web.response.BaseResponse;
import com.smv.onlineBankingAPI.web.response.FailedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchClientException.class)
    public ResponseEntity<BaseResponse> catchNoSuchClientException(NoSuchClientException exception) {
        log.warn("Client with requested id not found.");
        return new ResponseEntity<>(new FailedResponse(exception.getStatusCode(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<BaseResponse> catchNotEnoughMoneyException(NotEnoughMoneyException exception) {
        log.warn("Transaction failed. Not enough money.");
        return new ResponseEntity<>(new FailedResponse(exception.getStatusCode(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
