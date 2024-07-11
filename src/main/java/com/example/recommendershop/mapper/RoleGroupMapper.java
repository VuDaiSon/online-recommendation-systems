package com.example.recommendershop.mapper;

import com.example.recommendershop.dto.roleGroup.RoleGroupRequest;
import com.example.recommendershop.dto.roleGroup.RoleGroupResponse;
import com.example.recommendershop.dto.roleGroup.RoleGroupView;
import com.example.recommendershop.entity.RoleGroup;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleGroupMapper {
    RoleGroup toEntity(RoleGroupRequest roleGroupRequest);
    RoleGroupResponse toDao(RoleGroup roleGroup);
    List<RoleGroupResponse> toListDao(List<RoleGroup> roleGroup);
    RoleGroupView toResponse(RoleGroup roleGroup);
}
