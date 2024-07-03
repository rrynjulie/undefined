package com.lec.spring.controller;

import com.lec.spring.domain.Love;
import com.lec.spring.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/love")
public class LoveController {

    private LoveService loveService;

    @Autowired
    public LoveController(LoveService loveService) {
        this.loveService = loveService;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addLove(@RequestParam Long userId, @RequestParam Long lodgingId) {
        loveService.addLove(userId, lodgingId);
        return "{\"success\": true}";
    }

    @PostMapping("/remove")
    @ResponseBody
    public String removeLove(@RequestParam Long userId, @RequestParam Long lodgingId) {
        loveService.removeLove(userId, lodgingId);
        return "{\"success\": true}";
    }
}
