package com.task.lecture9.domain.service;

import com.task.lecture9.domain.Form.CreateForm;
import com.task.lecture9.domain.model.Vinyl;
import com.task.lecture9.infrastructure.VinylMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinylServiceImpl implements VinylService {

    @Autowired
    VinylMapper vinylMapper;

    @Override
    public List<Vinyl> findAll() {

        return vinylMapper.findAll();
    }

    @Override
    public Vinyl findById(Integer id) throws Exception {
        return vinylMapper.findById(id);
    }
    @Override
    public void insert(CreateForm form) {
        vinylMapper.insert(form);
    }
}
