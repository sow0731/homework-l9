package com.task.lecture9.infrastructure;

import com.task.lecture9.domain.Form.CreateForm;
import com.task.lecture9.domain.model.Vinyl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VinylMapper {
    @Select("SELECT * FROM vinyl")
    List<Vinyl> findAll();

    @Select("SELECT * FROM vinyl WHERE id=#{id}")
    Optional<Vinyl> findById(int id);

    @Insert("INSERT INTO vinyl (id, title, artist, label, year) VALUES (#{id}, #{title}, #{artist}, #{label}, #{year})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(CreateForm form);
    Vinyl findById(int id);
}
