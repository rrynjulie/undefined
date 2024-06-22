package com.lec.spring.controller;


import com.lec.spring.service.LodgingService;
import com.lec.spring.domain.Lodging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lodging")
public class LodgingController {
    private final LodgingService lodgingService;

    @Autowired
    public LodgingController(LodgingService lodgingService) {
        this.lodgingService = lodgingService;
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
}
