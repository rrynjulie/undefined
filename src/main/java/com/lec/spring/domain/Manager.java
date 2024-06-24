package com.lec.spring.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {
    // 유저 아이디
    private String userId;

    //유저 비밀번호
//    @JsonIgnore
//    private String userPassword;

    // 유저 이름
    private String userName;

    // 유저 이메일
    private String userEmail;

    // 유저 등록날짜
    private LocalDateTime userRegdate;

    // 유저 닉네임
    private String userNickname;

    //유저 번화번호
    private String userPhonenum;

}
