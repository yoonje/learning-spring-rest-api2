# Spring Framework 정리 자료
처음 배우는 스프링 부트2 웹 부분에 REST API 부분을 버그 수정하고 정리한 코드

## Requirements
- [JDK 1.8+](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [gradle](https://gradle.org/)
- [MySQL](https://www.mysql.com/)


** MySQL 설치 및 application.yml에 맞게 설정해야함**

## Build
```
$ ./gradlew build
```

## Run
```
$ java -jar target/*.jar
```

## 책과 다른 점
- 시큐리티 설정을 제외하고 웹과 REST API 코드만 집중
- Data REST를 사용하지 않고 MVC로 API 사용
- CORS 설정을 하지 않음
- 멀티 모듈로 개발하지 않고 단일 모듈 사용
