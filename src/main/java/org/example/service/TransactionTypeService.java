package org.example.service;

import org.example.entity.TransactionTypeEntity;
import org.example.model.TransactionTypeDTO;
import org.example.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public List<TransactionTypeDTO> listAll() {
        return transactionTypeRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public TransactionTypeDTO findById(Long id) {
        TransactionTypeEntity entity = transactionTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay loai giao dich"));
        return toDTO(entity);
    }

    private TransactionTypeDTO toDTO(TransactionTypeEntity entity) {
        TransactionTypeDTO dto = new TransactionTypeDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        return dto;
    }
}
