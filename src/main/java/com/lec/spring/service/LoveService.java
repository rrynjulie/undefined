package com.lec.spring.service;

import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Love;
import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.User;

import java.util.List;

public interface LoveService {
    List<Lodging> getLodgings(Long userId);

    // 특정 id에 따른 좋아요 누른 상태
    List<Love> getAllUserLove();

    // 사용자와 숙소 상태 테이블 조인
    List<User> getAllUsersWithLodging();

    // 좋아요 추가
    void addLove(Long userId, Long lodgingId);

    // 조아효 삭제
    void removeLove(Long userId, Long lodgingId);

    // 유저가 좋아요를 눌렀는지 확인
    boolean isUserLikedLodging(Long userId, Long lodgingId);

}
