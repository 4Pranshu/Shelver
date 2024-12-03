package com.example.Shelver.controllers;


import com.example.Shelver.dtos.CreateAdminRequest;
import com.example.Shelver.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    Integer createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest){
        return adminService.createAdmin(createAdminRequest);
    }

}
