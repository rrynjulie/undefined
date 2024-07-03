package com.lec.spring.service;

import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Love;
import com.lec.spring.domain.User;
import com.lec.spring.repository.LoveRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class LoveServiceImpl implements LoveService {

    // LoveRepository 기본 생성자 생성 후 초기화
    private final LoveRepository loveRepository;

    // 생성자를 통해 인스턴스를 주입받는다.
    public LoveServiceImpl(LoveRepository loveRepository) {
        this.loveRepository = loveRepository;
    }

    public List<Lodging> getLodgings(Long userId) {
        return loveRepository.findLodgings(userId);
    }

    @Override
    public List<Love> getAllUserLove() {
        return loveRepository.findAllUserLove();
    }

    // 사용자와 숙소사이 조인
    @Override
    public List<User> getAllUsersWithLodging() {
        List<User> users = loveRepository.findAllUserWithLove();
        return loveRepository.findAllUserWithLove();
    }

    @Override
    public void addLove(Long userId, Long lodgingId){
        Love love = new Love();
        love.setUserId(userId);
        love.setLodgingId(lodgingId);
        loveRepository.addLove(love);
    }

    @Override
    public void removeLove(Long userId, Long lodgingId) {
        Love love = new Love();
        love.setUserId(userId);
        love.setLodgingId(lodgingId);
        loveRepository.removeLove(love);
    }

    @Override
    public boolean isUserLikedLodging(Long userId, Long lodgingId) {
        // 사용자가 특정 숙소를 좋아요 했는지 확인
        return loveRepository.existsByUserIdAndLodgingId(userId, lodgingId);
    }

}
