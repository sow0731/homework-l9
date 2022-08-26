# 最終課題 CRUDアプリ概要

趣味で集めているアナログレコード(Vinyl)のデータベースを作成予定

---

## 機能一覧

***1***. Vinylデータ全件取得\
***2***. idで特定のVinylデータを指定して取得\
***3***. 新たにVinylデータを登録
<!-- 
* 編集
* 削除
-->

## DBテーブル
テーブル名：vinyl

| カラム| データ型 | NotNull | 備考 |
| ------------ | ------------- | ------------- | ------------- | 
| id | Integer | |主キー |
| title | VARCHAR(100) | NOT NULL |
| artist | VARCHAR(100)  | NOT NULL |
| label | VARCHAR(100) | NOT NULL |
| release_year | int(4) |

---

## URL設計
| 画面名／機能名     | メソッド | URL          | 
|-------------| ------------ |-----------------| 
| Vinylデータ全件取得     | GET | /vinyls      |
| idから特定のVinylデータを取得 | GET | /vinyls/{id} |
| Vinylデータ新規登録　| POST | /vinyls |

---

### GETリクエスト実行結果(全件取得 / localhost:8080/vinyls にアクセスした場合)

![2022-08-26 getAll](https://user-images.githubusercontent.com/103630732/186865306-0d9ff33a-730b-4765-b494-701391cda9fb.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyls/{id}にアクセスした場合)

![2022-08-26 getById](https://user-images.githubusercontent.com/103630732/186865629-80255d37-a803-472c-b725-c4b108c08776.png)

### GETリクエスト実行結果(存在しない"id"でlocalhost:8080/vinyls/{id}にアクセスした場合)

![2022-08-26 notFoundException](https://user-images.githubusercontent.com/103630732/186867573-05c708c8-ad2c-4b8d-9d4f-42e169c36af8.png)

### POSTリクエスト実行結果(新規登録localhost:8080/vinylsにアクセスした場合)

![2022-08-26 postNewVinyl](https://user-images.githubusercontent.com/103630732/186867842-9ecd22b7-e1e9-4773-9101-f76b404986c6.png)

### POSTリクエスト実行後にGETリクエストを実行した結果(全件取得 / localhost:8080/vinyls にアクセスした場合)

![2022-08-26 getAllAfterPost](https://user-images.githubusercontent.com/103630732/186868501-788a4ba6-4ba3-49e1-966b-c084ef195ba1.png)

### POSTリクエスト実行後にGETリクエストを実行した結果(idから該当のデータを取得 / localhost:8080/vinyls/4 にアクセスした場合)

![2022-08-26 getByIdAfterPost](https://user-images.githubusercontent.com/103630732/186868866-27d9999d-c174-42bf-980c-b1190297e19c.png)

## テスト一覧
- **VinylMaperTest**
  - Vinylデータが全件取得できること
  - 指定したidから特定のVinylデータが取得できること
  - 存在しないidでリクエストをした場合取得するデータが空であること
  - 新たにVinylデータが登録できること
- **VinylServiceImplTest**
  - VinylMapperから取得したVinylをそのまま返すこと
  - 存在するVinylのidを指定した時に正常にVinylが返されること
  - 存在しないVinylのidを指定した時に正常に例外が投げられていること
  - 新たにVinylを追加できること
- **VinylIntegrationTest**
  - 全てのVinylデータが取得できること
  - idを指定し特定のVinylデータを取得できること
  - 存在しないidでリクエストした場合例外がスローされること
  - Vinylデータが新たに登録できること
