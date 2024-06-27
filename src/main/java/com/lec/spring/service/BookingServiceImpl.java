package com.lec.spring.service;

import com.lec.spring.domain.*;
import com.lec.spring.repository.*;
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
    public int createBooking(User user, Room room, Booking booking) {
//        user = userRepository.findById(user.getUserId());
//        room = roomRepository.findByRoomId(room.getRoomId());

//        booking.setVisitorName(user.getUsername());
//        booking.setVisitorPhoneNum(user.getPhonenum());
//        booking.setBookingPay(room.getRoomPrice());
//        booking.setBookingStartDate(LocalDate.parse(startDate));
//        booking.setBookingEndDate(LocalDate.parse(endDate));
//        booking.setBookingAdult(adultCount);
//        booking.setBookingChild(childCount);

        return bookingRepository.save(booking);

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
    public int deleteBooking(Long bookingId) {
        int result = 0;

        Booking booking = bookingRepository.findByBookingId(bookingId);
        if(booking != null){
            result = bookingRepository.deleteBooking(booking);
        }

        return result;
    }


}