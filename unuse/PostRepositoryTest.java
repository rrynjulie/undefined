package com.lec.spring.repository;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void lodgingRepoTest() {
        PostRepository postRepository = sqlSession.getMapper(PostRepository.class);

        Long roomId = 1L;
        System.out.printf("[roomId가 %d인 Post 목록]\n", roomId);
        postRepository.findPostsByRoomId(1L).forEach(System.out::println);
        System.out.println();
    }
}