package com.smv.onlineBankingAPI.builder.builders;

import com.smv.onlineBankingAPI.builder.components.OperationType;
import com.smv.onlineBankingAPI.model.Client;

import java.math.BigDecimal;

public interface Builder {
    void setLocalDateTime();
    void setClient(Client client);
    void setOperationType(OperationType operationType);
    void setAmount(BigDecimal amount);
}
