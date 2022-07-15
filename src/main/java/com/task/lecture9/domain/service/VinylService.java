package com.task.lecture9.domain.service;

import com.task.lecture9.domain.model.Vinyl;
import com.task.lecture9.infrastructure.VinylMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinylService {

    private final VinylMapper vinylMapper;

    public VinylService(VinylMapper vinylMapper) {
        this.vinylMapper = vinylMapper;
    }

    public List<Vinyl> findAll() {

        return vinylMapper.findAll();
    }

    public Vinyl findVinyl(Integer id) {
        return this.vinylMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
    }
}
