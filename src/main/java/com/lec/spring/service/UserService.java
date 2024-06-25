package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    User findByNickname(String nickname);

    User findByEmail(String email);

    boolean isExist(String nickname);

    int register(User user);

    List<Authority> selectAuthoritiesById(Long userId);

    Authentication authenticate(String nickname, String password);

    User findByUsername(String username);


    void logout();
}
