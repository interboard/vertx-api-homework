# vertx-api-homework
筆試作業

我是有投履歷的李紹銘
因為來不及了，目前寫這些，確定是可以跑的，
但來不及用docker還有交易控制
但我會繼續努力，希望未來還有挑戰的機會

使用方式
安裝Intellij IDEA
用gradle開新專案

build.gradle 
內容

plugins {
    id 'java'
}

group 'vertex'
version '1.0-SNAPSHOT'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile 'io.vertx:vertx-core:3.7.1'
    compile 'io.vertx:vertx-web:3.7.1'
    compile 'io.vertx:vertx-config:3.7.1'
    compile group: 'mysql', name: 'mysql-connector-java', version: '8.0.16'
    testCompile group: 'junit', name: 'junit', version: '4.12'
}

db用

CREATE TABLE `member` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(20) NOT NULL DEFAULT '' ,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

測試時用postman打
http://127.0.0.1:8091/api/帳號名

ps.6/6 11點前有寄信，結果被退回了
