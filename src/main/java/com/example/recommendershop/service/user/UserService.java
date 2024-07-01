package com.example.recommendershop.service.user;

import com.example.recommendershop.dto.ResponseData;
import com.example.recommendershop.dto.user.request.LoginRequest;
import com.example.recommendershop.dto.user.request.UserRequest;
import com.example.recommendershop.dto.user.response.UserInfor;

import java.util.UUID;

public interface UserService {
    public ResponseData<?> register(UserRequest userRequest);

    ResponseData<?> login(LoginRequest loginRequest);

    public void logout();


    public UserInfor detail(UUID userId);
//    public void forgotPassword(String email);

    }