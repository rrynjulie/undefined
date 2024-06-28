package com.lec.spring.service;


import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    // Repository 초기화
    @Autowired
    public CommentServiceImpl(SqlSession sqlSession) {
        commentRepository = sqlSession.getMapper(CommentRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
    }

    @Override
    public QryCommentList list(Long postId){
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findByPost(postId);

        return list;
    }

    @Override
    public QryResult write(Long postId, Long userId, String content) {
        User user= userRepository.findById(userId);

        Comment comment =Comment.builder()
                .userId(user)
                .commentContent(content)
                .postId(postId)
                .build();

        int cnt = commentRepository.save(comment);

        QryResult result = QryResult.builder()
                .count(cnt)
                .status("OK")
                .build();

        return result;
    }

    @Override
    public QryResult delete(Long id) {
        return null;
    }
}
