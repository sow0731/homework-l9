package com.task.lecture9.service;

import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.doNothing;
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
        List<Vinyl> vinyls = Arrays.asList(new Vinyl(1, "a", "b", "c", "1000"),
                new Vinyl(2, "aa", "bb", "cc", "2000"));

        doReturn(vinyls).when(vinylMapper).findAll();
        List<Vinyl> actualVinyls = vinylServiceImpl.findAll();
        assertThat(actualVinyls).isEqualTo(vinyls);

        verify(vinylMapper, times(1)).findAll();

    }
    @Test
    void 存在するVinylのidを指定した時に正常にVinylが返されること() throws Exception {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(10, "aa", "bb", "cc", "1000"));

        doReturn(vinyl).when(vinylMapper).findById(10);
        Optional<Vinyl> actual = vinylMapper.findById(10);
        assertThat(actual).isEqualTo(vinyl);

        verify(vinylMapper, times(1)).findById(10);
    }

    @Test
    void 存在しないVinylのidを指定した時にデータが見つかりませんと返されること() {
        doReturn(Optional.empty()).when(vinylMapper).findById(anyInt());

        ResourceNotFoundException e =
                assertThrows(ResourceNotFoundException.class, () -> vinylServiceImpl.findById(99));
        assertThat(e.getMessage()).isEqualTo("データが見つかりません");

        verify(vinylMapper, times(1)).findById(99);
    }

    @Test
    void 新たにVinylを追加できること() {
        VinylDto vinylDto = new VinylDto("ss", "dd", "ff", "2003");

        vinylMapper.insert(vinylDto);

        verify(vinylMapper, times(1)).insert(vinylDto);
    }

    @Test
    void 既存のVinylを更新できること() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(7, "nn", "mm", "ww", "1999"));
        VinylDto vinylDto = new VinylDto("nn", "mm", "ww", "1999");

        doReturn(vinyl).when(vinylMapper).findById(7);
        doNothing().when(vinylMapper).update(7, vinylDto);

        Optional<Vinyl> actual = Optional.ofNullable(vinylServiceImpl.update(7, vinylDto));

        assertThat(actual).isEqualTo(vinyl);

        verify(vinylMapper, times(1)).update(7, vinylDto);
        verify(vinylMapper, times(1)).findById(7);
    }

    @Test
    void 存在しないidで更新した場合更新するデータがありませんと返されること() {
        doReturn(Optional.empty()).when(vinylMapper).findById(anyInt());
        VinylDto vinylDto = new VinylDto("ds", "wq", "er", "2003");

        ResourceNotFoundException e =
                assertThrows(ResourceNotFoundException.class, () -> {
                    vinylServiceImpl.update(99, vinylDto);
                });
        assertThat(e.getMessage()).isEqualTo("更新するデータがありません");

        verify(vinylMapper, times(1)).update(99, vinylDto);
        verify(vinylMapper, times(1)).findById(99);
    }

    @Test
    void 全項目nullを入力した場合に全て更新されないこと() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(7, "nn", "mm", "ww", "1999"));
        VinylDto vinylDto = new VinylDto(null, null, null, null);

        doReturn(vinyl).when(vinylMapper).findById(7);
        doNothing().when(vinylMapper).update(7, vinylDto);

        Optional<Vinyl> actual = Optional.ofNullable(vinylServiceImpl.update(7, vinylDto));

        assertThat(actual).isEqualTo(vinyl);

        verify(vinylMapper, times(1)).update(7, vinylDto);
        verify(vinylMapper, times(1)).findById(7);
    }

    @Test
    void title以外にnullを入力した場合titleのみが部分更新されること() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(7, "nn", "mm", "ww", "1999"));
        VinylDto vinylDto = new VinylDto("nn", null, null, null);

        doReturn(vinyl).when(vinylMapper).findById(7);
        doNothing().when(vinylMapper).update(7, vinylDto);

        Optional<Vinyl> actual = Optional.ofNullable(vinylServiceImpl.update(7, vinylDto));

        assertThat(actual).isEqualTo(vinyl);

        verify(vinylMapper, times(1)).update(7, vinylDto);
        verify(vinylMapper, times(1)).findById(7);
    }
    @Test
    void artist以外にnullを入力した場合artistのみが部分更新されること() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(7, "nn", "mm", "ww", "1999"));
        VinylDto vinylDto = new VinylDto(null, "mm", null, null);

        doReturn(vinyl).when(vinylMapper).findById(7);
        doNothing().when(vinylMapper).update(7, vinylDto);

        Optional<Vinyl> actual = Optional.ofNullable(vinylServiceImpl.update(7, vinylDto));

        assertThat(actual).isEqualTo(vinyl);

        verify(vinylMapper, times(1)).update(7, vinylDto);
        verify(vinylMapper, times(1)).findById(7);
    }
    @Test
    void label以外にnullを入力した場合labelのみが部分更新されること() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(7, "nn", "mm", "ww", "1999"));
        VinylDto vinylDto = new VinylDto(null, null, "ww", null);

        doReturn(vinyl).when(vinylMapper).findById(7);
        doNothing().when(vinylMapper).update(7, vinylDto);

        Optional<Vinyl> actual = Optional.ofNullable(vinylServiceImpl.update(7, vinylDto));

        assertThat(actual).isEqualTo(vinyl);

        verify(vinylMapper, times(1)).update(7, vinylDto);
        verify(vinylMapper, times(1)).findById(7);
    }
    @Test
    void releaseYear以外にnullを入力した場合releaseYearのみが部分更新されること() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(7, "nn", "mm", "ww", "1999"));
        VinylDto vinylDto = new VinylDto(null, null, null, "1999");

        doReturn(vinyl).when(vinylMapper).findById(7);
        doNothing().when(vinylMapper).update(7, vinylDto);

        Optional<Vinyl> actual = Optional.ofNullable(vinylServiceImpl.update(7, vinylDto));

        assertThat(actual).isEqualTo(vinyl);

        verify(vinylMapper, times(1)).update(7, vinylDto);
        verify(vinylMapper, times(1)).findById(7);
    }

    @Test
    void 指定したidのVinylDataを正常に削除できること() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(8, "aa", "bb", "cc", "1999"));

        doReturn(vinyl).when(vinylMapper).findById(8);
        doNothing().when(vinylMapper).delete(8);

        vinylServiceImpl.delete(8);

        verify(vinylMapper, times(1)).findById(8);
        verify(vinylMapper, times(1)).delete(8);
    }

    @Test
    void 存在しないidで削除リクエストをした場合削除するデータがありませんと返されること() {
        Optional<Vinyl> vinyl = Optional.of(new Vinyl(8, "aa", "bb", "cc", "1999"));

        doReturn(Optional.empty()).when(vinylMapper).findById(10);

        ResourceNotFoundException e =
                assertThrows(ResourceNotFoundException.class, () -> {
                    vinylServiceImpl.delete(10);
                });
        assertThat(e.getMessage()).isEqualTo("削除するデータがありません");

        verify(vinylMapper, times(1)).findById(10);
    }
}
