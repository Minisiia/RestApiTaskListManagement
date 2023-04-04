## RestApiTaskListManagement
Create a RESTful service for managing the list of tasks. Each task must contain a title and description.
## 8.Spring 7. RESTful webservices
## Задание 3

Необходимо создать RESTful сервис для управления списком задач. Каждая задача должна содержать название и описание. Для хранения задач необходимо использовать базу данных MySQL.

API должен содержать следующие эндпоинты:

 - GET /tasks - получение списка всех задач
 - GET /tasks/{id} - получение задачи по идентификатору
 - POST /tasks - создание новой задачи
 - PUT /tasks/{id} - обновление задачи по идентификатору
 - DELETE /tasks/{id} - удаление задачи по идентификатору
 
 ## 8.Spring 10. Boot
 
 ## Задание 2
 
 Спробуйте змінити Tomcat на Jetty.
 
 ## Заметки
 
 ### ModelMapper
 
 ModelMapper - это библиотека Java, которая помогает сопоставлять (маппить) объекты разных классов друг на друга. Она предназначена для упрощения процесса маппинга и уменьшения количества повторяющегося кода, связанного с копированием значений из одного объекта в другой.

В контексте Spring, ModelMapper может использоваться для преобразования DTO (Data Transfer Object) в сущности и обратно, что может быть полезно при работе с REST API.

ModelMapper использует конфигурационный файл, который определяет, какие поля в объектах будут сопоставлены между собой. Он также поддерживает различные стратегии для сопоставления полей, такие как соответствие по имени, по типу и по конвертерам. Все это позволяет легко настроить сопоставление объектов и сделать его более гибким.

Чтобы использовать ModelMapper в Spring, необходимо добавить зависимость в файл pom.xml или build.gradle и настроить бин в конфигурационном файле Spring. После этого можно использовать ModelMapper для сопоставления объектов.

 ### Замена Tomcat на Jetty
 
 1. Исключить зависимость от Tomcat  из spring-boot-starter-web. 
 
 ```
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

2. Добавить зависимость Jetty

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```
