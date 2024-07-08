package com.lec.spring.controller;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;
import com.lec.spring.service.*;
import com.lec.spring.util.AuthenticationUtil;
import com.lec.spring.util.U;
import com.lec.spring.util.Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/mypage/provider")
public class ProviderController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PostService postService;

    @GetMapping("/ProvBookingList")
    public String provBookingList(Model model, Authentication authentication, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = U.getLoggedUser();
            session.setAttribute("user", user);
        }
        model.addAttribute("user", user);

        if (authentication == null || !authentication.isAuthenticated()) {
            // 인증되지 않은 사용자 처리
            return "redirect:/user/Login"; // 로그인 페이지로 리다이렉트 또는 예외 처리
        }

        List<ProvLodging> lodgings = providerService.getLodgings(user.getUserId());

        model.addAttribute("lodgings", lodgings);

        return "mypage/provider/ProvBookingList";
    }

    @GetMapping("/ProvBookingList/books")
    public String provBookingListGetBooks(@RequestParam("lodgingId") Long lodgingId, Model model) {
        List<Room> rooms;
        if(lodgingId == null) {
            rooms = null;
        } else {
            rooms = roomService.findRoomsByLodgingId(lodgingId);
            rooms.forEach(room -> {
                room.setBookList(bookingService.findBooksByRoomId(room.getRoomId()));
                room.getBookList().forEach(booking -> {
                    booking.setFormattedPay(DecimalFormat.getInstance().format(booking.getBookingPay()));
                });
            });
        }

        model.addAttribute("rooms", rooms);

        return "mypage/provider/ProvBookingListInner :: bookingList";
    }

//    @GetMapping("/provlodginglist")
//    public String provlodginglist(Model model) {
//        List<ProvLodging> lodgings = providerService.getLodgings();
//        model.addAttribute("lodgings", lodgings);
//        return "mypage/provider/ProvLodgingList";
//    }

    @GetMapping("/provlodginglist")
    public String provlodginglist(Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        System.out.println("세션 userId: " + user.getUserId()); // userId를 콘솔에 출력
        System.out.println("세션 userId: " + 6);
        System.out.println("세션 userId: " + 6);
        System.out.println("세션 userId: " + 6);
        System.out.println("세션 userId: " + 6);
        System.out.println("세션 userId: " + 6);
        System.out.println("세션 userId: " + 6);
        System.out.println("세션 userId: " + 6);
        System.out.println("세션 userId: " + 6);

        List<ProvLodging> lodgings = providerService.getLodgings(user.getUserId());
        System.out.println("가져온 숙소 목록: " + lodgings);

        List<ProvLodging> myLodgings = new ArrayList<>();
        for (ProvLodging item : lodgings) {
            System.out.println("숙소 userId: " + item.getUserId());
            if (item.getUserId() != null && item.getUserId().equals(user.getUserId())) {
                myLodgings.add(item);
            }
        }
        System.out.println("필터링된 숙소 목록: " + myLodgings);

        model.addAttribute("lodgings", myLodgings);
        return "mypage/provider/ProvLodgingList";
    }




    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/provlodgingdetail/{lodgingId}")
    public String provlodgingdetail(@PathVariable Long lodgingId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        ProvLodging lodging = providerService.getAllDetails(lodgingId);
        model.addAttribute("lodging", lodging);
        return "mypage/provider/ProvLodgingDetail";
    }

    @GetMapping("/provlodgingupdate/{lodgingId}")
    public String provLodgingUpdate(@PathVariable Long lodgingId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        ProvLodging lodging = providerService.getAllDetails(lodgingId); // 숙소 정보 가져오기
        model.addAttribute("lodging", lodging); // 모델에 숙소 정보 추가
        return "mypage/provider/ProvLodgingUpdate"; // 숙소 업데이트 페이지로 이동
    }

    @PostMapping("/updateLodging")
    public String updateLodging(@ModelAttribute ProvLodging lodging, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        // lodging 객체에서 lodgingId 추출
        Long lodgingId = lodging.getLodgingId();

        providerService.updateLodging(lodging);
        return "redirect:/mypage/provider/provlodgingdetail/" + lodgingId;
    }

    @PostMapping("/deleteLodging/{lodgingId}")
    public String deleteLodging(@PathVariable int lodgingId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        int result;
        try {
            providerService.deleteLodging(lodgingId);
            result = 1; // 삭제 성공
        } catch (Exception e) {
            result = 0; // 삭제 실패
        }
        model.addAttribute("result", result);
        return "mypage/provider/ProvLodgingDeleteOk";
    }


    @GetMapping("/provlodgingregister")
    public String provlodgingregister(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = U.getLoggedUser();
            session.setAttribute("user", user);
        }
        model.addAttribute("user", user);
        return "mypage/provider/ProvLodgingRegister";
    }

    @PostMapping("/saveLodging")
    public String saveLodging(@ModelAttribute ProvLodging lodging, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        providerService.saveLodging(lodging);
        return "redirect:provlodginglist";
    }

    @PostMapping("/createRoom")
    public String createRoom(@ModelAttribute Room room, Model model, HttpSession session){
        User user = Util.getOrSetLoggedUser(session, model);

        roomService.createRoom(room);
        return "redirect:ProvRoomList";
    }

    @GetMapping("/ProvRoomRegister/{lodgingId}")
    public String provRoomRegister(@PathVariable("lodgingId") Long lodgingId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        ProvLodging lodging = providerService.getAllDetails(lodgingId);
        model.addAttribute("lodging", lodging);
        return "mypage/provider/ProvRoomRegister";
    }

    @PostMapping("/ProvRoomRegister")
    public String provRoomRegisterOk(Room room, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        model.addAttribute("result", roomService.createRoom(room));
        return "mypage/provider/ProvRoomRegisterOk";
    }

    @GetMapping("/ProvRoomList/{userId}")
    public String provRoomList(@PathVariable("userId") Long userId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = U.getLoggedUser();
            session.setAttribute("user", user);
        }
        model.addAttribute("user", user);
        List<ProvLodging> roomList = providerService.getLodgingsAndRoomsByUserId(userId);
        model.addAttribute("rooms", roomList);
        return "mypage/provider/ProvRoomList";
    }

    @GetMapping("/ProvRoomDetail/{roomId}")
    public String provRoomDetail(@PathVariable("roomId") Long roomId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        Room room = roomService.findRoomByRoomId(roomId);
        model.addAttribute("room", room);

        return "mypage/provider/ProvRoomDetail";
    }



    // 수정 폼을 보여주는 메서드
    @GetMapping("/ProvRoomUpdate/{roomId}")
    public String showUpdateForm(@PathVariable Long roomId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        Room room = roomService.findRoomByRoomId(roomId);
        model.addAttribute("room", room);
        return "mypage/provider/ProvRoomUpdate";
    }

    // 수정 처리를 담당하는 메서드
    @PostMapping("/ProvRoomUpdate")
    public String provRoomUpdateOk(@ModelAttribute Room room, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        roomService.updateRoom(room);
        return "redirect:/mypage/provider/ProvRoomDetail/" + room.getRoomId();
    }

    @PostMapping("/deleteRoom/{roomId}")
    public String deleteRoom(@PathVariable int roomId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        System.out.println("try 들어가기 전에 찍기" + roomId);
        int result;
        Long userId = user.getUserId();
        try {
            System.out.println("try 들어가서 찍기" + roomId);

            postService.deletePostsByRoomId(roomId);
            System.out.println("deletePostsByRoomId: " + roomId);
            bookingService.deleteBookingsByRoomId(roomId);
            System.out.println("deleteBookingsByRoomId: " + roomId);
            roomService.deleteRoom(roomId);
            System.out.println("deleteRoom: " + roomId);
            result = 1; // 삭제 성공
        } catch (Exception e) {
            result = 0; // 삭제 실패
        }
        model.addAttribute("result", result);
        model.addAttribute("userId", userId);
        System.out.println("[result] : " + result);
        System.out.println("[user] : " + user);



        AuthenticationUtil.addAuthenticationDetailsToModel(model);
        return "mypage/provider/ProvRoomDeleteOk";
    }


}