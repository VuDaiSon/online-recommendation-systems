package com.example.recommendershop.dto.roleGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleGroupRequest {
    private String name;
    private Set<Long> roleId;
}
