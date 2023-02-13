package com.task.lecture9.repository.mapper;

import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.repository.entity.Vinyl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface VinylMapper {
    @Select("SELECT * FROM vinyl" )
    List<Vinyl> findAll();

    @Select("SELECT * FROM vinyl WHERE id=#{id}" )
    Optional<Vinyl> findById(Integer id);

    @Insert("INSERT INTO vinyl (id, title, artist, label, release_year) VALUES (#{id}, #{title}, #{artist}, #{label}," +
            " #{releaseYear})" )
    @Options(useGeneratedKeys = true, keyProperty = "id" )
    void insert(VinylDto vinylDto);

    @Update("UPDATE vinyl " +
            "SET title=" +
            "   CASE WHEN #{vinylDto.title} IS NOT NULL THEN #{vinylDto.title} ELSE title END," +
            "artist=" +
            "   CASE WHEN #{vinylDto.artist} IS NOT NULL THEN #{vinylDto.artist} ELSE artist END," +
            "label=" +
            "   CASE WHEN #{vinylDto.label} IS NOT NULL THEN #{vinylDto.label} ELSE label END," +
            "release_year=" +
            "   CASE WHEN #{vinylDto.releaseYear} IS NOT NULL THEN #{vinylDto.releaseYear} ELSE release_year END " +
            "WHERE id=#{id}"
    )
    void update(Integer id, VinylDto vinylDto);
}
