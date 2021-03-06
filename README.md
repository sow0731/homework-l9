# 第9回課題概要

## 課題内容

***1***. MyBatisでR（Read）の実装

## 1.MyBatisでR（Read）の実装

・趣味で集めているアナログレコード(Vinyl)のデータベースを作成\
・"localhost:8080/vinyl-all"にアクセスするとDBに登録されている"タイトル・アーティスト・レーベル・リリース年"をレスポンスとして返す。\
・"localhost:8080/vinyl-by-id/?vinyl=id"にアクセスするとDBに登録されているidから該当のレコードのデータをレスポンスとして返す

### GETリクエスト実行結果(全件取得 / localhost:8080/vinyl-allにアクセスした場合)

![L9 get all](https://user-images.githubusercontent.com/103630732/178266034-7f954812-d853-41a8-92a7-8d5618757e3b.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyl-by-id/?vinyl=idにアクセスした場合)

![L9 getById](https://user-images.githubusercontent.com/103630732/178266111-bc5b6770-1f30-4a37-b71d-2b3cc64db424.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyl-by-id/?vinyl=idに登録のないidでアクセスした場合)

![L9 wrong ID](https://user-images.githubusercontent.com/103630732/178266146-8fc3e111-c19e-4977-9e18-2d8522312386.png)

## レイヤ構成

````
.
├── Lecture9Application.java
├── controller
│   └── VinylController.java
├── domain
│   ├── model
│   │   └── Vinyl.java
│   └── service
├── exception
│   └── ResourceNotFoundException.java
└── infrastructure
    └── VinylMapper.java

````

## 修正・更新概要

### 修正・更新内容

***1***. URL設計についての指摘を修正\
***2***. URL経路の変数がnullだった場合の処理の修正\
***3***. サービスクラスとコントローラクラスのフィールドインジェクションをコンストラクタインジェクションに変更\
***4***. 存在しない"id"で検索した場合の処理をExceptionHandlerを使った処理に変更

## 1.URL設計についての指摘を修正

・複数のレコードのデータを扱う為、valueの名前を"vinyl"から"vinyls"に変更

## 2.URL経路の変数がnullだった場合の処理の修正

・メソッドの引数のアノテーションを"@RequestParam"から"@PathVariable"に変更
・GetMappingのバリューにPathVariableがある時とない時のURIを設定し、"id"がnullだった場合の処理を追加。

### GETリクエスト実行結果(全件取得 / localhost:8080/vinylsにアクセスした場合)

![L9 update get all](https://user-images.githubusercontent.com/103630732/179193828-6b358c8e-be40-484d-b8b9-3d96add8069a.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyls/{id}にアクセスした場合)

![L9 update getById](https://user-images.githubusercontent.com/103630732/179193866-70e45b8b-9ad9-427f-8c4c-586e3e3981f7.png)

## 4.存在しない"id"で検索した場合の例外処理をExceptionHandlerを使った処理に変更

・コントローラクラスにExceptionHandlerの処理を実装し、代わりに"id"の値チェックを使った例外処理を削除

### GETリクエスト実行結果(存在しない"id"でlocalhost:8080/vinyls/{id}にアクセスした場合)

![L9 update wrong ID](https://user-images.githubusercontent.com/103630732/179193927-0f5612cd-0e5a-4adb-b8fc-a67316c996b9.png)
