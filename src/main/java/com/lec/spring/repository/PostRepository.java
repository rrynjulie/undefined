package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    void createPost(Post post);
}
