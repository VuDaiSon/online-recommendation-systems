package com.example.recommendershop.dto.admin.request;

import com.example.recommendershop.enums.Role;
import com.example.recommendershop.enums.Sex;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Role role;
}
