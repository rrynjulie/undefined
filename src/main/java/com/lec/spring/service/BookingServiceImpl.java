package com.lec.spring.service;

import com.lec.spring.domain.*;
import com.lec.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private ProviderRepository providerRepository;
    private RoomRepository roomRepository;

    @Autowired
    public BookingServiceImpl(
            BookingRepository bookingRepository
            , UserRepository userRepository
            , ProviderRepository providerRepository
            , RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public int createBooking(Booking booking) {
        int countBooking = bookingRepository.bookingCount(booking.getRoomId(), booking.getBookingStartDate(), booking.getBookingEndDate());
        if (countBooking > 0) {
            throw new IllegalArgumentException("예약불가");
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking findBookingById(Long bookingId) {
        return bookingRepository.findByBookingId(bookingId);
    }

    @Override
    public List<Booking> findBooksByRoomId(Long roomId) {
        return bookingRepository.findBooksByRoomId(roomId);
    }

    @Override
    public List<Booking> findBooksByUserId(Long userId) {
        List<Booking> bookingList = bookingRepository.findBooksByUserId(userId);
        bookingList.forEach(booking -> {
            User user = userRepository.findById(userId);
            Room room = roomRepository.findByRoomId(booking.getRoomId());
            ProvLodging lodging = providerRepository.findAllDetails(room.getLodgingId());
            booking.setUser(user);
            booking.setRoom(room);
            booking.setLodging(lodging);
        });
        return bookingList;
    }

    @Override
    public int deleteBooking(String userId, String bookingId) {
        return bookingRepository.deleteBooking(userId, bookingId);
    }


    @Override
    public int bookingCount(Long roomId, LocalDate bookingStartDate, LocalDate bookingEndDate) {
        return bookingRepository.bookingCount(roomId, bookingStartDate, bookingEndDate);
    }


}