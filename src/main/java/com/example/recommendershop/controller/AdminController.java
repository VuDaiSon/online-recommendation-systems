package com.example.recommendershop.controller;

import com.example.recommendershop.dto.ApiListBaseRequest;
import com.example.recommendershop.dto.BasePage;
import com.example.recommendershop.dto.admin.request.UserEditForAdmin;
import com.example.recommendershop.dto.admin.response.UserViewForAdmin;
import com.example.recommendershop.service.admin.AdminService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/admin")
public class AdminController {
@Autowired
    private AdminService adminService;
@GetMapping("/all")
    public BasePage<UserViewForAdmin> findAll(@ParameterObject ApiListBaseRequest listBaseRequest){
        return adminService.getAllUser(listBaseRequest);
}
@PatchMapping("/{userId}")
    public UserViewForAdmin update(@PathVariable(name = "userId") UUID userId, @RequestBody UserEditForAdmin edit){
        return adminService.update(userId, edit);
}

@DeleteMapping("/{userId}")
    public void delete(@PathVariable(name = "userId")UUID userId){
        adminService.deleteUser(userId);
    }
}
