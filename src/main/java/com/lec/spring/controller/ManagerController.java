package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserAuthority;
import com.lec.spring.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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



    @GetMapping("/ApprovalLodgingUpdate")
    public String getApprovalLodgingUpdate(Model model) {
        return "mypage/manager/ApprovalLodgingUpdate";
    }




}
