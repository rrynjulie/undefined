package com.lec.spring.repository;

import com.lec.spring.domain.Reservation;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ReservationRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    @Transactional
    void testInsertReservation() {
        ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);

        // Given
        Reservation reservation = Reservation.builder()
                .reservationId(3)
                .reservationTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .reservationAdult(4)
                .reservationChild(2)
                .visitorName("먕")
                .visitorPhoneNum("010-1212-3333")
                .reservationPayType("카드")
                .reservationPay(230000)
                .reservationFinalPay(200000)
                .reservationStartDate(Date.from(LocalDateTime.of(2024, 11, 1, 13, 0).atZone(ZoneId.systemDefault()).toInstant()))
                .reservationEndDate(Date.from(LocalDateTime.of(2024, 6, 5, 11, 0).atZone(ZoneId.systemDefault()).toInstant()))
                .roomId(3)
                .userId("3")
                .build();
        // When
        reservationRepository.insert(reservation);

        // Then
        assertNotNull(reservation.getReservationId()); // 아이디가 생성되었는지 확인 (Auto Increment id 가정)
        System.out.println("[예약 입력받기]: " + reservation);
    }

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
