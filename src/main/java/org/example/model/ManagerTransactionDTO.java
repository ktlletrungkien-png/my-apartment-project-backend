package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerTransactionDTO {
    private Long id;
    private String note;
    private Long customerId;
    private String customerName;
    private Long staffId;
    private String staffName;
    private Long transactionTypeId;
    private String transactionTypeCode;
    private String transactionTypeName;
}
