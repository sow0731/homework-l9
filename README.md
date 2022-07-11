# 第9回課題概要

## 課題内容

***1***. MyBatisでR（Read）の実装

## 1.MyBatisでR（Read）の実装

・趣味で集めているアナログレコード(Vinyl)のデータベースを作成\
・"localhost:8080/vinyl-all"にアクセスするとDBに登録されている"タイトル・アーティスト・レーベル・リリース年"をレスポンスとして返す。\
・"localhost:8080/vinyl-by-id/?vinyl=id"にアクセスするとDBに登録されているidから該当のレコードのデータをレスポンスとして返す

### GETリクエスト実行結果(全件取得 / localhost:8080/vinyl-allにアクセスした場合)

![](/Users/macos/Documents/プログラミング/Raisetech課題用キャプチャ/第9回CRUD処理課題/L9 get all.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyl-by-id/?vinyl=idにアクセスした場合)

![](/Users/macos/Documents/プログラミング/Raisetech課題用キャプチャ/第9回CRUD処理課題/L9 getById.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyl-by-id/?vinyl=idに登録のないidでアクセスした場合)

![](/Users/macos/Documents/プログラミング/Raisetech課題用キャプチャ/第9回CRUD処理課題/L9 wrong ID.png)

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
│       ├── VinylService.java
│       └── VinylServiceImpl.java
└── infrastructure
    └── VinylMapper.java