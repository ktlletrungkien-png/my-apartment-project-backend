package org.example.api;

import org.example.entity.TransactionTypeEntity;
import org.example.model.TransactionTypeDTO;
import org.example.repository.TransactionTypeRepository;
import org.example.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transactiontype")
public class TransactionTypeAPI {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @GetMapping
    public List<TransactionTypeDTO> listEntity() {
        return transactionTypeService.listAll();
    }

    @GetMapping("/{id}")
    public TransactionTypeDTO loadTransactionType(@PathVariable Long id) {
        return transactionTypeService.findById(id);
    }

    @PostMapping
    public String addTransactionType(@RequestBody TransactionTypeEntity request) {
        try {
            transactionTypeRepository.save(request);
            return "Them loai giao dich thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public String updateTransactionType(@PathVariable Long id, @RequestBody TransactionTypeEntity request) {
        try {
            TransactionTypeEntity old = transactionTypeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Khong tim thay loai giao dich"));
            old.setCode(request.getCode());
            old.setName(request.getName());
            transactionTypeRepository.save(old);
            return "Cap nhat loai giao dich thanh cong";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
