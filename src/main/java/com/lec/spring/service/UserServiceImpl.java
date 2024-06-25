package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isExist(String nickname) {
        User user = findByNickname(nickname);
        return user != null;
    }

    @Override
    @Transactional
    public int register(User user) {
        // 사용자 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 사용자 정보 저장
        userRepository.save(user);

        // 기본 권한 설정
        Authority authority = new Authority();
        authority.setAuthority(1);
        authority.setUser(user);
        authority.setUserId(user.getUserId()); // user의 ID를 Authority에 설정

        // 권한 저장
        authorityRepository.save(authority);

        // 유저에 권한 추가
        user.getAuthorities().add(authority);
//        userRepository.save(user);

        return 1; // 성공적으로 저장된 경우 1을 반환
    }

    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        User user = userRepository.findById(id);
        return authorityRepository.findByUser(user);
    }

    @Override
    public Authentication authenticate(String email, String password) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }


//    @Override
//    public Authentication authenticate(String nickname, String password) {
//        User user = findByNickname(nickname);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user;
//        }
//        return null;
//    }


}











