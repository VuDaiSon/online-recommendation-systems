package com.example.recommendershop.dto.userGroup;

import com.example.recommendershop.dto.roleGroup.RoleGroupView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupResponse {
    private String name;
    private Set<RoleGroupView> roleGroups;
}
