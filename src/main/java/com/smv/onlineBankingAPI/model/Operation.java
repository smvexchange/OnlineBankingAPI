package com.smv.onlineBankingAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonProperty(value = "clientId")
    private Client client;

    @Column(name = "operationType")
    @JsonProperty(value = "operationType")
    private int operationType;

    @Column(name = "amount")
    @JsonProperty(value = "amount")
    private BigDecimal amount;

}
