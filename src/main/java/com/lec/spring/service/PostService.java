package com.lec.spring.service;

import com.lec.spring.domain.Post;

import java.util.List;

public interface PostService {
    int savePost(Post post);

    int allPostSave(Post post);

    int countAllPostsByLodgingId(Long lodgingId);

    List<Post> findPostsByRoomId(Long roomId);

    List<Post> findPostByLodgingId(Long lodgingId);

    int updatePost(Post post);

    int deletePost(Long postId);

    List<Post> allPostUserId(Long userId);

    List<Post> allPostUser(Long userId, Long postId);

    int allPostUpdate(Post post);

    int allPostDelete(Post post);

    boolean checkIfUserPosted(Long userId, Long bookingId);

}
