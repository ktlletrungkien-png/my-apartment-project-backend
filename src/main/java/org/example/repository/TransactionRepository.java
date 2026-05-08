package org.example.repository;

import org.example.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `transaction` WHERE customerid = :customerId", nativeQuery = true)
    void deleteByCustomerId(@Param("customerId") Long customerId);
}
