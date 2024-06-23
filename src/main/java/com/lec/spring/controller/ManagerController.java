package com.lec.spring.controller;

import com.lec.spring.domain.Manager;
import com.lec.spring.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<Manager> managers = managerService.getAllUsers();
        model.addAttribute("managers", managers);
        return "mypage/manager/MemberManagement";
    }

    @GetMapping("/ApprovalLodgingUpdate")
    public String getApprovalLodgingUpdate(Model model) {
        return "mypage/manager/ApprovalLodgingUpdate";
    }
}
