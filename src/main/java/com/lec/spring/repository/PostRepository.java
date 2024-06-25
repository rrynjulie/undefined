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

    List<Post> postUserId(@Param("userId") Long userId);

    List<Post> postUserUpdate(@Param("userId") Long userId, @Param("postId") Long postId);

    int postUpdate(Post post);

//    오류 때문에 잠시 주석처리
//    void createPost(Post post);
}
