package com.task.lecture9.domain.service;

import com.task.lecture9.domain.Form.CreateForm;
import com.task.lecture9.domain.model.Vinyl;

import java.util.List;

public interface VinylService {
    List<Vinyl> findAll();

List<Vinyl> findAll();

    Vinyl findById(Integer id) throws Exception;

    void insert(VinylDto vinylDto);
}
