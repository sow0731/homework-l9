# 第9回課題修正・更新概要

## 修正・更新内容

***1***. URL設計についての指摘を修正
***2***. URL経路の変数がnullだった場合の処理の修正

## 1.URL設計についての指摘を修正

・複数のレコードのデータを扱う為、valueの名前を"vinyl"から"vinyls"に変更

## 2.URL経路の変数がnullだった場合の処理の修正

・メソッドの引数のアノテーションを"@RequestParam"から"@PathVariable"に変更
・GetMappingのバリューにPathVariableがある時とない時のURIを設定し、"id"がnullだった場合の処理を追加。

### GETリクエスト実行結果(全件取得 / localhost:8080/vinylsにアクセスした場合)

![](/Users/macos/Documents/プログラミング/Raisetech課題用キャプチャ/第9回CRUD処理課題/L9 update get all.png)

### GETリクエスト実行結果(idから該当のデータを取得 / localhost:8080/vinyls/{id}にアクセスした場合)

![](/Users/macos/Documents/プログラミング/Raisetech課題用キャプチャ/第9回CRUD処理課題/L9 update getById.png)

