package org.example.repository;

import org.example.entity.BuildingEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

    @Query(value = "SELECT DISTINCT b.* FROM building b " +
            "LEFT JOIN rentarea ra ON b.id = ra.buildingid " +
            "LEFT JOIN assignmentbuilding ab ON b.id = ab.buildingid " +
            "LEFT JOIN user u ON ab.staffid = u.id " +
            "WHERE (:name IS NULL OR b.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:rentArea IS NULL OR ra.value = :rentArea) " +
            "AND (:staffName IS NULL OR u.fullname LIKE CONCAT('%', :staffName, '%')) " +
            "AND (:address IS NULL OR b.ward LIKE CONCAT('%', :address, '%') " +
            "                     OR b.street LIKE CONCAT('%', :address, '%')) " +
            "AND (:rentPriceFrom IS NULL OR b.rentprice >= :rentPriceFrom) " +
            "AND (:rentPriceTo IS NULL OR b.rentprice <= :rentPriceTo)",
            nativeQuery = true)

    List<BuildingEntity> searchBuilding(
            @Param("name") String name,
            @Param("rentArea") Integer rentArea,
            @Param("address") String address,
            @Param("staffName") String staffName,
            @Param("rentPriceFrom") Integer rentPriceFrom,
            @Param("rentPriceTo") Integer rentPriceTo);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM rentarea WHERE buildingid = :buildingId", nativeQuery = true)
    void deleteRentAreaByBuildingId(@Param("buildingId") Long buildingId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM assignmentbuilding WHERE buildingid = :buildingId", nativeQuery = true)
    void deleteAssignmentByBuildingId(@Param("buildingId") Long buildingId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM buildingrenttype WHERE buildingid = :buildingId", nativeQuery = true)
    void deleteBuildingRentTypeByBuildingId(@Param("buildingId") Long buildingId);

    @Query(value = "SELECT staffid FROM assignmentbuilding WHERE buildingid = :buildingId", nativeQuery = true)
    List<Long> findAssignedStaffIds(@Param("buildingId") Long buildingId);
    @Query(value = "SELECT DISTINCT b.* FROM building b " +
            "JOIN assignmentbuilding ab ON b.id = ab.buildingid " +
            "WHERE ab.staffid = :staffId", nativeQuery = true)
    List<BuildingEntity> findBuildingsByStaffId(@Param("staffId") Long staffId);
}
