package com.lec.spring.repository;

import com.lec.spring.domain.Manager;

import java.util.List;


public interface ManagerRepository {

    // 모든 사용자 조회
    List<Manager> findAll();

    // 아이디로 사용자 조회
//    Optional<User> findById(Long id);

    // 사용자 저장
//    User save(User user);

    //아이디로 사용자 삭제
//    void deleteById(Long id);



}
