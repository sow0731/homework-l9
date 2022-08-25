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

![L9 get all](https://user-images.githubusercontent.com/103630732/178266034-7f954812-d853-41a8-92a7-8d5618757e3b.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyls/{id}にアクセスした場合)

![L9 getById](https://user-images.githubusercontent.com/103630732/178266111-bc5b6770-1f30-4a37-b71d-2b3cc64db424.png)

### GETリクエスト実行結果(存在しない"id"でlocalhost:8080/vinyls/{id}にアクセスした場合)

![L9 wrong ID](https://user-images.githubusercontent.com/103630732/178266146-8fc3e111-c19e-4977-9e18-2d8522312386.png)

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
