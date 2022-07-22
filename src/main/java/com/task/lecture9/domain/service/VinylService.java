package com.task.lecture9.domain.service;

import com.task.lecture9.domain.Form.CreateForm;
import com.task.lecture9.domain.model.Vinyl;

import java.util.List;

public interface VinylService {
    List<Vinyl> findAll();

    Vinyl findVinyl(Integer id) throws Exception;

    void insert(CreateForm form);
    Vinyl findById(Integer id) throws Exception;
}
