package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.*;

import com.lec.spring.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.awt.print.Book;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

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
            return "redirect:/login"; // 로그인 페이지로 리다이렉트 또는 예외 처리
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
    public String lodgingBookingOk(Booking booking,
                                   Model model,
                                   Principal principal) {
        booking.setBookingTime(LocalDateTime.now());
//        // 사용자 이름 설정
//        String userName = principal.getName(); // 사용자 이름 가져오기
//        booking.setVisitorName(userName);

        model.addAttribute("result", bookingService.createBooking(booking));

        // 예약 완료 페이지로 이동
        return "LodgingBookingOk";
    }

    @GetMapping("/mypage/provider/ProvBookingList/{userId}")
    public void provBookingList(Model model) {
    }
}