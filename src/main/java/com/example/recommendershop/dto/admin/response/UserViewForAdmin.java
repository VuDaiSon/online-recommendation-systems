package com.example.recommendershop.dto.admin.response;

import com.example.recommendershop.dto.BaseDto;
import com.example.recommendershop.dto.userGroup.UserGroupResponse;
import com.example.recommendershop.enums.Sex;
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
public class UserViewForAdmin extends BaseDto {
    private UUID userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Integer age;
    private Sex sex;
    private Set<UserGroupResponse> userGroups;
}
