package com.lec.spring.service;

import com.lec.spring.domain.Booking;
import com.lec.spring.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public Booking makeBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingDetails(Long bookingId) {
        return bookingRepository.getBookingDetails(bookingId);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}