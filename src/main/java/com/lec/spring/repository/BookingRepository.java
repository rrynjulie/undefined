package com.lec.spring.repository;

import com.lec.spring.domain.Booking;
import com.lec.spring.service.BookingService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BookingRepository {
    int save(Booking booking);

    Booking findByBookingId(Long bookingId);

    List<Booking> findBooksByRoomId(Long roomId);

    List<Booking> findBooksByUserId(Long userId);

//    @Delete("DELETE FROM booking WHERE user_id = #{userId} AND booking_id = #{bookingId}")
    int deleteBooking(@Param("userId") String userId, @Param("bookingId") String bookingId);

    int bookingCount(Long roomId, LocalDate bookingStartDate, LocalDate bookingEndDate);
}