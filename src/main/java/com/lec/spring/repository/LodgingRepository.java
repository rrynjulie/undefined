package com.lec.spring.repository;



import com.lec.spring.domain.Lodging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


//@Mapper
public interface LodgingRepository {
//    @Select("SELECT * FROM lodging WHERE lodging_id = #{lodgingId}")
//    Lodging findById(Long lodgingId);

//    @Select("SELECT * FROM lodging")
    List<Lodging> findAllDetails();
}
