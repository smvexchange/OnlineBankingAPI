package com.smv.onlineBankingAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Long id;

    @Column(name = "balance")
    @JsonProperty(value = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Setter(AccessLevel.NONE)
    private Set<Operation> operationList;

    public void setOperationList(Operation operation) {
        if (this.operationList == null) {
            operationList = new HashSet<>();
            operationList.add(operation);
        } else {
            operationList.add(operation);
        }
    }
}
