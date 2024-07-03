package com.lec.spring.repository;

import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Love;
import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.User;

import java.util.List;

public interface LoveRepository {
    List<Lodging> findLodgings(Long userId);

    // 모든 사용자 조회
    List<User> findAll();

    // 모든 좋아요 조회
    List<Love> findAllUserLove();

    // 사용자와 Lodging 테이블 조인
    List<User> findAllUserWithLove();

    // 좋아요 추가 (insert)
    void addLove(Love love);

    // 조하요 제거 (delete)
    void removeLove(Love love);

    // 특정 사용자와 숙소에 대한 좋아요 상태 확인
    boolean existsByUserIdAndLodgingId(Long userId, Long lodgingId);
}
