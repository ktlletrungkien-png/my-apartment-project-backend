package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "assignmentbuilding")
@Getter
@Setter
public class AssignmentBuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ N-1: Nhiều bản ghi phân công trỏ về 1 nhân viên
    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity user;

    // Quan hệ N-1: Nhiều bản ghi phân công trỏ về 1 tòa nhà
    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;
}