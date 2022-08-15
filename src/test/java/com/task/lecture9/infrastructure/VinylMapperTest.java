package com.task.lecture9.infrastructure;

import com.task.lecture9.domain.model.Vinyl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VinylMapperTest {

    @Autowired
    VinylMapper vinylMapper;

    @Test
    @Transactional
    @Sql(scripts = {"classpath:/sqlannotation/delete-vinyl.sql", "classpath:/sqlannotation/insert-vinyl.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void 全てのVinylデータが返されること() {
        List<Vinyl> vinyls = vinylMapper.findAll();
        assertThat(vinyls)
                .hasSize(2)
                .contains(
                        new Vinyl(1, "aaa", "bbb", "ccc", 1999),
                        new Vinyl(2, "aaaa", "bbbb", "cccc", 2000)
                );
    }

    @Test
    @Transactional
    @Sql(scripts = {"classpath:/sqlannotation/delete-vinyl.sql", "classpath:/sqlannotation/insert-vinyl.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void Idを指定して特定のVinylデータが取得できること() {
        Optional<Vinyl> vinyls = vinylMapper.findById(1);
        assertThat(vinyls).isEqualTo(Optional.of(new Vinyl(1, "aaa", "bbb", "ccc", 1999)));
    }

//　これからやるところ
//    @Test
//    void 新たにVinylデータが登録されること() {
//        VinylDto vinylDto = new VinylDto();
//
//        vinylDto.setTitle("ss");
//        vinylDto.setArtist("dd");
//        vinylDto.setLabel("ff");
//        vinylDto.setRelease_year(2001);
//        vinylMapper.insert(vinylDto);
//
//        VinylDto actual = vinylMapper.insert(vinylDto);
//        assertThat(vinylDto).isEqualTo(actual);
//    }
}

