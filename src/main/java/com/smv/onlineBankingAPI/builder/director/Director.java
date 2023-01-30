package com.smv.onlineBankingAPI.builder.director;

import com.smv.onlineBankingAPI.builder.builders.Builder;
import com.smv.onlineBankingAPI.builder.components.OperationType;
import com.smv.onlineBankingAPI.model.Client;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Director {

    public void buildTakeMoneyOperation(Builder builder, Client client, BigDecimal cash) {
        builder.setLocalDateTime();
        builder.setClient(client);
        builder.setOperationType(OperationType.TAKE_MONEY);
        builder.setAmount(cash);
    }

    public void buildPutMoneyOperation(Builder builder, Client client, BigDecimal cash) {
        builder.setLocalDateTime();
        builder.setClient(client);
        builder.setOperationType(OperationType.PUT_MONEY);
        builder.setAmount(cash);
    }
}
