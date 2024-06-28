package com.lec.spring.repository;

import com.lec.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {

    //특정 후기(post_id)의 댓글
    List<Comment> findByPost(Long postId);

    // 댓글 작성
    int save(Comment comment);

    // 댓글 삭제
    int deleteById(Long id);
}
