package com.example.recommendershop.repository;

import com.example.recommendershop.entity.Role;
import com.example.recommendershop.entity.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Role findByName(String name);
    Set<Role> findByRoleGroups(RoleGroup roleGroup);
}
