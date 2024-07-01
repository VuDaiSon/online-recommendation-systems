package com.example.recommendershop.service.admin;


import com.example.recommendershop.dto.ApiListBaseRequest;
import com.example.recommendershop.dto.BasePage;
import com.example.recommendershop.dto.admin.request.UserEditForAdmin;
import com.example.recommendershop.dto.admin.response.UserViewForAdmin;

import java.util.UUID;

public interface AdminService {
    public BasePage<UserViewForAdmin> getAllUser(ApiListBaseRequest listBaseRequest);
    public UserViewForAdmin update(UUID userId, UserEditForAdmin edit);
    void deleteUser(UUID userId);
}
