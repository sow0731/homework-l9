package com.task.lecture9.infrastructure;

import com.task.lecture9.domain.model.Vinyl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface VinylMapper {
    @Select("SELECT * FROM vinyl")
    List<Vinyl> findAll();

    @Select("SELECT * FROM vinyl WHERE id=#{id}")
    Optional<Vinyl> findById(int id);
}
