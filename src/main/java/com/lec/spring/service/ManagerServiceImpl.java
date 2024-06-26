package com.lec.spring.service;



import com.lec.spring.domain.UserAuthority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    // ManagerRepository 기본생성자 생성 후 초기화
    private final ManagerRepository managerRepository;

    // 생성자를 통해 ManagerRepository 인스턴스를 주입받는다.
    public ManagerServiceImpl(ManagerRepository managerRepository) { this.managerRepository = managerRepository; }


    @Override
    public List<User> getAllUsers(){
        return managerRepository.findAll();
    }

    @Override
    public List<UserAuthority> getAllUserAuthorities() {
        return managerRepository.findAllUserAuthority();
    }

    @Override
    public List<User> getAllUsersWithAuthorities() {
        return managerRepository.findAllWithAuthorities();
    }

}