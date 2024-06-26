package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserValidator;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String registerOk(@Valid User user,
                             BindingResult result,
                             RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("nickname", user.getNickname());
            redirectAttrs.addFlashAttribute("email", user.getEmail());
            redirectAttrs.addFlashAttribute("username", user.getUsername());

            List<FieldError> errList = result.getFieldErrors();
            for (FieldError err : errList) {
                // 보다 구체적인 에러 메시지 전달을 위해 메시지 코드를 사용할 수 있음
                redirectAttrs.addFlashAttribute("error", err.getDefaultMessage());
            }

            return "redirect:/user/register";
        }

        int cnt = userService.register(user);
        if (cnt == 1) {
            return "redirect:/Home";
        } else {
            // 회원가입 실패 시 처리할 로직 추가 가능
            return "redirect:/user/register";
        }
    }

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(userValidator);
    }

    @GetMapping("/login")
    public void login(){}

    @PostMapping("/login")
    public String login(String email, String password, Model model) {
        System.out.println("출력 확인 제발");
        try {
            Authentication authentication = userService.authenticate(email, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("-----------------------------" + email);
            return "redirect:/Home";
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            model.addAttribute("errorMessage", "이메일 또는 비밀번호가 올바르지 않습니다.");
            return "user/login";
        }
    }

    @PostMapping("/loginError")
    public String loginError() {
        return "user/login";
    }

    @RequestMapping("/rejectAuth")
    public String rejectAuth() {
        return "common/rejectAuth";
    }
}
