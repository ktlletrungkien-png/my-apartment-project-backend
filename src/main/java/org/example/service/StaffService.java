package org.example.service;

import org.example.entity.BuildingEntity;
import org.example.entity.TransactionEntity;
import org.example.entity.TransactionTypeEntity;
import org.example.model.BuildingDTO;
import org.example.model.CreateTransactionRequest;
import org.example.model.TransactionDTO;
import org.example.repository.*;
import org.example.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.entity.CustomerEntity;
import org.example.model.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    @Transactional(readOnly = true)
    public List<BuildingDTO> getMyAssignedBuildings() {
        Long currentStaffId = SecurityUtils.getCurrentUserId();

        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        List<BuildingEntity> buildings = buildingRepository.findBuildingsByStaffId(currentStaffId);

        return buildings.stream().map(item -> {
            BuildingDTO dto = new BuildingDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());

            String street = item.getStreet() != null ? item.getStreet() : "";
            String ward = item.getWard() != null ? item.getWard() : "";
            String fullAddress = street + (!street.isEmpty() && !ward.isEmpty() ? ", " : "") + ward;

            dto.setAddress(fullAddress);
            dto.setRentPrice(item.getRentPrice());
            dto.setManagerName(item.getManagerName());
            dto.setManagerPhoneNumber(item.getManagerPhoneNumber());

            if (item.getRentAreas() != null && !item.getRentAreas().isEmpty()) {
                String rentAreas = item.getRentAreas().stream()
                        .map(area -> String.valueOf(area.getValue()))
                        .collect(Collectors.joining(", "));
                dto.setRentAreas(rentAreas);
            }

            return dto;
        }).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<CustomerDTO> getMyAssignedCustomers() {
        Long currentStaffId = SecurityUtils.getCurrentUserId();

        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        List<CustomerEntity> customers = customerRepository.findCustomersByStaffId(currentStaffId);

        return customers.stream().map(item -> {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(item.getId());
            dto.setFullName(item.getFullname());
            dto.setPhone(item.getPhone());
            dto.setEmail(item.getEmail());
            return dto;
        }).toList();
    }
    private void validateCustomerAssignment(Long customerId) {
        Long currentStaffId = SecurityUtils.getCurrentUserId();

        if (currentStaffId == null) {
            throw new RuntimeException("Chua dang nhap");
        }

        int count = assignmentCustomerRepository.countAssignment(currentStaffId, customerId);
        if (count <= 0) {
            throw new RuntimeException("Ban khong duoc phan cong khach hang nay");
        }
    }
    @Transactional(readOnly = true)
    public List<TransactionDTO> getTransactionsByCustomer(Long customerId) {
        validateCustomerAssignment(customerId);

        List<TransactionEntity> transactions = transactionRepository.findByCustomerId(customerId);

        return transactions.stream().map(item -> {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(item.getId());
            dto.setNote(item.getNote());
            dto.setCustomerId(item.getCustomer().getId());

            if (item.getTransactionType() != null) {
                dto.setTransactionTypeId(item.getTransactionType().getId());
                dto.setTransactionTypeCode(item.getTransactionType().getCode());
                dto.setTransactionTypeName(item.getTransactionType().getName());
            }

            return dto;
        }).toList();
    }
    @Transactional
    public void createTransaction(CreateTransactionRequest request) {
        if (request.getCustomerId() == null) {
            throw new RuntimeException("CustomerId khong duoc de trong");
        }

        if (request.getTransactionTypeId() == null) {
            throw new RuntimeException("TransactionTypeId khong duoc de trong");
        }

        validateCustomerAssignment(request.getCustomerId());

        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay khach hang"));

        TransactionTypeEntity transactionType = transactionTypeRepository.findById(request.getTransactionTypeId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay loai giao dich"));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setCustomer(customer);
        transaction.setTransactionType(transactionType);
        transaction.setNote(request.getNote());

        transactionRepository.save(transaction);
    }
}