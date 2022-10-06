package com.task.lecture9.service;

import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.repository.entity.Vinyl;

import java.util.List;

public interface VinylService {
    List<Vinyl> findAll();

    Vinyl findById(Integer id) throws Exception;

    int insert(VinylDto vinylDto);
}