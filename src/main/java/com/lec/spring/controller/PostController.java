package com.lec.spring.controller;

import com.lec.spring.domain.Post;
import com.lec.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/mypage/customer")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/PostList/{userId}")
    public String postList(@PathVariable("userId") Long userId, Model model) {
        List<Post> userPost = postService.allPostUserId(userId);
        model.addAttribute("userPost", userPost);
        return "/mypage/customer/PostList";
    }

    @GetMapping("/PostUpdate/{userId}/{postId}")
    public String postUpdate(@PathVariable Long userId, @PathVariable Long postId, Model model) {
        List<Post> userPostUpdate = postService.allPostUser(userId, postId);
        model.addAttribute("userPostUpdate", userPostUpdate);
        return "/mypage/customer/PostUpdate";
    }

    @PostMapping("/PostUpdate")
    public String postUpdate(@ModelAttribute Post post) {
        int result = postService.allPostUpdate(post);
        if (result > 0) {
            return "redirect:/mypage/customer/PostList/" + post.getUserId();
        } else {
            return "redirect:/mypage/customer/PostUpdate/" + post.getUserId() + "/" + post.getPostId();
        }
    }

    @PostMapping("/PostDelete")
    public String postDelete(Post post, Model model) {
        int result = postService.allPostDelete(post);
        model.addAttribute("result", result);
        return "redirect:/mypage/customer/PostList/" + post.getUserId();
    }

    @GetMapping("/PostCreate/{userId}")
    public String postCreate(@ModelAttribute Long userId, Model model) {
        Post newPost = new Post();
        model.addAttribute("newPost", newPost);
        return "mypage/customer/PostCreate";
    }
    @PostMapping("/PostCreate")
    public String savePost(@ModelAttribute Post post) {
        int result = postService.savePost(post);
        if (result > 0) {
            return "redirect:/mypage/customer/PostList/" + post.getUserId();
        } else {
            return "redirect:/mypage/customer/PostCreate" + post.getUserId();
        }
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