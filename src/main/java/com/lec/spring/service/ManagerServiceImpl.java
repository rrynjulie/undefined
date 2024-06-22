package com.lec.spring.service;

import com.lec.spring.domain.Manager;
import com.lec.spring.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) { this.managerRepository = managerRepository; }

    @Override
    public List<Manager> getAllUsers(){
        return managerRepository.findAll();
    }
}