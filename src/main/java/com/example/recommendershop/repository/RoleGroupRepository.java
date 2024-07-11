package com.example.recommendershop.repository;

import com.example.recommendershop.entity.RoleGroup;
import com.example.recommendershop.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, UUID>, JpaSpecificationExecutor<RoleGroup> {
    RoleGroup findByName(String name);
    Set<RoleGroup> findByUserGroups(UserGroup userGroup);
}
