package com.lec.spring.service;

import com.lec.spring.domain.Booking;
import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    int createBooking(Booking booking);

    Booking findBookingById(Long bookingId);

    List<Booking> findBooksByRoomId(Long roomId);

    List<Booking> findBooksByUserId(Long userId);

    int deleteBooking(String userId, String bookingId);

    int bookingCount(Long roomId, LocalDate bookingStartDate, LocalDate bookingEndDate);

    void deleteBookingsByRoomId(int roomId);
}