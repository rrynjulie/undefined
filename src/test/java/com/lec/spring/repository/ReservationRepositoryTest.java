package com.lec.spring.repository;

import com.lec.spring.domain.Reservation;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ReservationRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void testFindAllReservations() {
        ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);

        System.out.println("[모든 예약 정보]");
        reservationRepository.findAll().forEach(System.out::println);
    }

    @Test
    void testFindReservationById() {
        ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
        int reservationId = 1;

        System.out.println("[ID가 1인 예약 정보]");
        Reservation reservation = reservationRepository.findById(reservationId);
        System.out.println(reservation);
    }

}
