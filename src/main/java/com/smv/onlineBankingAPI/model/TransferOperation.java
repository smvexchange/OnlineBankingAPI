package com.smv.onlineBankingAPI.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transfer_operations")
public class TransferOperation implements Comparable<TransferOperation> {

    @EmbeddedId
    private TransferOperationKey id;

    @ManyToOne
    @MapsId("senderId")
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @MapsId("receiverId")
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    private BigDecimal cash;

    @Override
    public int compareTo(TransferOperation transferOperation) {
        return id.getDateTime().compareTo(transferOperation.getId().getDateTime());
    }
}
