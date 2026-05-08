package org.example.repository;

import org.example.entity.AssignmentCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM assignmentcustomer ac WHERE ac.staffid = :staffId AND ac.customerid = :customerId", nativeQuery = true)
    int countAssignment(@Param("staffId") Long staffId, @Param("customerId") Long customerId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM assignmentcustomer WHERE customerid = :customerId", nativeQuery = true)
    void deleteAssignmentByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "SELECT staffid FROM assignmentcustomer WHERE customerid = :customerId", nativeQuery = true)
    List<Long> findAssignedStaffIds(@Param("customerId") Long customerId);
}
