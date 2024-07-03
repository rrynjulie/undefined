package com.lec.spring.repository;

import com.lec.spring.domain.Booking;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootTest
public class BookingRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void bookingCreateTest() {
        BookingRepository bookingRepository = sqlSession.getMapper(BookingRepository.class);
        System.out.println("예약 생성");

        Booking booking = Booking.builder()
                .bookingTime(LocalDateTime.parse("2024-05-25T14:20:23"))
                .bookingAdult(3)
                .bookingChild(2)
                .visitorName("이재혁")
                .visitorPhoneNum("010-0000-0000")
                .bookingPay(50000)
                .bookingStartDate(LocalDate.parse("2024-06-01"))
                .bookingEndDate(LocalDate.parse("2024-06-15"))
                .roomId(1L)
                .userId(1L)
                .build();

        System.out.println("[생성 전]" + booking);
        bookingRepository.save(booking);
        System.out.println("[생성 후]" + booking);
        System.out.println();
        System.out.println("[예약 리스트]");
        bookingRepository.findBooksByUserId(1L).forEach(System.out::println);
    }

    @Test
    void bookingReadTest() {
        Long userId = 1L;
        BookingRepository bookingRepository = sqlSession.getMapper(BookingRepository.class);
        System.out.println("예약 내역");
        bookingRepository.findBooksByUserId(userId).forEach(System.out::println);
    }

    @Test
    void bookingExceptionControlTest() {
        BookingRepository bookingRepository = sqlSession.getMapper(BookingRepository.class);
        System.out.println("예약 중복 처리 검사");
        Long roomId = 135L;
        LocalDate bookingStartDate = LocalDate.parse("2024-06-13");
        LocalDate bookingEndDate = LocalDate.parse("2024-06-16");
        System.out.println(bookingRepository.bookingCount(roomId, bookingStartDate, bookingEndDate));
    }

}