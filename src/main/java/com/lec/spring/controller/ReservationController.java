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
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final LodgingService lodgingService;
    private final RoomService roomService;
    private final UserService userService;

    @Autowired
    public ReservationController(ReservationService reservationService, LodgingService lodgingService, RoomService roomService, UserService userService) {
        this.reservationService = reservationService;
        this.lodgingService = lodgingService;
        this.roomService = roomService;
        this.userService = userService;
    }

    // 숙소 예약 폼 페이지 보여주기
    @GetMapping("/lodging/LodgingBooking")
    public String ShowReservationForm(@RequestParam("lodgingId") Long lodgingId,
                                      @RequestParam(value = "roomId", required = false) Long roomId,
                                      Model model,
                                      Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            // 인증되지 않은 사용자 처리
            return "redirect:/login"; // 로그인 페이지로 리다이렉트 또는 예외 처리
        }

        Object principal = authentication.getPrincipal();
        User user = null;
        if (principal instanceof PrincipalDetails) {
            PrincipalDetails principalDetails = (PrincipalDetails) principal;
            user = principalDetails.getUser();
            model.addAttribute("user", user);
        } else if (principal instanceof String) {
            String username = (String) principal;
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        } else {
            // 다른 타입에 대한 처리
            throw new IllegalStateException("Unknown principal type: " + principal.getClass());
        }

        // 사용자 정보 모델에 추가
        model.addAttribute("phonenum", user.getPhonenum());

        Lodging lodging = lodgingService.getLodgingById(lodgingId);
        model.addAttribute("lodging", lodging);

        // Lodging ID에 해당하는 Room 목록 조회
        List<Room> rooms = roomService.findRoomsByLodgingId(lodgingId);
        model.addAttribute("rooms", rooms);

        // 선택한 객실 정보 조회
        Room selectedRoom = null;
        if (roomId != null && roomId != 0) {
            selectedRoom = roomService.getRoomById(roomId);
        }
        model.addAttribute("selectedRoom", selectedRoom);

        model.addAttribute("reservation", new Reservation());

        return "lodging/LodgingBooking";
    }


    @PostMapping("/lodging/saveReservation")
    public String saveReservation(@Valid @ModelAttribute("reservation") Reservation reservation,
                                  BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "lodging/LodgingBooking";
        }

        // 세션에서 예약 인원과 날짜 정보 가져오기
        LocalDateTime startDate = (LocalDateTime) session.getAttribute("startDate");
        LocalDateTime endDate = (LocalDateTime) session.getAttribute("endDate");

        // 예약 객체에 세션에서 가져온 값들 설정
        reservation.setReservationStartDate(startDate);
        reservation.setReservationEndDate(endDate);

        // Spring Security를 사용하여 현재 인증된 사용자의 userName 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); // 현재 인증된 사용자의 userName 가져오기

        // UserService를 사용하여 현재 사용자의 정보를 가져오기
        User currentUser = userService.findByUsername(userName);
        if (currentUser == null) {
            bindingResult.reject("userNotFound", "User not found.");
            return "lodging/LodgingBooking";
        }

        // visitorName과 visitorPhoneNum 설정
        reservation.setVisitorName(currentUser.getUsername());
        reservation.setVisitorPhoneNum(currentUser.getPhonenum());

        // 비즈니스 로직 처리 (예약 저장)
        reservationService.saveReservation(reservation);

        // 성공 메시지 추가
        model.addAttribute("message", "Reservation successful!");
        return "lodging/LodgingBookingOk";
    }
}