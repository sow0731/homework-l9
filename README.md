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


