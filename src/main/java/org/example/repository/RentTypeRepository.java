package org.example.repository;

import org.example.entity.RentTypeEntity;
import org.example.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentTypeRepository extends JpaRepository<RentTypeEntity, Long> {

}
