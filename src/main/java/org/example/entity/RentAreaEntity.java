package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rentarea")
@Getter
@Setter
public class RentAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Integer value;

    // Map quan hệ N-1 về Building
    @ManyToOne
    @JoinColumn(name = "buildingid") // Tên cột khóa ngoại trong bảng rentarea
    private BuildingEntity building;
}