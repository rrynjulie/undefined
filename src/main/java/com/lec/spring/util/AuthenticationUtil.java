package com.lec.spring.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationUtil {

    public static void addAuthenticationDetailsToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorityIds = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .collect(Collectors.toList());
        model.addAttribute("authorityIds", authorityIds);
    }
}