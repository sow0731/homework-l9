package com.task.lecture9.repository.mapper.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.form.InsertForm;
import com.task.lecture9.form.UpdateForm;
import com.task.lecture9.repository.entity.Vinyl;
import com.task.lecture9.repository.mapper.VinylMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VinylMapperTest {

    @Autowired
    VinylMapper vinylMapper;

    @Autowired
    DataSource dataSource;

    @Test
    @DataSet(value = "datasets/vinyls.yml")
    @Transactional
    void Vinylデータが全件取得できること() {
        List<Vinyl> vinyls = vinylMapper.findAll();
        assertThat(vinyls)
                .hasSize(2)
                .contains(
                        new Vinyl(1, "aa", "bb", "cc", "1999"),
                        new Vinyl(2, "aaa", "bbb", "ccc", "2000"));
    }

    @Test
    @DataSet(value = "datasets/vinyls.yml")
    @Transactional
    void 指定したidから特定のVinylデータが取得できること() {
        Optional<Vinyl> vinyl = vinylMapper.findById(1);
        assertThat(vinyl).hasValue((new Vinyl(1, "aa", "bb", "cc", "1999")));
    }

    @Test
    @DataSet(value = "datasets/vinyls.yml")
    @Transactional
    void 存在しないidでリクエストをした場合取得するデータが空であること() {
        Optional<Vinyl> vinyl = vinylMapper.findById(30);
        assertThat(vinyl).isEmpty();
    }

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "datasets/expectedVinylDataAfterInsert.yml", ignoreCols = "id")
    @Transactional
    void 新たにVinylデータが登録できること() {
        InsertForm insertForm = new InsertForm("sss", "ddd", "fff", "2003");
        VinylDto vinylDto = new VinylDto(
                insertForm.getTitle(),
                insertForm.getArtist(),
                insertForm.getLabel(),
                insertForm.getReleaseYear()
        );
//        idの自動採番が正しく機能するか確認
        vinylMapper.insert(vinylDto);
        assertThat(vinylDto.getId()).isEqualTo(4);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @ExpectedDataSet(value = "datasets/expectedVinylDataAfterUpdate.yml")
    @Transactional
    void 既存のVinylデータを更新できること() {
        vinylMapper.findById(1);
        UpdateForm updateForm = new UpdateForm("aab", "bbc", "ccd", "1998");
        VinylDto vinylDto = new VinylDto(
                updateForm.getTitle(),
                updateForm.getArtist(),
                updateForm.getLabel(),
                updateForm.getReleaseYear()
        );
        vinylMapper.update(1, vinylDto);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 存在しないidで更新リクエストをした場合更新データが空であること() {
        vinylMapper.findById(10);
        UpdateForm updateForm = new UpdateForm("uu", "oo", "ll", "1988");
        VinylDto vinylDto = new VinylDto(
                updateForm.getTitle(),
                updateForm.getArtist(),
                updateForm.getLabel(),
                updateForm.getReleaseYear()
        );
        assertThat(vinylMapper.findById(10)).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @ExpectedDataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void nullが入力された場合更新されないこと() {
        vinylMapper.findById(1);
        UpdateForm updateForm = new UpdateForm(null, null, null, null);
        VinylDto vinylDto = new VinylDto(
                updateForm.getTitle(),
                updateForm.getArtist(),
                updateForm.getLabel(),
                updateForm.getReleaseYear()
        );
        vinylMapper.update(1, vinylDto);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @ExpectedDataSet(value = "expectedVinylDataAfterPartUpdate.yml")
    @Transactional
    void 部分更新ができること() {
        vinylMapper.findById(1);
        UpdateForm updateForm = new UpdateForm("aab", null, null, null);
        VinylDto vinylDto = new VinylDto(
                updateForm.getTitle(),
                updateForm.getArtist(),
                updateForm.getLabel(),
                updateForm.getReleaseYear()
        );
        vinylMapper.update(1, vinylDto);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 指定したidのVinylDataを正常に削除できること() {
        vinylMapper.delete(1);
        assertThat(vinylMapper.findById(1)).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 存在しないidで削除リクエストをした場合何も削除されないこと() {
        vinylMapper.delete(10);
        assertThat(vinylMapper.findAll()).contains(new Vinyl(1, "aa", "bb", "cc", "1999"));
    }
}
