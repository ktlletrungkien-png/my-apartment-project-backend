package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "assignmentcustomer")
@Getter
@Setter
public class AssignmentCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staffid")
    private Long staffId;

    @Column(name = "customerid")
    private Long customerId;
}