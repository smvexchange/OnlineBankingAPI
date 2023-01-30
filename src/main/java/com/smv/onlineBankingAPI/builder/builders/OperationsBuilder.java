package com.smv.onlineBankingAPI.builder.builders;

import com.smv.onlineBankingAPI.builder.components.OperationType;
import com.smv.onlineBankingAPI.model.Client;
import com.smv.onlineBankingAPI.model.Operation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class OperationsBuilder implements Builder{

    private Long id;
    private LocalDateTime localDateTime;
    private Client client;
    private OperationType operationType;
    private BigDecimal cash;

    @Override
    public void setLocalDateTime() {
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.cash = amount;
    }

    public Operation getOperation() {
        return new Operation(localDateTime, client, operationType, cash);
    }
}
