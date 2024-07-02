package com.lec.spring.util;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class Util {

    public static User getOrSetLoggedUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = getLoggedUser();
            session.setAttribute("user", user);
        }
        model.addAttribute("user", user);
        return user;
    }

    private static User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof PrincipalDetails) {
            return ((PrincipalDetails) principal).getUser();
        } else if (principal instanceof String) {
            return null;
        } else {
            throw new IllegalStateException("사용자 정보를 가져올 수 없습니다.");
        }
    }

}
