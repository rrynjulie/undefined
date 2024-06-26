package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.domain.UserAuthority;
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

    User getUserById(int userId);
    void updateUser(Long userId, String nickname, String password, String email, String phone);


    List<UserAuthority> getAllUserAuthorities();

    boolean checkPassword(Long userId, String currentPassword);
}
