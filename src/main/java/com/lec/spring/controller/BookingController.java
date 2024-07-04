package com.lec.spring.controller;

import com.lec.spring.domain.*;

import com.lec.spring.service.*;
import com.lec.spring.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Controller
public class BookingController {

    private BookingService bookingService;
    private LodgingService lodgingService;
    private ProviderService providerService;
    private RoomService roomService;
    private UserService userService;

    @Autowired
    public BookingController(
            BookingService bookingService
            , LodgingService lodgingService
            , ProviderService providerService
            , RoomService roomService
            , UserService userService
    ) {
        this.bookingService = bookingService;
        this.lodgingService = lodgingService;
        this.providerService = providerService;
        this.roomService = roomService;
        this.userService = userService;
    }

    @GetMapping("/lodging/LodgingBooking")
    public String lodgingBooking(@RequestParam("lodgingId") Long lodgingId,
                                 @RequestParam("roomId") Long roomId,
                                 Model model,
                                 HttpSession session,
                                 Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // 인증되지 않은 사용자 처리
            return "redirect:/user/Login"; // 로그인 페이지로 리다이렉트 또는 예외 처리
        }

        User user = Util.getOrSetLoggedUser(session, model);

        Lodging lodging = lodgingService.getLodgingById(lodgingId);
        model.addAttribute("lodging", lodging);

        Room room = roomService.findRoomByRoomId(roomId);
        model.addAttribute("room", room);

        String formattedPay = DecimalFormat.getInstance().format(room.getRoomPrice());
        model.addAttribute("formattedPay", formattedPay);

        return "lodging/LodgingBooking";
    }

    @PostMapping("/lodging/LodgingBooking")
    public String createBooking(@RequestParam("visitorName") String visitorName,
                                @RequestParam("visitorPhoneNum") String visitorPhoneNum,
                                @RequestParam("bookingPay") int bookingPay,
                                @RequestParam("bookingStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String bookingStartDate,
                                @RequestParam("bookingEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String bookingEndDate,
                                @RequestParam("bookingAdult") int bookingAdult,
                                @RequestParam("bookingChild") int bookingChild,
                                @RequestParam("roomId") Long roomId,
                                @RequestParam("userId") Long userId,
                                Model model, HttpSession session, Authentication authentication) {

        User user = Util.getOrSetLoggedUser(session, model);

        Room room = roomService.findRoomByRoomId(roomId);

        if (room == null) {
            throw new IllegalArgumentException("Room not found for roomId: " + roomId);
        }

        int conflictingReservations = bookingService.bookingCount(roomId, LocalDate.parse(bookingStartDate), LocalDate.parse(bookingEndDate));
        if (conflictingReservations > 0) {
            // 예약 불가 메시지를 전달
            model.addAttribute("error", "이미 예약된 객실입니다.");
            model.addAttribute("user", user);
            return "lodging/LodgingBookingError";
        }

        Booking booking = Booking.builder()
                .visitorName(visitorName)
                .visitorPhoneNum(visitorPhoneNum)
                .bookingPay(bookingPay)
                .bookingStartDate(LocalDate.parse(bookingStartDate))
                .bookingEndDate(LocalDate.parse(bookingEndDate))
                .bookingAdult(bookingAdult)
                .bookingChild(bookingChild)
                .userId(userId)
                .roomId(roomId)
                .build();
        model.addAttribute("result", bookingService.createBooking(booking));
        model.addAttribute("user", user);

        return "lodging/LodgingBookingOk";
    }
}