# Приложение для выгрузки списка клиентов для последующей рассылки. 

### Два основных назначения:
1. Выгрузить список клиентов по кастомизированной выборке
2. Отправить рекламное письмо по списку

### Использованные технологии:
+ Spring Boot (Security, SpringData, Lombok, Apache Poi, MapStruct)
+ MS SQL 2005
+ Redis

### Трудности с какими пришлось столкнуться при разработке:
+ Для соединения с такой старой базой как ms sql 2005 требуется заменять файл security.config прямо в самом языке java
+ Таблица базы сгенерированы десктоп приложением и не имеют названий, а скорее Id. Например, attr225 - название таблица. Что делает запросы нечитаемыми.

У приложение есть [фронтенд часть на Angular](https://github.com/Madrabit/fp_deps). 
***
Приложение используется в реальной работе коммерческой организацией.
