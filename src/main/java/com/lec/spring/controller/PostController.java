package com.lec.spring.controller;

import com.lec.spring.domain.Post;
import com.lec.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mypage/customer")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/PostCreate")
    public void postCreate() {
    }

    @PostMapping("/PostCreate")
    public String submitReview(@RequestParam("rating") Integer rating,
                               @RequestParam("post_text") String postText) {
        Post post = new Post();
        post.setPostGrade(rating);
        post.setPostText(postText);
        // userId와 reservationId 등은 필요에 따라 설정

        postService.createPost(post);
        return "redirect:/mypage/customer/post";
    }
}