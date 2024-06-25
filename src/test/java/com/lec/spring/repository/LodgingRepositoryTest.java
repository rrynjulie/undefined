package com.lec.spring.repository;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LodgingRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void lodgingRepoTest() {
        ProviderRepository providerRepository = sqlSession.getMapper(ProviderRepository.class);

        Long userId = 1L;
        System.out.printf("[userId가 %d인 lodging Table 목록]\n", userId);
        providerRepository.findByUserId(1L).forEach(System.out::println);
        System.out.println();
    }

    @Test
    void lodging(){
        LodgingRepository lodgingRepository = sqlSession.getMapper(LodgingRepository.class);
        lodgingRepository.findLodgingById(1L).forEach(System.out::println);
        System.out.println();
    }
}