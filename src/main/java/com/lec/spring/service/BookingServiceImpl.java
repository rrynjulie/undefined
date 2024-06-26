package com.lec.spring.service;

import com.lec.spring.domain.Booking;
import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;
import com.lec.spring.repository.BookingRepository;
import com.lec.spring.repository.ProviderRepository;
import com.lec.spring.repository.RoomRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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
        return bookingRepository.createBooking(booking);
    }

    @Override
    public Booking findBookingByBookingId(Long bookingId) {
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
    public int deleteByBookingId(Long bookingId) {
        int result = 0;

        Booking booking = bookingRepository.findByBookingId(bookingId);
        if(booking != null){
            result = bookingRepository.deleteBooking(booking);
        }

        return result;
    }

    @Override
    public LocalDate getBookingStartDate(Long bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId);
        return booking.getBookingStartDate();
    }


}