package com.lec.spring.service;

// Manager 도메인과 연결
import com.lec.spring.domain.Manager;
import java.util.List;

public interface ManagerService {

    //모든 사용자 조회
    List<Manager> getAllUsers();
}
