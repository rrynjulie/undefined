package com.lec.spring.repository;

import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;


public interface UserRepository {

    User findById(Long userId);

    // 특정 email 의 user return
    User findByNickname(String nickname);
    User findByEmail(String email);

    int save(User user);

    int update(User user);


}