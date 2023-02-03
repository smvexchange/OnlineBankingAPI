package com.smv.onlineBankingAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smv.onlineBankingAPI.builder.components.OperationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Operation {

    public Operation(LocalDateTime localDateTime,
                     Client client,
                     OperationType operationType,
                     BigDecimal cash) {
        this.localDateTime = localDateTime;
        this.client = client;
        this.operationType = operationType;
        this.cash = cash;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "localDateTime")
    @JsonProperty(value = "localDateTime")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "operationType")
    @JsonProperty(value = "operationType")
    private OperationType operationType;

    @Column(name = "amount")
    @JsonProperty(value = "amount")
    private BigDecimal cash;

}
