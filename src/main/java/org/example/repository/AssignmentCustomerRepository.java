package org.example.repository;

import org.example.entity.AssignmentCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM assignmentcustomer ac WHERE ac.staffid = :staffId AND ac.customerid = :customerId", nativeQuery = true)
    int countAssignment(@Param("staffId") Long staffId, @Param("customerId") Long customerId);
}