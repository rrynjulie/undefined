package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Booking;

import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;
import com.lec.spring.service.LodgingService;
import com.lec.spring.service.BookingService;
import com.lec.spring.service.RoomService;
import com.lec.spring.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("booking", new Booking());

        return "lodging/LodgingBooking";
    }

    @GetMapping("/mypage/provider/ProvBookingList/{userId}")
    public void provBookingList(Model model) {

    }
}