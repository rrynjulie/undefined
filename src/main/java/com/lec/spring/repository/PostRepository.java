package com.lec.spring.repository;

import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface PostRepository {
    int savePost(Post post);

    int countAllPostsByLodgingId(Long lodgingId);

    List<Post> findPostsByRoomId(Long roomId);

    List<Post> findPostByLodgingId(Long lodgingId);

    int updatePost(Post post);
    List<Post> postUserId(@Param("userId") Long userId);

    List<Post> postUser(@Param("userId") Long userId, @Param("postId") Long postId);

    int postUpdate(Post post);

    int postDelete(Post post);

    boolean checkIfUserPosted(@Param("userId") Long userId, @Param("bookingId") Long bookingId);

    // roomId 관련된 post 삭제
    void deletePostsByRoomId(int roomId);
}