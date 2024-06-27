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
import java.time.LocalDate;

@Controller
public class BookingController {

    private BookingService bookingService;
    private LodgingService lodgingService;
    private RoomService roomService;
    private UserService userService;

    @Autowired
    public BookingController(
            BookingService bookingService
            , LodgingService lodgingService
            , RoomService roomService
            , UserService userService
    ) {
        this.bookingService = bookingService;
        this.lodgingService = lodgingService;
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

        return "lodging/LodgingBooking";
    }

    @PostMapping("/lodging/LodgingBooking")
    public String createBooking(@RequestParam("visitorName") String visitorName,
                                @RequestParam("visitorPhoneNum") String visitorPhoneNum,
                                @RequestParam("bookingPayType") String bookingPayType,
                                @RequestParam("bookingPay") int bookingPay,
                                @RequestParam("bookingStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingStartDate,
                                @RequestParam("bookingEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingEndDate,
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
            // Handle case where room with given id is not found
            throw new IllegalArgumentException("Room not found for roomId: " + roomId);
        }

        Booking booking = Booking.builder()
                .visitorName(visitorName)
                .visitorPhoneNum(visitorPhoneNum)
                .bookingPayType(Booking.BookingPayType.valueOf("카드"))
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

    @GetMapping("/mypage/provider/ProvBookingList/{userId}")
    public void provBookingList(Model model) {
    }
}