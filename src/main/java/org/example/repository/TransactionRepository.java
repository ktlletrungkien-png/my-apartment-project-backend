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

    @Query(value = "SELECT t.id, t.note, c.id, c.fullname, tt.id, tt.code, tt.name, " +
            "GROUP_CONCAT(DISTINCT u.id ORDER BY u.id SEPARATOR ', '), " +
            "GROUP_CONCAT(DISTINCT u.fullname ORDER BY u.id SEPARATOR ', ') " +
            "FROM `transaction` t " +
            "LEFT JOIN customer c ON t.customerid = c.id " +
            "LEFT JOIN transactiontype tt ON t.type = tt.id " +
            "LEFT JOIN assignmentcustomer ac ON c.id = ac.customerid " +
            "LEFT JOIN user u ON ac.staffid = u.id " +
            "WHERE (:staffName IS NULL OR u.fullname LIKE CONCAT('%', :staffName, '%')) " +
            "AND (:customerName IS NULL OR c.fullname LIKE CONCAT('%', :customerName, '%')) " +
            "AND (:transactionTypeId IS NULL OR tt.id = :transactionTypeId) " +
            "AND (:note IS NULL OR t.note LIKE CONCAT('%', :note, '%')) " +
            "GROUP BY t.id, t.note, c.id, c.fullname, tt.id, tt.code, tt.name",
            nativeQuery = true)
    List<Object[]> searchManagerTransactions(
            @Param("staffName") String staffName,
            @Param("customerName") String customerName,
            @Param("transactionTypeId") Long transactionTypeId,
            @Param("note") String note);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `transaction` WHERE customerid = :customerId", nativeQuery = true)
    void deleteByCustomerId(@Param("customerId") Long customerId);
}
