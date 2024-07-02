package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.User;
import com.lec.spring.domain.UserAuthority;
import com.lec.spring.service.LodgingService;
import com.lec.spring.service.LodgingServiceImpl;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import com.lec.spring.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private LodgingService lodgingService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/Home";
    }

    @RequestMapping("/Home")
    public void home(HttpSession session, Model model) {
        User user = Util.getOrSetLoggedUser(session, model);

        List<UserAuthority> userAuthorities = userService.getAllUserAuthorities();
        model.addAttribute("userAuthorities", userAuthorities);
        System.out.println("user: " + user);

        List<Lodging> lodgings = lodgingService.getCountHotelBookingWithRatings();
        model.addAttribute("lodgings",lodgings);
        System.out.println("home에서 출력 " + lodgings);

    }

    @RequestMapping("/lodging/LodgingSearch")
    public String lodgingSearch() {
        return "lodging/LodgingSearch";
    }

    @RequestMapping("/ticket/TicketCheck")
    public String ticketCheck() {
        return "ticket/TicketCheck";
    }

    @RequestMapping("/user/Login")
    public String login() {
        return "user/Login";
    }

    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
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
