package com.lec.spring.controller;


import com.lec.spring.domain.Post;
import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import com.lec.spring.service.LodgingService;
import com.lec.spring.domain.Lodging;
import com.lec.spring.service.PostService;
import com.lec.spring.service.ProviderService;
import com.lec.spring.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lodging")
public class LodgingController {
    private final LodgingService lodgingService;
    private ProviderService providerService;
    private RoomService roomService;
    private PostService postService;

    @Autowired
    public LodgingController(LodgingService lodgingService, ProviderService providerService, RoomService roomService, PostService postService) {
        this.lodgingService = lodgingService;
        this.providerService = providerService;
        this.roomService = roomService;
        this.postService = postService;
    }

    @GetMapping("/LodgingSearch")
    public String showLodgingSearch() {
        return "lodging/LodgingSearch";
    }

    @PostMapping("/LodgingList")
    public String handleSearchRequest(@RequestParam("location") String location, Model model) {
        List<Lodging> lodging = lodgingService.getLodgingsByLocation(location);
        model.addAttribute("lodging", lodging);
        model.addAttribute("location", location); // location 정보를 모델에 추가
        return "lodging/LodgingList";
    }

    @GetMapping("/LodgingList")
    public void showLodgingListPage() {
    }

    @GetMapping("/LodgingDetail/{lodgingId}")
    public String getLodgingDetail(@PathVariable("lodgingId") Long lodgingId, Model model) {
        List<Lodging> lodging = lodgingService.lodgingDetail(lodgingId);
        List<Lodging> lodgingName = lodgingService.lodgingName(lodgingId);
        model.addAttribute("lodging", lodging);
        model.addAttribute("lodgingName", lodgingName);
        return "lodging/LodgingDetail";
    }


    @GetMapping("/filter")
    @ResponseBody
    public List<Lodging> filterLodgingsByLocationAndType(@RequestParam("location") String location, @RequestParam("type") String type) {
        return lodgingService.getLodgingsByLocationAndType(location, type);
    }

    @GetMapping("/PostList/{lodgingId}")
    public String postList (@PathVariable("lodgingId") Long lodgingId, Model model) {
        List<Lodging> lodgingss = lodgingService.getPostList(lodgingId);
        model.addAttribute("lodgingss", lodgingss);
        return "lodging/PostList";
    }

    @GetMapping("/RoomDetail/{lodgingId}/{roomId}")
    public String RoomDetail(@PathVariable("lodgingId") int lodgingId, @PathVariable("roomId") Long roomId, Model model) {
        ProvLodging lodging = providerService.getLodgingById(lodgingId);
        Room room = roomService.findByRoomId(roomId);
        List<Post> postList = postService.findPostsByRoomId(roomId);
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
