package com.lec.spring.repository;

import com.lec.spring.domain.Post;

import java.util.List;

public interface PostRepository {
    int createPost(Post post);

    int countAllPostsByLodgingId(Long lodgingId);

    List<Post> findPostsByRoomId(Long roomId);

    int updatePost(Post post);

    int deletePost(Post post);
}