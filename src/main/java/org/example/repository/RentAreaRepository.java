package org.example.repository;

import org.example.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    List<RentAreaEntity> findByBuildingId(Long buildingId);

    void deleteByBuildingId(Long buildingId);
}
