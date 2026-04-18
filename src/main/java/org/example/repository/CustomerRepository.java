package org.example.repository;

import org.example.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT DISTINCT c.* FROM customer c " +
            "JOIN assignmentcustomer ac ON c.id = ac.customerid " +
            "WHERE ac.staffid = :staffId", nativeQuery = true)
    List<CustomerEntity> findCustomersByStaffId(@Param("staffId") Long staffId);
}