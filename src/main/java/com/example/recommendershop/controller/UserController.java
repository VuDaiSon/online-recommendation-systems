package com.example.recommendershop.controller;

import com.example.recommendershop.dto.ResponseData;
import com.example.recommendershop.dto.user.request.ChangePasswordRequest;
import com.example.recommendershop.dto.user.request.LoginRequest;
import com.example.recommendershop.dto.user.request.UserRequest;
import com.example.recommendershop.dto.user.response.UserInfor;
import com.example.recommendershop.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseData<?> register(@RequestBody @Valid UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout() {
        userService.logout();
    }
//    @PutMapping("/forgot-password")
//    public ResponseData<String> resetPassword(@RequestParam(name = "email") String email){
//        userService.forgotPasword(email);
//        return new ResponseData<>(HttpStatus.OK.value(), "New password đã được gửi về email của bạn !");
//    }
    @GetMapping("/{userId}")
    public UserInfor getById(@PathVariable(name = "userId")UUID userId){
        return userService.detail(userId);
    }
    @PutMapping("/{userId}")
    public UserInfor edit(@PathVariable(name = "userId") UUID userId, @RequestBody UserRequest userRequest){
        return userService.update(userId, userRequest);
    }
    @PutMapping("/{userId}/changePassword")
    public ResponseData<?> change(@PathVariable(name = "userId")UUID userId, @RequestBody ChangePasswordRequest changePasswordRequest){
        return userService.changePassword(userId, changePasswordRequest);
    }

}
