# CryptoMania

## Pristup k API dokumentaci a Swagger UI

- Swagger UI je dostupny na adrese:
  - http://localhost:8080/swagger-ui/index.html
- OpenAPI dokumentace je dostupna na adrese:
  - http://localhost:8080/v3/api-docs

## Jak zakazat Swagger UI a API dokumentaci v produkci

Swagger UI a OpenAPI dokumentaci muzete vypnout v produkcnim prostredi upravou nastaveni.

`application.properties`:
```properties
springdoc.api-docs.enabled=false
springdoc.swagger-ui.enabled=false
```

## Automaticky reload po zmene kodu

Projekt ma v zavislotech `spring-boot-devtools` pro automaticky reload aplikace po zmene kodu.

`pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

DevTools se automaticky neaktivuje v produkcnim prostredi.

## Jak zapnout/vypnout debug logy

Pro zapnuti debug logu pridej do `src/main/resources/application.properties`:

```properties
logging.level.root=DEBUG
```

Pro vypnuti debug logu v produkci nastav na INFO nebo WARN:

```properties
logging.level.root=INFO
```

---

Pro testovani endpointu aplikace je mozne vyuzit Swagger-UI nebo Bruno kolekci v `src/main/resources/bruno/CryptoMania API`.
