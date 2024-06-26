package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserAuthority;
import com.lec.spring.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/MemberManagement")
    public String getAllManagers(Model model) {
//        List<User> managers = managerService.getAllUsers();
//        model.addAttribute("managers", managers);

        List<UserAuthority> userAuthorities = managerService.getAllUserAuthorities();
        model.addAttribute("userAuthorities", userAuthorities);

        List<User> managers = managerService.getAllUsersWithAuthorities();
        model.addAttribute("managers", managers);

        System.out.println(managers);
        System.out.println(userAuthorities);
        return "mypage/manager/MemberManagement";
    }


    @PostMapping("/addRoleProvider")
    public String addRoleProvider(@RequestParam Long userId) {
        managerService.addUserAuthority(userId, 2L); // 2L is ROLE_PROVIDER
        return "redirect:/mypage/manager/MemberManagement";
    }

    @PostMapping("/addRoleMaster")
    public String addRoleMaster(@RequestParam Long userId) {
        managerService.addUserAuthority(userId, 3L); // 3L is ROLE_MASTER
        return "redirect:/mypage/manager/MemberManagement";
    }


}
