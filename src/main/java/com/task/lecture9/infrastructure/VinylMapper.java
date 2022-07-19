package com.task.lecture9.infrastructure;

import com.task.lecture9.domain.model.Vinyl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VinylMapper {
    @Select("SELECT * FROM vinyl")
    List<Vinyl> findAll();

    @Select("SELECT * FROM vinyl WHERE id=#{id}")
    Vinyl findById(int id);
}
