package com.smv.onlineBankingAPI.service;

import com.smv.onlineBankingAPI.web.exception.NoSuchClientException;
import com.smv.onlineBankingAPI.web.exception.NotEnoughMoneyException;
import com.smv.onlineBankingAPI.model.Client;
import com.smv.onlineBankingAPI.repository.ClientRepository;
import com.smv.onlineBankingAPI.web.response.BalanceResponse;
import com.smv.onlineBankingAPI.web.response.SuccessfulResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountTransactions {

    private final ClientRepository clientRepository;

    public AccountTransactions(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public BalanceResponse getBalance(Long clientId) {
        return new BalanceResponse(clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchClientException("Client with id " + clientId + " not found.")).getBalance());
    }

    public SuccessfulResponse takeMoney(Long clientId, BigDecimal cash) {
        if (clientId == null || cash == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchClientException("Client with id " + clientId + " not found."));

        if (client.getBalance().compareTo(cash) >= 0) {
            client.setBalance(client.getBalance().subtract(cash));
            return new SuccessfulResponse(1);
        } else {
            throw new NotEnoughMoneyException("Not enough money");
        }
    }

    public SuccessfulResponse putMoney(Long clientId, BigDecimal cash) {
        if (clientId == null || cash == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchClientException("Client with id " + clientId + " not found."));
        client.setBalance(client.getBalance().subtract(cash));
        return new SuccessfulResponse(1);
    }
}
