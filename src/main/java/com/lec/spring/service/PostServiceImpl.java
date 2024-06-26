package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public int createPost(Post post) {
        postRepository.createPost(post);
        return 0;
    }

    @Override
    public int countAllPostsByLodgingId(Long lodgingId) {
        return postRepository.countAllPostsByLodgingId(lodgingId);
    }

    @Override
    public List<Post> findPostsByRoomId(Long roomId) {
        return postRepository.findPostsByRoomId(roomId);
    }

    @Override
    public int updatePost(Post post) {
        return 0;
    }

    @Override
    public int deletePost(Long postId) {
        return 0;
    }


    @Override
    public List<Post> allPostUserId(Long userId) {
        return postRepository.postUserId(userId);
    }

    @Override
    public List<Post> allPostUserUpdate(Long userId, Long postId) {
        return postRepository.postUserUpdate(userId, postId);
    }

    @Override
    public int allPostUpdate(Post post) {
        int result = 0;
        result = postRepository.postUpdate(post);
        return result;
    }




    //    오류 때문에 잠시 주석처리
//    @Override
//    public void createPost(Post post) {
//        postRepository.createPost(post);
//    }
}