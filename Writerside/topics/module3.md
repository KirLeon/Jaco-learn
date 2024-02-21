# Модуль 3

## Клиент-серверная архитектура

Клиент-серверная архитектура
: Подход к построению программных систем, при котором функциональность разделяется между клиентской стороной, отвечающей
за пользовательский интерфейс и взаимодействие с пользователем, и серверной стороной, предоставляющей данные и услуги,
необходимые клиенту.

Этот подход позволяет создавать распределенные системы, где клиенты могут быть различными устройствами или приложениями,
подключенными к сети, а серверы обеспечивают централизованный доступ к данным и ресурсам.
[Клиент-серверная архитектура](client-server-architecture.md)

## Инверсия контроля и внедрение зависимостей

Инверсия контроля (IoC) и внедрение зависимостей (DI) - это принципы разработки программного обеспечения, которые
помогают создавать более гибкие, масштабируемые и тестируемые приложения. IoC переворачивает управление потоком
выполнения приложения, делегируя ответственность за создание и управление объектами контейнеру управления. DI - это
техника реализации IoC, при которой зависимости объектов передаются им извне, обычно через конструкторы или методы,
вместо того чтобы создаваться внутри объекта самим объектом.

[Инверсия контроля и внедрение зависимостей](ioc-di.md)

## Взаимодействие с базами данных

Взаимодействие с базами данных - это ключевой аспект многих программных приложений, который позволяет им сохранять и
извлекать данные из постоянного хранилища. Базы данных предоставляют механизм для организации, хранения и управления
данными, а различные технологии и API, такие, как JDBC, JPA и ORM-фреймворки, обеспечивают удобные способы работы с ними
из приложений на языке Java.

[Взаимодействие с базами данных](working-with-databases.md)