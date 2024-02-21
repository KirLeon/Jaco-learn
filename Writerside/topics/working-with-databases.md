# Взаимодействие с базами данных

Персистентность данных является важным аспектом при разработке Java-приложений, особенно когда речь идет о долгосрочном
хранении информации. Базы данных (БД) предоставляют надежный механизм для сохранения данных между запусками приложения и
обеспечивают доступ к ним через структурированный интерфейс. В этой статье мы рассмотрим, почему использование баз
данных в Java-приложениях является необходимым шагом для обеспечения персистентности данных.

Зачем нужны базы данных?

* Сохранение состояния: Java-приложения могут быть долгоживущими сущностями, и важно сохранять их состояние между
  запусками. Базы данных предоставляют механизм для сохранения и восстановления этого состояния.
* Обеспечение целостности: БД обеспечивают средства для обеспечения целостности данных, таких как проверка на
  уникальность, ограничения целостности и транзакции, что помогает предотвратить ошибки и сохранить данные в
  согласованном
  состоянии.
* Эффективный доступ: С использованием БД приложения могут эффективно извлекать и обновлять данные, даже при работе с
  большими объемами информации.

Пример кода:

```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
private static final String USER = "username";
private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

`Java Database Connectivity` (JDBC) предоставляет стандартный способ взаимодействия с базами данных из Java-приложений.
Он
предоставляет API для выполнения SQL-запросов, обработки результатов и управления транзакциями. В этой статье мы
рассмотрим основы работы с JDBC для взаимодействия с базами данных в Java-приложениях.

Основные шаги работы с `JDBC`:

* Загрузка драйвера: Перед началом работы с базой данных необходимо загрузить JDBC-драйвер для используемой базы данных.

* Установка соединения: После загрузки драйвера устанавливаем соединение с базой данных, используя соответствующие
  параметры подключения.

* Выполнение запросов: Мы можем создавать и выполнять SQL-запросы к базе данных с помощью объектов Statement или
  `PreparedStatement`.

* Обработка результатов: После выполнения запроса мы можем обрабатывать результаты, полученные от базы данных.

* Закрытие ресурсов: Важно закрывать все ресурсы, связанные с соединением с базой данных, после их использования, чтобы
  избежать утечек ресурсов.

Пример кода:

```
import java.sql.*;

public class JDBCDemo {
public static void main(String[] args) {
try {
Connection connection = DatabaseConnection.getConnection();
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name"));
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
```

`Java Persistence API` (JPA) - это спецификация Java EE, которая предоставляет стандартный способ работы с
объектно-реляционной моделью данных в Java-приложениях. `EntityManager` - основной интерфейс JPA, который используется
для
управления жизненным циклом объектов и выполнения операций с базой данных. В этой статье мы рассмотрим, как использовать
EntityManager и JPA для работы с базами данных в Java-приложениях.

Основные понятия JPA:

* `Entity`: Объекты, которые могут быть сохранены в базе данных, называются сущностями (entities). Они обычно
  представляют
  собой классы Java с аннотациями, указывающими JPA на связь между объектом и таблицей в базе данных.

* `EntityManager`: Этот интерфейс предоставляет методы для выполнения операций над сущностями, такими как поиск,
  вставка,
  обновление и удаление.

* `EntityManagerFactory`: Фабрика, которая создает экземпляры EntityManager.

* `Transaction`: Операции над базой данных обычно выполняются в рамках транзакций, которые гарантируют атомарность,
  согласованность, изолированность и долговечность изменений.

Пример кода:

```
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAClient {
public static void main(String[] args) {
EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
EntityManager em = emf.createEntityManager();
EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            
            // Создание новой сущности
            User user = new User("John Doe", "johndoe@example.com");
            em.persist(user);
            
            // Изменение существующей сущности
            user.setEmail("newemail@example.com");
            em.merge(user);
            
            // Удаление сущности
            em.remove(user);
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
```
