package com.example.recommendershop.dto.admin.response;

import com.example.recommendershop.dto.BaseDto;
import com.example.recommendershop.enums.Role;
import com.example.recommendershop.enums.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Role role;
}
