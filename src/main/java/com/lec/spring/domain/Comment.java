package com.lec.spring.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Comment {

    // PK를 가지고 있는 댓글 고유 ID값 (PK)
    private Long commentId;

    // 댓글 내용
    private String commentContent;

    // 댓글 작성시간
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @JsonProperty("regdate")
    private LocalDateTime commentRegdate;

    // 어떤 후기(post_id)의 댓글인가 (FK)
    private Long postId;

    // 댓글 작성자(user_id) (FK)
    private User userId;

}
