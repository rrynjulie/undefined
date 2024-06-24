package com.lec.spring.service;

import com.lec.spring.domain.Post;

import java.util.List;

public interface PostService {
    int createPost(Post post);

    int countAllPostsByLodgingId(Long lodgingId);

    List<Post> findPostsByRoomId(Long roomId);

    int updatePost(Post post);

    int deletePost(Long postId);
}
