package com.lec.spring.repository;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityRepository {

    Authority findByNickname(String nickname);

    List<Authority> findByUser(User user);

    int addAuthority(Long user_id, Long auth_id);
}
