package com.task.lecture9.service;

import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.exception.ResourceNotFoundException;
import com.task.lecture9.repository.entity.Vinyl;
import com.task.lecture9.repository.mapper.VinylMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VinylServiceImpl implements VinylService {

    private final VinylMapper vinylMapper;

    public VinylServiceImpl(VinylMapper vinylMapper) {

        this.vinylMapper = vinylMapper;
    }

    @Override
    public List<Vinyl> findAll() {

        return vinylMapper.findAll();
    }

    @Override
    public Vinyl findById(Integer id) {
        Optional<Vinyl> vinyl = vinylMapper.findById(id);
        return vinyl.orElseThrow(() -> new ResourceNotFoundException("データが見つかりません"));

    }
    @Override
    public int insert(VinylDto vinylDto) {
        vinylMapper.insert(vinylDto);
        return vinylDto.getId();
    }

    @Override
    public Vinyl update(Integer id, VinylDto vinylDto) {
        vinylMapper.update(id, vinylDto);
        var vinyl = vinylMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("更新するデータがありません"));
        return vinyl;
    }

    @Override
    public Map<String, String> delete(Integer id) {
        var vinyl = vinylMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("削除するデータがありません"));
        vinylMapper.delete(id);
        return Map.of("message", "Vinyl Data Has Been Deleted");
    }
}
