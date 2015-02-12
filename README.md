osrn-bot
================

## Requirements

- Java: v1.7
- Twitterアカウント & [TwitterAPI使用時の登録](http://webnaut.jp/develop/633.html)

## Setup

### Twitter認証キーの設定

このプロジェクト直下にある`token.properties.tmpl`ファイルをコピーして、`src/main/resources/token.properties`ファイルを作成。
`token.properties`ファイル内の以下のプロパティ値を設定してください。

```
consumerKey=# set consumerkey
consumerSecret=# set consumersecret
accessToken=# set accesstoken
accessTokenSecret=# set accesstokensecret
```

**※ 各プロパティ値は適宜設定してください**

### Eclipseプロジェクトファイル作成

このプロジェクトをeclipseへインポートするための各種ファイルを作成します

	./gradlew eclipse

> Windows環境では `./gradlew` を `gradlew` と読み替えてください。

上記実行後eclipseへこのプロジェクトをインポートしてください
