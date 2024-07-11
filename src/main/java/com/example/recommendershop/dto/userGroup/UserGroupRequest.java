package com.example.recommendershop.dto.userGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupRequest {
    private String name;
    private Set<UUID> roleGroupIds;
}
