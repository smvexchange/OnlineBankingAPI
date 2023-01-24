package com.smv.onlineBankingAPI.service;

import com.smv.onlineBankingAPI.model.Client;
import com.smv.onlineBankingAPI.repository.ClientRepository;
import com.smv.onlineBankingAPI.web.exception.NoSuchClientException;
import com.smv.onlineBankingAPI.web.exception.NotEnoughMoneyException;
import com.smv.onlineBankingAPI.web.response.BalanceResponse;
import com.smv.onlineBankingAPI.web.response.BaseResponse;
import com.smv.onlineBankingAPI.web.response.FailedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountTransactions {

    private final ClientRepository clientRepository;

    public AccountTransactions(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public BalanceResponse getBalance(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchClientException("Client with id " + clientId + " not found.", -1));
        log.info("Client# " + clientId + ": Balance request completed.");
        return new BalanceResponse(client.getBalance());
    }

    public BaseResponse takeMoney(Long clientId, BigDecimal cash) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchClientException("Client with id " + clientId + " not found.", 0));
        if (client.getBalance().compareTo(cash) >= 0) {
            client.setBalance(client.getBalance().subtract(cash));
            clientRepository.save(client);
            log.info("Client# " + clientId + ": Take out cash successful complete. Amount: " + cash + ".");
            return new BaseResponse(1);
        } else {
            throw new NotEnoughMoneyException("Client# " + clientId + ": Transaction failed. Not enough money.", 0);
        }
    }

    public BaseResponse putMoney(Long clientId, BigDecimal cash) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchClientException("Client with id " + clientId + " not found.", 0));
        client.setBalance(client.getBalance().add(cash));
        clientRepository.save(client);
        log.info("Client# " + clientId + ": Cash deposit was successful complete. Amount: " + cash + ".");
        return new BaseResponse(1);
    }
}
