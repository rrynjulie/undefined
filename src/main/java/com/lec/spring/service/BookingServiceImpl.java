package com.lec.spring.service;

import com.lec.spring.domain.Booking;
import com.lec.spring.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public int createBooking(Booking booking) {
        return bookingRepository.createBooking(booking);
    }

    @Override
    public List<Booking> findBooksByRoomId(Long roomId) {
        return bookingRepository.findBooksByRoomId(roomId);
    }

    @Override
    public List<Booking> findBooksByUserId(Long userId) {
        return bookingRepository.findBooksByUserId(userId);
    }

    @Override
    public int deleteBooking(Booking booking) {
        return bookingRepository.deleteBooking(booking);
    }
}