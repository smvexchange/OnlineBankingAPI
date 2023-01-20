package com.smv.onlineBankingAPI.web.handler;

import com.smv.onlineBankingAPI.web.exception.NoSuchClientException;
import com.smv.onlineBankingAPI.web.response.FailedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<FailedResponse> catchNoSuchClientException(NoSuchClientException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new FailedResponse(exception.getStatusCode(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
