package com.example.recommendershop.mapper;

import com.example.recommendershop.dto.admin.request.UserEditForAdmin;
import com.example.recommendershop.dto.admin.response.UserViewForAdmin;
import com.example.recommendershop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    User toEntity(UserEditForAdmin edit);
    UserViewForAdmin toDao(User user);
    void update(UserEditForAdmin edit, @MappingTarget User user);

}
