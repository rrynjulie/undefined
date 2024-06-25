package com.lec.spring.service;

import com.lec.spring.domain.Post;

import java.util.List;

public interface PostService {

    List<Post> allPostUserId(Long userId);

    List<Post> allPostUserUpdate(Long userId, Long postId);

    int allPostUpdate(Post post);


    //    오류 때문에 잠시 주석처리
//    void createPost(Post post);
}
