package org.example.repository;

import org.example.entity.BuildingRentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRentTypeRepository extends JpaRepository<BuildingRentTypeEntity, Long> {
    List<BuildingRentTypeEntity> findByBuildingId(Long buildingId);
    void deleteByBuildingId(Long buildingId);
}
