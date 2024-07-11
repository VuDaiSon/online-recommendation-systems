package com.example.recommendershop.controller;

import com.example.recommendershop.dto.ResponseData;
import com.example.recommendershop.dto.roleGroup.RoleGroupRequest;
import com.example.recommendershop.dto.roleGroup.RoleGroupResponse;
import com.example.recommendershop.service.roleGroup.RoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roleGroups")
public class RoleGroupController {
    private final RoleGroupService roleGroupService;
    @Autowired
    public RoleGroupController(RoleGroupService roleGroupService){
        this.roleGroupService = roleGroupService;
    }
    @PostMapping("/")
    public RoleGroupResponse add(@RequestBody RoleGroupRequest roleGroupRequest){
        return roleGroupService.create(roleGroupRequest);
    }
    @PatchMapping("/{roleGroupId}")
    public RoleGroupResponse edit(@PathVariable(name = "roleGroupId") UUID roleGroupId, @RequestBody RoleGroupRequest roleGroupRequest){
        return roleGroupService.update(roleGroupId, roleGroupRequest);
    }
    @GetMapping("/")
    public List<RoleGroupResponse> getAll(){
        return roleGroupService.findAll();
    }
    @DeleteMapping("/{roleGroupId}")
    public ResponseData<?> delete(@PathVariable(name = "roleGroupId") UUID roleGroupId){
        return roleGroupService.delete(roleGroupId);
    }
    @GetMapping({"/roleGroupId"})
    public RoleGroupResponse detail(@PathVariable(name = "roleGroupId") UUID roleGroupId){
        return roleGroupService.findById(roleGroupId);
    }

}
