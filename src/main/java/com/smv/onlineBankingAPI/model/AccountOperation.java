package com.smv.onlineBankingAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smv.onlineBankingAPI.enums.OperationType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "account_operations")
public class AccountOperation implements Comparable<AccountOperation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "localDateTime")
    @JsonProperty(value = "localDateTime")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @Column(name = "operationType")
    @JsonProperty(value = "operationType")
    private OperationType operationType;

    @Column(name = "cash")
    @JsonProperty(value = "cash")
    private BigDecimal cash;

    @Override
    public int compareTo(AccountOperation accountOperation) {
        return localDateTime.compareTo(accountOperation.getLocalDateTime());
    }
}
