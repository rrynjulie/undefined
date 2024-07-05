package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.domain.UserAuthority;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    User findByNickname(String nickname);

    User findByEmail(String email);

    boolean isExistNickname(String nickname);
    boolean isExistPhonenum(String phonenum);

    int register(User user);

    List<Authority> selectAuthoritiesById(Long userId);

    Authentication authenticate(String nickname, String password);

    User findByUsername(String username);


    void logout();

    User getUserById(int userId);
    void updateUser(Long userId, String nickname, String password, String email, String phone);

    void deleteUser(Long userId);

    void deleteUserReferences(Long userId);

    void deleteUserAndReferences(Long userId);

    void deleteLodging(Long userId);
    void deleteUserAuthority(Long userId);
    void deleteLikes(Long userId);
    void deletePosts(Long userId);
    void deleteComments(Long userId);
    void deleteBookings(Long userId);

    List<UserAuthority> getAllUserAuthorities();

    boolean checkPassword(Long userId, String currentPassword);
    boolean checkingPassword(String nickname, String currentPassword);

    User findByProviderId(String providerId);

    boolean emailExists(String email);

    User findUserById(Long userId);
}
