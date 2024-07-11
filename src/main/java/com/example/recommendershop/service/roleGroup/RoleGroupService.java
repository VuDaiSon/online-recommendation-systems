package com.example.recommendershop.service.roleGroup;

import com.example.recommendershop.dto.ResponseData;
import com.example.recommendershop.dto.roleGroup.RoleGroupRequest;
import com.example.recommendershop.dto.roleGroup.RoleGroupResponse;

import java.util.List;
import java.util.UUID;

public interface RoleGroupService {
public RoleGroupResponse create(RoleGroupRequest roleGroupRequest);
public RoleGroupResponse update(UUID roleGroupId, RoleGroupRequest roleGroupRequest);
public List<RoleGroupResponse> findAll();
public ResponseData<?> delete(UUID roleGroupId);
    public RoleGroupResponse findById(UUID roleGroupId);
}
