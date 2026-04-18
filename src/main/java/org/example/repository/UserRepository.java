package org.example.repository;

import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value="select u.* " +
            "from user u " +
            "join user_role ur on u.id=ur.userid " +
            "join role r on ur.roleid=r.id " +
            "where r.code = 'staff'", nativeQuery = true)
    List<UserEntity> findAllStaff();
    Optional<UserEntity> findByUsername(String username);
}
