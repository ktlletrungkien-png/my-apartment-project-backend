package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionRequest {
    private Long customerId;
    private Long transactionTypeId;
    private String note;
}