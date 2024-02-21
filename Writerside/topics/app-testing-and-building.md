# Тестирование и сборка приложений

В современной разработке программного обеспечения тестирование играет важную роль. Оно позволяет проверить корректность
работы приложения и обнаружить ошибки до того, как они достигнут конечного пользователя. В этом модуле речь будет идти о
том, как использовать библиотеку JUnit 5 и фреймворк Mockito для написания тестов Java-приложений.

Однако, перед тем, как перейти к теме использования классов сторонних библиотек в проекте, необходимо разобраться с тем,
как именно импортировать подобные ресурсы в локальный проект.

## Управление зависимостями {collapsible="true"}

Одна из ключевых функций Maven - управление зависимостями. Для добавления зависимости в проект нужно указать ее
координаты (groupId, artifactId и version) в файле pom.xml. Пример:

```
<dependencies>
    <dependency>
        <groupId>org.example</groupId>
        <artifactId>example-library</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

Maven автоматически загрузит эту зависимость из удаленного репозитория и добавит ее в проект.

## Жизненный цикл сборки {collapsible="true"}

Жизненный цикл (ЖЦ) сборки в Maven состоит из нескольких этапов, таких как `compile`, `test`, `package`, `install`
и `deploy`.
Каждый этап выполняет определенные действия, например, компиляцию исходного кода, выполнение тестов, упаковку проекта
и.т.д. Maven автоматически выполняет эти этапы в порядке, определенном в стандартном жизненном цикле сборки.

Этапы жизненного цикла сборки Maven
`validate`: Проверка корректности проекта, включая проверку структуры каталогов и наличие необходимых файлов.
`compile`: Компиляция исходного кода проекта.
`test`: Выполнение тестов проекта.
`package`: Упаковка скомпилированного кода и ресурсов в JAR, WAR или другой тип артефакта.
`verify`: Проверка качества кода, например, выполнение статического анализа кода или проверка на соответствие
стандартам.
`install`: Установка собранного артефакта в локальный репозиторий Maven.
`deploy`: Развертывание собранного артефакта в удаленный репозиторий или хранилище.

Кроме стандартных этапов жизненного цикла сборки Maven предоставляет возможность создания собственных циклы сборки,
определив свои наборы фаз. Подробнее об этом можно почитать
здесь: [Как создать плагин Maven](https://www.baeldung.com/maven-plugin)

Для запуска цикла сборки Maven необходимо исполнить команду `mvn` в командной строке. Например, чтобы выполнить все
этапы цикла сборки, можно использовать команду:

```mvn clean install```

## Плагины {collapsible="true"}

Плагины Maven - это инструменты, которые расширяют функциональность среды сборки Maven. Они предоставляют возможность
выполнения различных задач во время сборки проекта, таких как выполнение тестов, генерация документации, анализ кода и
многие другие. Для использования плагинов Maven необходимо добавить соответствующую конфигурацию в файл pom.xml проекта.
Каждый плагин определяется с помощью его groupId, artifactId и version, а также списка целей (goals), которые он может
выполнять. Пример:

```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

В этом примере используется плагин maven-compiler-plugin, который определяет конфигурацию компилятора Java для проекта.

Распространенные плагины Maven:

* `maven-compiler-plugin`: Плагин для компиляции исходного кода Java.
* `maven-surefire-plugin`: Плагин для выполнения тестов JUnit.
* `maven-jar-plugin`: Плагин для упаковки проекта в JAR-файл.
* `maven-install-plugin`: Плагин для установки собранного артефакта в локальный репозиторий Maven.
* `maven-deploy-plugin`: Плагин для развертывания собранного артефакта в удаленный репозиторий.

## Surefire {collapsible="true"}

Плагин Maven Surefire используется для запуска и выполнения тестов JUnit в проектах Java. Он автоматически находит и
запускает тестовые классы в проекте, выводя результаты выполнения тестов в консоль и генерирует отчеты. Его
использование позволяет запускать тесты выборочно. Пример использования данного плагина для выборочного запуска
тестового класса с передачей аргумента выбранной модели для сортировки:

```
<properties>
        //Other Maven properties
        <model>Stub</model>
        <sort>Bubble</sort>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <includes>
                        <include>${sort}SortTest</include>
                    </includes>
                    <systemPropertyVariables>
                        <model>${model}</model>
                    </systemPropertyVariables>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

В properties указаны переменные среды, которые будут доступны в Java-коде при помощи
метода `System.getEnv("propertyName")`. В конфигурации плагина в `systemPropertyVariables` указаны системные переменные,
которые будут доступны в Java-коде при помощи метода `System.getProperty("propertyName")`. Системные переменные подходят
для их использования при выборе тестовых классов на этапе тестирования приложения. Эта логика описывается в
теге `<includes>` конфигурации плагина Surefire. В данном случае к значению переменной `sort`, переданной при запуске
задачи `test` сборщика Maven командой: `mvn test -Dsort=SomeSortValue`, добавляется SortTest, на которое оканчиваются
все названия тестовых классов в папке `test/java`.

Настроить исключение определенных тестов из выполнения можно следующим образом:

```
<configuration>
    <excludes>
        <exclude>**/IntegrationTest.java</exclude>
    </excludes>
</configuration>
```

Кроме того, Surefire генерирует отчеты о выполнении тестов в каталог target/surefire-reports.

## Добавление JUnit 5 в проект {collapsible="true"}

Для добавления библиотеки JUnit 5 в проект используется следующий тег в `pom.xml`:

```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

## Использование JUnit 5 для написания тестов {collapsible="true" id="junit-5_1"}

Ниже приведен пример простого теста с использованием JUnit 5:

```
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SortTest {

    @Test
	public void shouldNotCauseNullPointerExceptionWhenGivenNullArray() {
		// given
		AbstractModel<?>[] nullArray = null;
		
		//when
		sortingService.sort(nullArray);
		
		// assert
		Assertions.assertDoesNotThrow(() -> sortingService.sort(nullArray));
	}

}
```

В этом примере используется класс Assertions, предоставляющий функционал методов-"утверждений". Метод assertDoesNotThrow
принимает экземпляр функционального интерфейса `ThrowingSupplier` и проверяет отсутствие выбрасывания исключений
результатом выполнения переданного в тело метода `T get() throws Throwable` выражения.

## Проверка равенства/неравенства значений {collapsible="true"}

Еще одно применение класса Assertions - проверка равенства/неравенства значений. Пример:

``` 
@Test
	public void shouldCompareArrayElementsInCorrectOrder() {
		//given
		AbstractModel modelToCompare = factory.createModel(model, 1);
		
		//assert
		Assertions.assertNotEquals(0, modelToCompare.compareToOtherModel(model));
		Assertions.assertEquals((modelToCompare.compareToOtherModel(model) > 0), isOrderAscending);
	}
```

Здесь две модели сначала проверяются на их разность для того, чтобы можно было их уверенно сравнивать. В тело методов
assert***Equals и assert***NotEquals (*** может быть Array, Link и.т.п. методы, переопределяющие условия сравнения
значений, например со сравнения простых ссылок на массивы, на перебор и сравнение их значений) передаются так называемые
`expected` и `actual`, причем их не стоит путать местами. Так, результат `modelToCompare.compareToOtherModel(model)`
ожидаемо должен быть 0.

После проверки на неравенство тестируется порядок сравнения. Свойство `isOrderAscending`
обозначает порядок сравнения объектов, при его значении true ожидается, что model (имеющая по умолчанию самое низкое
значение), переданная как параметр метода `compareToOtherModel` будет ниже при сравнении и результате вызова данного
метода для "большей" `modelToCompare` вернет значение, большее, чем 0.

## Добавление Mockito в проект {collapsible="true"}

Для добавления зависимости на Mockito стоит добавить следующий код в файл `pom.xml`:

```
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>4.2.0</version>
    <scope>test</scope>
</dependency>
```

## Создание заглушек {collapsible="true"}

При тестировании кода (прежде всего unit-тестировании, но не только) тестируемому элементу часто требуется предоставить
экземпляры классов, которыми он должен пользоваться при работе. При этом часто они не должны быть полнофункциональными —
наоборот, от них требуется вести себя жестко заданным образом, так, чтобы их поведение было простым и предсказуемым. Они
и называются заглушками.

Ниже приведен пример тестового класса, использующего Mockito для создания заглушки:

```
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class MyServiceTest {

    @Test
    public void testDoSomething() {
        // Создание заглушки для зависимости
        Dependency dependency = mock(Dependency.class);

        // Настройка поведения заглушки
        when(dependency.someMethod()).thenReturn("mocked result");

        // Создание объекта класса, который мы тестируем
        MyService myService = new MyService(dependency);

        // Вызов метода, который мы тестируем
        String result = myService.doSomething();

        // Проверка результата
        assertEquals("mocked result", result);
    }

}   
```

## Проверка void-методов {collapsible="true"}

Иногда может потребоваться создать заглушку для метода, который возвращает void. Для этого в Mockito используется
метод doNothing(). Ниже приведен пример:

```
@Test
public void testDoSomethingVoidMethod() {
Dependency dependency = mock(Dependency.class);
doNothing().when(dependency).voidMethod();

    // вызов метода
    myService.doSomethingWithVoidMethod();

    // проверка, что метод был вызван
    verify(dependency).voidMethod();

}
```

## Проверка факта вызовов методов {collapsible="true"}

Mockito можно использовать для проверки вызовов методов объектов. Например:

``` 
@Test
    //проверка отсутствия вызовов метода
	public void shouldNotCallInnerMethodsWhenGivenEmptyArray() {
		// given
		AbstractModel<?>[] emptyArray = new AbstractModel[]{};
		
		// when
		sortingService.sort(emptyArray);
		
		// then
		verify(sortingService, never()).mergeSort(any(), any(), anyInt(), anyInt());
		verify(sortingService, never()).merge(any(), any(), anyInt(), anyInt(), anyInt());
		
	}
	
	//проверка наличия вызова методов
	@Test
	public void shouldCallInnerMethodsAtLeastFewTimesWhenGivenNonEmptyArray() {
		
		// given
		AbstractModel<?>[] array = factory.createModels(10, model);
		
		// when
		sortingService.sort(array);
		
		// then
		verify(sortingService, atLeast(3)).mergeSort(any(), any(), anyInt(), anyInt());
		verify(sortingService, atLeast(3)).merge(any(), any(), anyInt(), anyInt(), anyInt());
	}
```

## Создание заглушек с аргументами {collapsible="true"}

Способ передачи аргументов в метод thenReturn() при создании заглушек:

```
@Test
public void testMethodWithArguments() {
    InnerService innerService = mock(InnerService.class);
    when(innerService.methodWithArgument(eq("input"))).thenReturn("output");

    //внутри вызывает метод внутреннего сервиса (сервиса-зависимости), поведение которого было переопределено выше
    String result = mainService.doSomethingWithArgument("input");

    assertEquals("output", result);
}
```

## Исключения и тестирование их вызовов {collapsible="true"}

```
@Test
public void testMethodThrowsException() {
    Dependency dependency = mock(Dependency.class);
    when(dependency.methodThatThrowsException()).thenThrow(new RuntimeException());

    assertThrows(RuntimeException.class, () -> {
        myService.doSomethingThatThrowsException();
    });

}
```
