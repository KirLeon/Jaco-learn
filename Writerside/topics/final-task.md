# Итоговое задание

Название проекта: Управление задачами (Task Manager)

Постановка задачи:
Разработать веб-приложение для управления задачами, которое позволяет пользователям создавать, просматривать,
редактировать и удалять задачи. Приложение должно быть построено с использованием Java 17, Jakarta EE, Hibernate для
взаимодействия с базой данных, JUnit 5 и Mockito для тестирования.

Описание предметной области:
Приложение предназначено для управления задачами в команде или личных проектах. Пользователи могут создавать задачи,
указывать им приоритет, статус выполнения, сроки и другие детали. Задачи могут быть назначены на конкретных исполнителей
или команды. Пользователи могут просматривать свои задачи, редактировать их и отмечать как выполненные.

Степень покрытия тестами:

Unit-тесты:

Тесты для классов, отвечающих за бизнес-логику (например, классы для работы с задачами, пользователями и т.д.);
Тесты для проверки валидации данных (например, ввод корректных и некорректных данных при создании задачи).
Интеграционные тесты:

Тесты для проверки взаимодействия с базой данных с использованием Hibernate;
Тесты для эндпоинтов API для управления задачами.
Mock-тесты:

Использование Mockito для создания заглушек при тестировании классов, взаимодействующих с внешними сервисами или
ресурсами.

Описание сущностей:

**Пользователь (User)**:

* Идентификатор (ID)
* Имя пользователя (Username)
* Пароль (Password)
* Электронная почта (Email)
* Роль (Role) - может быть администратором или обычным пользователем
* Команда (Team):

**Команда**

* Идентификатор (ID)
* Название команды (Team Name)
* Список участников команды (List<User> Members)
* Задача (Task):

**Задача**

* Идентификатор (ID)
* Название задачи (Task Name)
* Описание задачи (Description)
* Приоритет (Priority)
* Статус (Status) - например, "в процессе", "завершено" и т.д.
* Дата начала (Start Date)
* Дата окончания (Due Date)
* Исполнитель (Assignee) - ссылка на пользователя или команду, которой назначена задача
* Комментарии (Comments) - список комментариев к задаче

Спецификация эндпоинтов:

* GET /tasks - Получить список всех задач.
* GET /tasks/{taskId} - Получить информацию о задаче по её идентификатору.
* POST /tasks - Создать новую задачу.
* PUT /tasks/{taskId} - Обновить информацию о задаче.
* DELETE /tasks/{taskId} - Удалить задачу.
* GET /users/{userId}/tasks - Получить список задач, назначенных конкретному пользователю.
* GET /teams/{teamId}/tasks - Получить список задач, назначенных конкретной команде.
* POST /users - Создать нового пользователя.
* GET /users - Получить список всех пользователей.
* GET /users/{userId} - Получить информацию о пользователе по его идентификатору.
* PUT /users/{userId} - Обновить информацию о пользователе.
* DELETE /users/{userId} - Удалить пользователя.
* POST /teams - Создать новую команду.
* GET /teams - Получить список всех команд.
* GET /teams/{teamId} - Получить информацию о команде по её идентификатору.
* PUT /teams/{teamId} - Обновить информацию о команде.
* DELETE /teams/{teamId} - Удалить команду.
* POST /teams/{teamId}/members/{userId} - Добавить пользователя в команду.
* DELETE /teams/{teamId}/members/{userId} - Удалить пользователя из команды.

Каждый эндпоинт должен возвращать соответствующие HTTP статусы в зависимости от успешности операции и корректности
запроса.