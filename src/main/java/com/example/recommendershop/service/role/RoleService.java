package com.example.recommendershop.service.role;

import com.example.recommendershop.dto.role.RoleRequest;
import com.example.recommendershop.dto.role.RoleResponse;

import java.util.List;

public interface RoleService {
    public RoleResponse create(RoleRequest roleRequest);
    public List<RoleResponse> findAll();
}
