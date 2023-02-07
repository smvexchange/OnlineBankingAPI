package com.smv.onlineBankingAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Long id;

    @Column(name = "balance")
    @JsonProperty(value = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Setter(AccessLevel.NONE)
    private Set<AccountOperation> accountOperationSet;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private Set<TransferOperation> outgoingTransferSet;

    @OneToMany(mappedBy = "receiver")
    @Setter(AccessLevel.NONE)
    private Set<TransferOperation> incomingTransferSet;

    public void setAccountOperationSet(AccountOperation accountOperation) {
        if (this.accountOperationSet == null) {
            accountOperationSet = new LinkedHashSet<>();
            accountOperationSet.add(accountOperation);
        } else {
            accountOperationSet.add(accountOperation);
        }
    }

    public void setOutgoingTransferSet(TransferOperation transfer) {
        if (this.outgoingTransferSet == null) {
            outgoingTransferSet = new LinkedHashSet<>();
            outgoingTransferSet.add(transfer);
        } else {
            outgoingTransferSet.add(transfer);
        }
    }

    public void setIncomingTransferSet(TransferOperation transfer) {
        if (this.incomingTransferSet == null) {
            incomingTransferSet = new LinkedHashSet<>();
            incomingTransferSet.add(transfer);
        } else {
            incomingTransferSet.add(transfer);
        }
    }
}
