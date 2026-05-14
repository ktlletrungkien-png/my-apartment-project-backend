package org.example.api;

import org.example.model.ManagerTransactionDTO;
import org.example.service.ManagerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/manager/transactions")
public class ManagerTransactionAPI {

    @Autowired
    private ManagerTransactionService managerTransactionService;

    @GetMapping
    public List<ManagerTransactionDTO> findAll(@RequestParam Map<String, String> params) {
        return managerTransactionService.findAll(params);
    }
}
