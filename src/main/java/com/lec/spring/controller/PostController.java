package com.lec.spring.controller;

import com.lec.spring.domain.Post;
import com.lec.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mypage/customer")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/PostCreate")
    public String postCreate(Model model) {
        model.addAttribute("post", new Post());
        return "/mypage/customer/PostCreate";
    }

//    오류 때문에 잠시 주석처리
//    @PostMapping("/PostCreate")
//    public String postCreateOk(Post post, BindingResult result) {
//        if (result.hasErrors()) {
//            return "mypage/customer/PostCreate";
//        }
//        postService.createPost(post);
//        return "redirect:/Home";
//    }
}