package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private String note;
    private Long customerId;
    private Long transactionTypeId;
    private String transactionTypeCode;
    private String transactionTypeName;
}