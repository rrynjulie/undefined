package com.lec.spring.repository;

import com.lec.spring.service.LoveService;
import com.lec.spring.service.LoveServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LoveRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    LoveService loveService;
    @Test
    void loveRepoTest() {

    }

    @Test
    void loveServiceTest() {

        Long userId = 1L, lodgingId = 1L;

        System.out.println("[테스트 결과 확인]");
        System.out.println(loveService.isUserLikedLodging(userId, lodgingId));
    }
}