package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="renttype")
@Getter
@Setter
public class RentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="code")
    private String code;
    @Column(name="name")
    private String name;
}
