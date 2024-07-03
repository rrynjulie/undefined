package com.lec.spring.controller;


import com.lec.spring.domain.*;
import com.lec.spring.service.*;
import com.lec.spring.util.Util;
import jakarta.servlet.http.HttpSession;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.lec.spring.util.U.getLoggedUser;

@Controller
@RequestMapping("/lodging")
public class LodgingController {
    private final LodgingService lodgingService;
    private ProviderService providerService;
    private RoomService roomService;
    private final PostService postService;

    private BookingService bookingService;
    private LoveService loveService;

    @Autowired
    public LodgingController(LodgingService lodgingService, ProviderService providerService, RoomService roomService, PostService postService, BookingService bookingService, LoveService loveService) {
        this.lodgingService = lodgingService;
        this.providerService = providerService;
        this.roomService = roomService;
        this.postService = postService;
        this.bookingService = bookingService;
        this.loveService = loveService;
    }

    @GetMapping("/LodgingSearch")
    public String showLodgingSearch() {
        return "lodging/LodgingSearch";
    }

    @GetMapping("/LodgingList")
    public void showLodgingListPage() {
    }

    @PostMapping("/LodgingList")
    public String handleSearchRequest(@RequestParam("location") String location, Model model) {
        List<Lodging> lodgings = lodgingService.getLodgingsByLocation(location);

        // 각 숙소에 대해 평균 점수를 설정
        for (Lodging lodging : lodgings) {
            Double avgPostGrade = lodgingService.getAvgPostGrade(lodging.getLodgingId());
            Integer totalPosts = lodgingService.getTotalPosts(lodging.getLodgingId());
            lodging.setAvgPostGrade(avgPostGrade != null ? avgPostGrade : 0.0);
            lodging.setTotalPosts(totalPosts != null ? totalPosts : 0);
        }

        model.addAttribute("lodging", lodgings);
        model.addAttribute("location", location); // location 정보를 모델에 추가
        return "lodging/LodgingList";
    }

    @PostMapping("/LodgingList/price")
    public String filterPrice(@RequestParam("location") String location,
                              @RequestParam("price") String price,
                              @RequestParam(value = "type", required = false) String type,
                              Model model) {
        List<Lodging> lodgings;
        if (type == null || type.isEmpty() || type.equals("전체")) {
            if (price.equals("ALL")) {
                lodgings = lodgingService.findLodgingIdASC(location);
            } else if (price.equals("DESC")) {
                lodgings = lodgingService.findLodgingByPriceDESC(location);
            } else {
                lodgings = lodgingService.findLodgingByPriceASC(location);
            }
        } else {
            if (price.equals("ALL")) {
                lodgings = lodgingService.findLodgingIdASCByType(location, type);
            } else if (price.equals("DESC")) {
                lodgings = lodgingService.findLodgingByLocationAndTypeAndPriceDESC(location, type);
            } else
                lodgings = lodgingService.findLodgingByLocationAndTypeAndPriceASC(location, type);
        }
        addAdditionalInfoToLodgings(lodgings);
        model.addAttribute("lodging", lodgings);
        return "lodging/LodgingList :: #item-list";
    }
    // 추가 정보를 설정하는 메서드
    private void addAdditionalInfoToLodgings(List<Lodging> lodgings) {
        for (Lodging lodging : lodgings) {
            Double avgPostGrade = lodgingService.getAvgPostGrade(lodging.getLodgingId());
            Integer totalPosts = lodgingService.getTotalPosts(lodging.getLodgingId());
            lodging.setAvgPostGrade(avgPostGrade != null ? avgPostGrade : 0.0);
            lodging.setTotalPosts(totalPosts != null ? totalPosts : 0);
            // 필요한 경우 추가 정보 설정
        }
    }

    @GetMapping("/LodgingDetail/{lodgingId}")
    public String getLodgingDetail(@PathVariable("lodgingId") Long lodgingId,
                                   @RequestParam(value = "bookingStartDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingStartDate,
                                   @RequestParam(value = "bookingEndDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingEndDate,
                                   Model model,
                                   HttpSession session,
                                   Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // 인증되지 않은 사용자 처리
            return "redirect:/user/login"; // 로그인 페이지로 리다이렉트 또는 예외 처리
        }

        User user = Util.getOrSetLoggedUser(session, model);

        // 현재 로그인한 사용자 정보를 세션에서 가지고온다.
        System.out.println("세션 userId: " + user.getUserId()); // userId를 콘솔에 출력

        System.out.println("user 정보는 :" + user);

        // 숙소 상세 정보를 가지고온다.
        List<Lodging> lodgings = lodgingService.lodgingDetail(lodgingId);
        List<Lodging> lodgingName = lodgingService.lodgingName(lodgingId);
        List<Post> lodgingPost = postService.findPostByLodgingId(lodgingId);


        for (Lodging lodging : lodgings) {
            Double avgPostGrade = lodgingService.getAvgPostGrade(lodging.getLodgingId());
            Integer totalPosts = lodgingService.getTotalPosts(lodging.getLodgingId());
            lodging.setAvgPostGrade(avgPostGrade != null ? avgPostGrade : 0.0);
            lodging.setTotalPosts(totalPosts != null ? totalPosts : 0);

            int conflictingBookingCount = 0;
            if (bookingStartDate != null && bookingEndDate != null) {
                // 예약 상황을 확인하여 겹치는 예약 수를 구합니다.
                conflictingBookingCount = bookingService.bookingCount(lodging.getRoomId(), bookingStartDate, bookingEndDate);
            }
            lodging.setAvailable(conflictingBookingCount);
        }

        System.out.println("lodging Id : " + lodgingId);
        // 사용자가 해당 숙소를 좋아요했는지 여부 확인
        boolean isLiked = loveService.isUserLikedLodging(user.getUserId(), lodgingId);

        //모델에 필요한 속성들 추가
        model.addAttribute("lodging", lodgings);
        model.addAttribute("lodgingName", lodgingName);
        model.addAttribute("lodgingPost", lodgingPost);
        model.addAttribute("bookingStartDate", bookingStartDate);
        model.addAttribute("bookingEndDate", bookingEndDate);
        model.addAttribute("userId", user.getUserId()); //현재 로그인한 사용자 id를 추가
        model.addAttribute("hasLove", isLiked); // 좋아요 상태를 모델에 추가


        return "lodging/LodgingDetail";
    }

    @PostMapping("/addLove")
    @ResponseBody
    public String addLove(@RequestParam Long userId, @RequestParam Long lodgingId) {
        loveService.addLove(userId, lodgingId);
        return "{\"success\": true}";
    }

    @PostMapping("/removeLove")
    @ResponseBody
    public String removeLove(@RequestParam Long userId, @RequestParam Long lodgingId) {
        loveService.removeLove(userId, lodgingId);
        return "{\"success\": true}";
    }

    @GetMapping("/LodgingPostList/{lodgingId}")
    public String postList (@PathVariable("lodgingId") Long lodgingId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        List<Post> userPosts = postService.findPostByLodgingId(lodgingId);
        Double avgPostGrade = lodgingService.getAvgPostGrade(lodgingId);
        int totalPosts = postService.countAllPostsByLodgingId((long)lodgingId);
        String formattedAvgPostGrade = String.format("%.1f", avgPostGrade);

        model.addAttribute("userPosts", userPosts);
        model.addAttribute("avgPostGrade", formattedAvgPostGrade);
        model.addAttribute("totalPosts", totalPosts);
        return "lodging/LodgingPostList";
    }

    @GetMapping("/RoomDetail/{lodgingId}/{roomId}")
    public String RoomDetail(@PathVariable("lodgingId") Long lodgingId, @PathVariable("roomId") Long roomId, Model model, HttpSession session) {
        User user = Util.getOrSetLoggedUser(session, model);

        ProvLodging lodging = providerService.getAllDetails(lodgingId);
        Room room = roomService.findByRoomId(roomId);
        List<Post> postList = postService.findPostByLodgingId(lodgingId);
        int totalPosts = postService.countAllPostsByLodgingId((long)lodgingId);

        if(postList.size() > 3)
            postList = postList.subList(0, 3);

        model.addAttribute("lodging", lodging);
        model.addAttribute("room", room);
        model.addAttribute("roomPrice", DecimalFormat.getInstance().format(room.getRoomPrice()));
        model.addAttribute("postList", postList);
        model.addAttribute("totalPosts", totalPosts);

        return "lodging/RoomDetail";
    }


}
