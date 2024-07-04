package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import org.apache.ibatis.annotations.Param;
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
    public int savePost(Post post) {
        return 0;
    }
    @Override
    public int allPostSave(Post post) {
        return postRepository.savePost(post);
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
    public List<Post> findPostByLodgingId(Long lodgingId) {
        return postRepository.findPostByLodgingId(lodgingId);
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
    public List<Post> allPostUser(Long userId, Long postId) {
        return postRepository.postUser(userId, postId);
    }

    @Override
    public int allPostUpdate(Post post) {
        int result = 0;
        result = postRepository.postUpdate(post);
        return result;
    }

    @Override
    public int allPostDelete(Post postId) {
        int result = 0;
        result = postRepository.postDelete(postId);
        return result;
    }

    @Override
    public boolean checkIfUserPosted(Long userId, Long bookingId) {
        return postRepository.checkIfUserPosted(userId, bookingId);
    }

    // roomId 관련된 post 삭제
    @Override
    public void deletePostsByRoomId(int roomId) {
        postRepository.deletePostsByRoomId(roomId);
    }
}