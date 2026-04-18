package org.example.api;

import org.example.model.BuildingDTO;
import org.example.model.CreateTransactionRequest;
import org.example.model.TransactionDTO;
import org.example.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.model.CustomerDTO;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffAPI {

    @Autowired
    private StaffService staffService;

    @GetMapping("/buildings")
    public List<BuildingDTO> getMyBuildings() {
        return staffService.getMyAssignedBuildings();
    }
    @GetMapping("/customers")
    public List<CustomerDTO> getMyCustomers() {
        return staffService.getMyAssignedCustomers();
    }
    @GetMapping("/customers/{customerId}/transactions")
    public List<TransactionDTO> getCustomerTransactions(@PathVariable Long customerId) {
        return staffService.getTransactionsByCustomer(customerId);
    }

    @PostMapping("/transactions")
    public String createTransaction(@RequestBody CreateTransactionRequest request) {
        try {
            staffService.createTransaction(request);
            return "Them giao dich thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}