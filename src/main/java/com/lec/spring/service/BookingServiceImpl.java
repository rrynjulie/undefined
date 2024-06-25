package com.lec.spring.service;

import com.lec.spring.domain.Booking;
import com.lec.spring.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking makeBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking getBookingDetails(Long bookingId) {
        return null;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return null;
    }
}