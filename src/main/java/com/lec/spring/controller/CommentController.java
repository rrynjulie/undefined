package com.lec.spring.controller;

// 댓글은 후기 안에 있어야한다. - post_id 가져오기
// 댓글은 숙소를 등록한 오너여야 한다. - lodging_id 에 user_id 있나 판별

// 작성되어있는 댓글은 ('OWENR' 권한이 있어야 한다.)

// 댓글 작성은 페이지로드가 아닌 데이터를 추가해주는 것이다.

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/list/postId")
    public QryCommentList list(@PathVariable Long postId){
        return commentService.list(postId);
    }

    @PostMapping("/write")
    public QryResult write(
            @RequestParam("post_id") Long postId,
            @RequestParam("user_id") Long userId,
            String content){
        return commentService.write(postId, userId, content);
    }



}
