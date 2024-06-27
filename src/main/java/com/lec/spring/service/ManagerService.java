    package com.lec.spring.service;

    // User 도메인과 연결

    import com.lec.spring.domain.UserAuthority;
    import com.lec.spring.domain.User;

    import java.util.List;

    public interface ManagerService {

        //모든 사용자 조회
        List<User> getAllUsers();

        //특정 id에 따른 권한정보(들)
        List<UserAuthority> getAllUserAuthorities();

        //사용자와 권한정보 테이블 조인
        List<User> getAllUsersWithAuthorities();

        // 유저 권한 추가
        void addUserAuthority(Long userId, Long authorityId);
    }
