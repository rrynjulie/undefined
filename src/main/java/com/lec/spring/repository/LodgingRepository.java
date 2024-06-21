package com.lec.spring.repository;



import com.lec.spring.domain.Lodging;


import java.util.List;


//@Mapper
public interface LodgingRepository {
    List<Lodging> findLodgingsByLocation(String location);
}
