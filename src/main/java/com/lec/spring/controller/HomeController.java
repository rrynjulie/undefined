package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) {
        return "redirect:/Home";
    }

    @RequestMapping("/Home")
    public void home() {}

    @RequestMapping("/lodging/LodgingSearch")
    public String lodgingSearch() {
        return "lodging/LodgingSearch";
    }

    @RequestMapping("/ticket/TicketCheck")
    public String ticketCheck() {
        return "ticket/TicketCheck";
    }

    @RequestMapping("/user/Login")
    public String login() {
        return "user/Login";
    }
}
