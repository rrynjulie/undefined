package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Reservation;

import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;
import com.lec.spring.service.LodgingService;
import com.lec.spring.service.ReservationService;
import com.lec.spring.service.RoomService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.security.Principal;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final LodgingService lodgingService;
    private final UserService userService;

    @Autowired
    public ReservationController(ReservationService reservationService, LodgingService lodgingService, UserService userService) {
        this.reservationService = reservationService;
        this.lodgingService = lodgingService;
        this.userService = userService;
    }


    // 숙소 예약 폼 페이지 보여주기
    // 숙소 예약 폼 페이지 보여주기
    @GetMapping("/lodging/reservation/form")
    public String showReservationForm(@RequestParam("lodgingId") Long lodgingId, Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // 인증되지 않은 사용자 처리
            return "/Home"; // 로그인 페이지로 리다이렉트 또는 예외 처리
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
        model.addAttribute("reservation", new Reservation());

        return "lodging/LodgingBooking";
    }
}
