package com.example.recommendershop.service.admin;

import com.example.recommendershop.dto.ApiListBaseRequest;
import com.example.recommendershop.dto.BasePage;
import com.example.recommendershop.dto.admin.request.UserEditForAdmin;
import com.example.recommendershop.dto.admin.response.UserViewForAdmin;
import com.example.recommendershop.entity.User;
import com.example.recommendershop.enums.Role;
import com.example.recommendershop.exception.MasterException;
import com.example.recommendershop.mapper.AdminMapper;
import com.example.recommendershop.mapper.UserMapper;
import com.example.recommendershop.repository.UserRepository;
import com.example.recommendershop.utils.FilterDataUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AdminMapper adminMapper;
    private final HttpSession httpSession;
    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper, AdminMapper adminMapper, HttpSession httpSession){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.adminMapper = adminMapper;
        this.httpSession = httpSession;
    }
    protected BasePage<UserViewForAdmin> map(Page<User> page) {
        BasePage<UserViewForAdmin> rPage = new BasePage<>();
        rPage.setData(userMapper.toListDao(page.getContent()));
        rPage.setTotalPage(page.getTotalPages());
        rPage.setTotalRecord( page.getTotalElements());
        rPage.setPage(page.getPageable().getPageNumber());
        return rPage;
    }
    public BasePage<UserViewForAdmin> getAllUser(ApiListBaseRequest listBaseRequest){
        UUID userId = (UUID) httpSession.getAttribute("UserId");
        User user = userRepository.getByUserId(userId);
        if(!(user.getRole().equals(Role.ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        Page<User> page = userRepository.findAll(FilterDataUtil.buildPageRequest(listBaseRequest));
        return this.map(page);
    }

    @Override
    public UserViewForAdmin update(UUID userId, UserEditForAdmin edit) {
        UUID adminId = (UUID) httpSession.getAttribute("UserId");
        User admin = userRepository.getByUserId(adminId);
        if(!(admin.getRole().equals(Role.ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new MasterException(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng!"));
        adminMapper.update(edit, existingUser);
        User user = userRepository.save(existingUser);
        return adminMapper.toDao(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        UUID adminId = (UUID) httpSession.getAttribute("UserId");
        User admin = userRepository.getByUserId(adminId);
        if(!(admin.getRole().equals(Role.ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new MasterException(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng!"));
        userRepository.delete(existingUser);
    }
}
