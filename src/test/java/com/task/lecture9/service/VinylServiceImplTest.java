package com.task.lecture9.service;

import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.exception.ResourceNotFoundException;
import com.task.lecture9.form.InsertForm;
import com.task.lecture9.repository.entity.Vinyl;
import com.task.lecture9.repository.mapper.VinylMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VinylServiceImplTest {

    @InjectMocks
    VinylServiceImpl vinylServiceImpl;

    @Mock
    VinylMapper vinylMapper;

    @Test
    void VinylMapperから取得したVinylをそのまま返すこと() {
        List<Vinyl> vinyls = Arrays.asList(new Vinyl(1, "a", "b", "c", 1000),
                new Vinyl(2, "aa", "bb", "cc", 2000));
        doReturn(vinyls).when(vinylMapper).findAll();
        List<Vinyl> actualVinyls = vinylServiceImpl.findAll();
        assertThat(actualVinyls).isEqualTo(vinyls);
        verify(vinylMapper, times(1)).findAll();

    }
    @Test
    void 存在するVinylのidを指定した時に正常にVinylが返されること() throws Exception {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(10, "aa", "bb", "cc", 1000));
        doReturn(vinyl).when(vinylMapper).findById(10);
        Optional<Vinyl> actual = vinylMapper.findById(10);
        assertThat(actual).isEqualTo(vinyl);
        verify(vinylMapper, times(1)).findById(10);
    }

    @Test
    void 存在しないVinylのidを指定した時にデータが見つかりませんと返されること() throws Exception {
        Optional<Vinyl> vinyl = Optional.empty();
        doReturn(vinyl).when(vinylMapper).findById(anyInt());
        assertThrows(ResourceNotFoundException.class, () -> vinylServiceImpl.findById(20));
        verify(vinylMapper, times(1)).findById(20);
    }

    @Test
    void 新たにVinylを追加できること() {
        InsertForm insertForm = new InsertForm("ss", "dd", "ff", 2003);
        VinylDto vinylDto = new VinylDto(
                insertForm.getTitle(),
                insertForm.getArtist(),
                insertForm.getLabel(),
                insertForm.getReleaseYear());


        vinylMapper.insert(vinylDto);
        verify(vinylMapper, times(1)).insert(vinylDto);
    }
}