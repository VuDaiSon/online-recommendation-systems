package com.example.recommendershop.service.user;

import com.example.recommendershop.dto.ResponseData;
import com.example.recommendershop.dto.user.request.ChangePasswordRequest;
import com.example.recommendershop.dto.user.request.LoginRequest;
import com.example.recommendershop.dto.user.request.UserRequest;
import com.example.recommendershop.dto.user.response.UserInfor;

import java.util.UUID;

public interface UserService {
    public ResponseData<?> register(UserRequest userRequest);

    ResponseData<?> login(LoginRequest loginRequest);

    public void logout();
    public UserInfor update(UUID userId, UserRequest userRequest);

    public UserInfor detail(UUID userId);
    public ResponseData<?> changePassword(UUID uuid, ChangePasswordRequest changePasswordRequest);
//    public ResponseData<?> forgotPassword(String email);

    }