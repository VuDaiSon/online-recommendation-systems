package com.example.recommendershop.dto.admin.request;

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
public class UserEditForAdmin {

    private String name;

    private String email;

    private String phone;

    private String address;

    private String password;
    private Integer age;
    private Sex sex;
    private Set<UUID> userGroupIds;
}
