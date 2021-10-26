package com.sda.project.repository;

import com.sda.project.model.Privilege;
import com.sda.project.model.PrivilegeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByType(PrivilegeType type);
}
