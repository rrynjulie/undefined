package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("mypage/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @GetMapping("/ManageAccount")
    public String manageAccount(Model model) {
        User user = getLoggedUser();
        model.addAttribute("user", user);
        return "mypage/customer/ManageAccount";
    }

    @PostMapping("/ManageAccount")
    public String updateAccount(@RequestParam String nickname,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String phone) {
        User user = getLoggedUser();
        userService.updateUser(user.getUserId(), nickname, password, email, phone);
        return "redirect:/mypage/customer/ManageAccount";
    }

    @GetMapping("/getProvider")
    @ResponseBody
    public String getProvider() {
        User user = getLoggedUser();
        System.out.println(user.getProvider());
        return user.getProvider();
    }

    private User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof PrincipalDetails) {
            PrincipalDetails principalDetails = (PrincipalDetails) principal;
            System.out.println(principalDetails.getUser());
            return principalDetails.getUser();
        } else if (principal instanceof DefaultOAuth2User) {
            Map<String, Object> attributes = ((DefaultOAuth2User) principal).getAttributes();
            String nickname = (String) attributes.get("nickname");
            return userService.findByNickname(nickname);
        } else if (principal instanceof String) {
            String username = (String) principal;
            return userService.findByUsername(username);
        } else {
            throw new IllegalStateException("Unknown principal type: " + principal.getClass());
        }
    }


}






