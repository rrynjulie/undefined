package com.lec.spring.repository;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BookingRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void bookingReadTest() {
        Long userId = 1L;
        BookingRepository bookingRepository = sqlSession.getMapper(BookingRepository.class);
        System.out.println("예약 내역");
        bookingRepository.findBooksByUserId(userId).forEach(System.out::println);
    }

}