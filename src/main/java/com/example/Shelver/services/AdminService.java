package com.example.Shelver.services;


import com.example.Shelver.dtos.CreateAdminRequest;
import com.example.Shelver.dtos.CreateStudentRequest;
import com.example.Shelver.models.Admin;
import com.example.Shelver.models.Authority;
import com.example.Shelver.models.Student;
import com.example.Shelver.models.User;
import com.example.Shelver.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminRepository adminRepository;

    public Integer createAdmin(CreateAdminRequest createAdminRequest) {
        Admin admin = createAdminRequest.to();
        User user = userService.create(admin.getUser(), Authority.ADMIN);
        admin.setUser(user);
        admin= adminRepository.save(admin);
        return admin.getId();
    }


}
