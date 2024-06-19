package com.lec.spring.repository;

import com.lec.spring.domain.Lodging;

import java.util.List;

public interface LodgingRepository {
    List<Lodging> list();   // 검색 후 검색 결과에 맞는 숙소 리스트

}
