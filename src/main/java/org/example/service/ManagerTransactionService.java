package org.example.service;

import org.example.model.ManagerTransactionDTO;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ManagerTransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public List<ManagerTransactionDTO> findAll(Map<String, String> params) {
        String staffName = params.get("staffName");
        String customerName = params.get("customerName");
        String note = params.get("note");
        Long transactionTypeId = (params.get("transactionTypeId") != null && !params.get("transactionTypeId").isEmpty())
                ? Long.parseLong(params.get("transactionTypeId")) : null;

        return transactionRepository.searchManagerTransactions(staffName, customerName, transactionTypeId, note)
                .stream()
                .map(row -> {
                    ManagerTransactionDTO dto = new ManagerTransactionDTO();
                    dto.setId(toLong(row[0]));
                    dto.setNote((String) row[1]);
                    dto.setCustomerId(toLong(row[2]));
                    dto.setCustomerName((String) row[3]);
                    dto.setTransactionTypeId(toLong(row[4]));
                    dto.setTransactionTypeCode((String) row[5]);
                    dto.setTransactionTypeName((String) row[6]);
                    dto.setStaffName(row[8] != null ? row[8].toString() : "Chua xac dinh");

                    return dto;
                })
                .toList();
    }

    private Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number number) {
            return number.longValue();
        }
        return Long.parseLong(value.toString());
    }
}
