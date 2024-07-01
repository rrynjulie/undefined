package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.*;

import com.lec.spring.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

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
                                 Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // 인증되지 않은 사용자 처리
            return "redirect:/user/login"; // 로그인 페이지로 리다이렉트 또는 예외 처리
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof PrincipalDetails) {
            PrincipalDetails principalDetails = (PrincipalDetails) principal;
            User user = principalDetails.getUser();
            model.addAttribute("user", user);
        } else if (principal instanceof String) {
            String username = (String) principal;
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        } else {
            // 다른 타입에 대한 처리
            throw new IllegalStateException("Unknown principal type: " + principal.getClass());
        }

        Lodging lodging = lodgingService.getLodgingById(lodgingId);
        model.addAttribute("lodging", lodging);

        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);

        String formattedPay = DecimalFormat.getInstance().format(room.getRoomPrice());
        model.addAttribute("formattedPay", formattedPay);

        return "lodging/LodgingBooking";
    }

    @PostMapping("/lodging/LodgingBooking")
    public String createBooking(@RequestParam("visitorName") String visitorName,
                                @RequestParam("visitorPhoneNum") String visitorPhoneNum,
                                @RequestParam("bookingPay") int bookingPay,
                                @RequestParam("bookingStartDate") LocalDate bookingStartDate,
                                @RequestParam("bookingEndDate") LocalDate bookingEndDate,
                                @RequestParam("bookingAdult") int bookingAdult,
                                @RequestParam("bookingChild") int bookingChild,
                                @RequestParam("roomId") Long roomId,
                                @RequestParam("userId") Long userId,
                                Authentication authentication,
                                Model model) {

        Object principal = authentication.getPrincipal();
        User user;
        if (principal instanceof PrincipalDetails) {
            user = ((PrincipalDetails) principal).getUser();
        } else if (principal instanceof String) {
            user = userService.findByUsername((String) principal);
        } else {
            throw new IllegalStateException("Unknown principal type: " + principal.getClass());
        }


        Room room = roomService.findByRoomId(roomId);

        if (room == null) {
            throw new IllegalArgumentException("Room not found for roomId: " + roomId);
        }

        int conflictingReservations = bookingService.bookingCount(roomId, bookingStartDate, bookingEndDate);
        if (conflictingReservations > 0) {
            // 예약 불가 메시지를 전달
            model.addAttribute("error", "예약불가");
            model.addAttribute("user", user);
            return "lodging/LodgingBookingError";
        }

        Booking booking = Booking.builder()
                .visitorName(visitorName)
                .visitorPhoneNum(visitorPhoneNum)
                .bookingPay(bookingPay)
                .bookingStartDate(bookingStartDate)
                .bookingEndDate(bookingEndDate)
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