package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {
    private Long id;  // 권한의 식별자(ID)
    private String name;  // 권한의 이름

    // 권한의 이름을 가져오는 메서드
    public String getName() {
        return name;
    }

    // 권한의 이름을 설정하는 메서드
    public void setName(String name) {
        this.name = name;
    }

    private Long userId; // 사용자 ID (추가됨)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Integer getAuthority() {
//        return authority;
//    }
//
//    public void setAuthority(Integer authority) {
//        this.authority = authority;
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
