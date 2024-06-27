package com.lec.spring.repository;


import com.lec.spring.domain.UserAuthority;
import com.lec.spring.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ManagerRepository {

    // 모든 사용자 조회
    List<User> findAll();

    // 권한정보 불러오기
    List<UserAuthority> findAllUserAuthority();


    //사용자와 권한정보 테이블 조인
    List<User> findAllWithAuthorities();


    // 권한 추가 (insert)
    void addUserAuthority(UserAuthority userAuthority);

    // 특정 권한 제거 (delete)
    void removeUserAuthority(UserAuthority userAuthority);

}
