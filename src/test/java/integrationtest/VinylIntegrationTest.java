package integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.task.lecture9.Lecture9Application;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest(classes = Lecture9Application.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VinylIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    ZonedDateTime zonedDataTime = ZonedDateTime.of(2022, 8, 31, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

    @Test
    @DataSet(value = "datasets/vinyls.yml")
    @Transactional
    void 全てのVinylデータが取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/vinyls"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("[" +
                "{" +
                "\"id\":1," +
                "\"title\":\"aa\"," +
                "\"artist\":\"bb\"," +
                "\"label\":\"cc\"," +
                "\"releaseYear\":\"1999\"" +
                "}," +
                "{ " +
                "\"id\":2," +
                "\"title\":\"aaa\"," +
                "\"artist\":\"bbb\"," +
                "\"label\":\"ccc\"," +
                "\"releaseYear\":\"2000\"" +
                "}" +
                "]", response, JSONCompareMode.STRICT);
    }
    @Test
    @DataSet(value = "datasets/vinyls.yml")
    @Transactional
    void idを指定し特定のVinylデータを取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/vinyls/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("{" +
                "\"id\":1," +
                "\"title\":\"aa\"," +
                "\"artist\":\"bb\"," +
                "\"label\":\"cc\"," +
                "\"releaseYear\":\"1999\"" +
                "}", response, JSONCompareMode.STRICT);
    }
    @Test
    @DataSet(value = "vinyls.yml")
    @Transactional
    void 存在しないidでリクエストした場合例外がスローされること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.get("/vinyls/3"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("{" +
                    "\"timestamp\":\"2022-08-31T00:00+09:00[Asia/Tokyo]\"," +
                    "\"status\":\"404\"," +
                    "\"error\":\"Not Found\"," +
                    "\"message\":\"データが見つかりません\"," +
                    "\"path\":\"/vinyls/3\"}", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "datasets/expectedVinylDataAfterInsert.yml", ignoreCols = "id")
    @Transactional
    void Vinylデータが新たに登録できること() throws Exception {
        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/vinyls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"sss\",\"artist\":\"ddd\",\"label\":\"fff\",\"releaseYear\": \"2003\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("{\"message\":\"New Vinyl Data Is Added\"}", response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(cleanBefore = true)
    @Transactional
    void 必須項目を全て空文字で登録しようとした場合Badリクエストが返って来ること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);
            String response = mockMvc.perform(MockMvcRequestBuilders.post("/vinyls")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}")
                            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("{" +
                    "\"timestamp\":\"2022-08-31T00:00+09:00[Asia/Tokyo]\"," +
                    "\"status\":\"400\"," +
                    "\"error\":\"Bad Request\"," +
                    "\"message\": {\"title\":[\"titleを入力してください\"]," +
                    "\"artist\":[\"artistを入力してください\"]," +
                    "\"label\":[\"labelを入力してください\"]," +
                    "\"releaseYear\":[\"整数4桁で入力してください\"]," +
                    "\"releaseYear\":[\"releaseYearを入力してください\"]}}", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(cleanBefore = true)
    @Transactional
    void 必須項目文字数制限以上で登録しようとしたBadリクエストが返って来ること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.post("/vinyls")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," +
                                    "\"artist\":\"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\"," +
                                    "\"label\":\"ccccccccccccccccccccccccccccccccccccccccccccccccccc\"," +
                                    "\"releaseYear\":\"20003\"}")
                            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("{" +
                    "\"timestamp\":\"2022-08-31T00:00+09:00[Asia/Tokyo]\"," +
                    "\"status\":\"400\"," +
                    "\"error\":\"Bad Request\"," +
                    "\"message\": {" +
                    "\"title\":[\"titleは50文字以内で入力してください\"]," +
                    "\"artist\":[\"artistは50文字以内で入力してください\"]," +
                    "\"label\":[\"labelは50文字以内で入力してください\"]," +
                    "\"releaseYear\":[\"整数4桁で入力してください\"]}" +
                    "}", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(cleanBefore = true)
    @Transactional
    void releaseYearに整数以外が入力された場合Badリクエストが返って来ること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.post("/vinyls")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\":\"aaaaaa\"," +
                                    "\"artist\":\"bbbbbb\"," +
                                    "\"label\":\"ccccccc\"," +
                                    "\"releaseYear\":\"cccc\"}")
                            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("{" +
                    "\"timestamp\":\"2022-08-31T00:00+09:00[Asia/Tokyo]\"," +
                    "\"status\":\"400\"," +
                    "\"error\":\"Bad Request\"," +
                    "\"message\": {\"releaseYear\":[\"整数4桁で入力してください\"]}}", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(cleanBefore = true)
    @Transactional
    void releaseYearに4桁未満の整数が入力された場合Badリクエストが返って来ること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.post("/vinyls")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\":\"aaaaaa\"," +
                                    "\"artist\":\"bbbbbb\"," +
                                    "\"label\":\"ccccccc\"," +
                                    "\"releaseYear\":\"203\"}")
                            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("{" +
                    "\"timestamp\":\"2022-08-31T00:00+09:00[Asia/Tokyo]\"," +
                    "\"status\":\"400\"," +
                    "\"error\":\"Bad Request\"," +
                    "\"message\": {\"releaseYear\":[\"整数4桁で入力してください\"]}}", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 既存のVinylデータが更新される事() throws Exception {
        String response = mockMvc
                .perform(MockMvcRequestBuilders.patch("/vinyls/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"aab\",\"artist\":\"bbc\",\"label\":\"ccd\",\"releaseYear\": \"1998\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("{\"id\":1,\"title\":\"aab\",\"artist\":\"bbc\",\"label\":\"ccd\",\"releaseYear\": " +
                        "\"1998\"}",
                response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 既存のVinylデータが部分更新される事() throws Exception {
        String response = mockMvc
                .perform(MockMvcRequestBuilders.patch("/vinyls/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"aab\",\"artist\":null,\"label\":null,\"releaseYear\":null}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("{\"id\":1,\"title\":\"aab\",\"artist\":\"bb\",\"label\":\"cc\",\"releaseYear\": " +
                        "\"1999\"}",
                response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 全てのフィールドがnullだった場合更新されないこと() throws Exception {
        String response = mockMvc
                .perform(MockMvcRequestBuilders.patch("/vinyls/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("{\"id\":1,\"title\":\"aa\",\"artist\":\"bb\",\"label\":\"cc\",\"releaseYear\": " +
                        "\"1999\"}",
                response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 必須項目を制限文字数以上で更新した場合Badリクエストが返って来ること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.patch("/vinyls/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," +
                                    "\"artist\":\"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb\"," +
                                    "\"label\":\"ccccccccccccccccccccccccccccccccccccccccccccccccccc\"," +
                                    "\"releaseYear\":\"20003\"}")
                            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                     {
                     "timestamp": "2022-08-31T00:00+09:00[Asia/Tokyo]",
                     "status": "400",
                     "error": "Bad Request",
                     "message": {
                     "title":["titleは50文字以内で入力してください"],
                     "artist":["artistは50文字以内で入力してください"],
                     "label":["labelは50文字以内で入力してください"],
                     "releaseYear":["整数4桁で入力してください"]}
                    }""", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void releaseYearを整数以外で更新した場合Badリクエストが返って来ること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.patch("/vinyls/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\":\"aaaa\"," +
                                    "\"artist\":\"bbbb\"," +
                                    "\"label\":\"cccc\"," +
                                    "\"releaseYear\":\"llll\"}")
                            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("{" +
                    "\"timestamp\":\"2022-08-31T00:00+09:00[Asia/Tokyo]\"," +
                    "\"status\":\"400\"," +
                    "\"error\":\"Bad Request\"," +
                    "\"message\": {\"releaseYear\":[\"整数4桁で入力してください\"]}}", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void releaseYearを4桁未満の整数で更新した場合Badリクエストが返って来ること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.patch("/vinyls/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\":\"aaaa\"," +
                                    "\"artist\":\"bbbb\"," +
                                    "\"label\":\"cccc\"," +
                                    "\"releaseYear\":\"203\"}")
                            .header(HttpHeaders.ACCEPT_LANGUAGE, "ja-JP"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("{" +
                    "\"timestamp\":\"2022-08-31T00:00+09:00[Asia/Tokyo]\"," +
                    "\"status\":\"400\"," +
                    "\"error\":\"Bad Request\"," +
                    "\"message\": {\"releaseYear\":[\"整数4桁で入力してください\"]}}", response, JSONCompareMode.STRICT);
        }
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 指定したidのVinylDataを正常に削除できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/vinyls/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                "message":"Vinyl Data Has Been Deleted"
                }
                """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/forUpdateVinyl.yml")
    @Transactional
    void 存在しないidで削除リクエストをした場合削除するデータがありませんと返されること() throws Exception {
        try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDataTime);

            String response = mockMvc.perform(MockMvcRequestBuilders.delete("/vinyls/10"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                    "timestamp":"2022-08-31T00:00+09:00[Asia/Tokyo]",
                    "status":"404",
                    "error":"Not Found",
                    "message":"削除するデータがありません",
                    "path":"/vinyls/10"
                    }
                    """, response, JSONCompareMode.STRICT);
        }
    }
}
