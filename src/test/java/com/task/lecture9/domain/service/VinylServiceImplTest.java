package com.task.lecture9.domain.service;

import com.task.lecture9.domain.model.Vinyl;
import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.infrastructure.VinylMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

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

    }
    @Test
    void 存在するVinylのidを指定した時に正常にVinylが返されること() throws Exception {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(10, "aa", "bb", "cc", 1000));
        doReturn(vinyl).when(vinylMapper).findById(10);
        Optional<Vinyl> actual = vinylMapper.findById(10);
        assertThat(actual).isEqualTo(vinyl);
    }

    @Test
    void 存在しないVinylのidを指定した時にデータが見つかりませんと返されること() throws Exception {
        Optional<Vinyl> vinyl = Optional.empty();
        doReturn(vinyl).when(vinylMapper).findById(20);
        Optional<Vinyl> actual = vinylMapper.findById(20);
        assertThat(actual).isEqualTo(vinyl.orElseThrow(() -> new RuntimeException("データが見つかりません")));
    }

    @Test
    void 新たにVinylを追加できること() {
        VinylDto vinylDto = new VinylDto();
        doReturn(vinylDto).when(vinylMapper).insert(vinylDto);
        VinylDto actual = vinylMapper.insert(vinylDto);
        assertThat(actual).isEqualTo(vinylDto);
    }
}