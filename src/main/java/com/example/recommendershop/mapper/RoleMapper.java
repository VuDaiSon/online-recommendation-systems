package com.example.recommendershop.mapper;

import com.example.recommendershop.dto.role.RoleRequest;
import com.example.recommendershop.dto.role.RoleResponse;
import com.example.recommendershop.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleRequest roleRequest);
    RoleResponse toDao(Role role);
    List<RoleResponse> toListDao(List<Role> role);
}
