package integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.task.lecture9.Lecture9Application;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = Lecture9Application.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VinylIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "vinyls.yml")
    @Transactional
    void 全てのVinylデータが取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/vinyls"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("[" +
                "{" +
                "\"id\":1," +
                "\"title\":aa," +
                "\"artist\": bb," +
                "\"label\": cc," +
                "\"release_year\":1999" +
                "}," +
                "{ " +
                "\"id\":2," +
                "\"title\": aaa," +
                "\"artist\": bbb," +
                "\"label\":ccc," +
                "\"release_year\":2000" +
                "}" +
                "]", response, JSONCompareMode.STRICT);
    }
    @Test
    @DataSet(value = "vinyls.yml")
    @Transactional
    void idを指定し特定のVinylデータを取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/vinyls/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("{" +
                "\"id\":1," +
                "\"title\":aa," +
                "\"artist\":bb," +
                "\"label\":cc," +
                "\"release_year\":1999" +
                "}", response, JSONCompareMode.STRICT);
    }
    @Test
    @DataSet(value = "vinyls.yml")
    @Transactional
    void 存在しないidでリクエストした場合例外がスローされること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vinyls/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "datasets/expectedVinylDataAfterInsert.yml", ignoreCols = "id")
    @Transactional
    void Vinylデータが新たに登録できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/vinyls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"sss\",\"artist\":\"ddd\",\"label\":\"fff\",\"releaseYear\": 2003}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("{\"message\":\"New Vinyl Data Is Added\"}", response, JSONCompareMode.STRICT);
    }
}