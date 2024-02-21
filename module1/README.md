Задача: реализовать алгоритм сортировки по варианту для класса-модели.
Классы модели представлены Record-ами и хранятся в папке `model`. Они наследуют интерфейс `AbstractModel`,
параметризованный собственным типом данным. Это дает возможность наследникам реализовать его вариацию,
параметризованными самим собой и переопределить метод `compareToOtherModel` с изменением типа принимаемого параметра на
собственный.

Сортировки хранятся в пакете `service` и наследуют интерфейс `SortingService`. Он определяет метод `sort`, являющийся
точкой входа для всех сортировок и принимает массив `AbstractModel`.

Важно! Не следует реализовывать логику сортировки в одном методе `sort`. В каждой из трех сортировок, предложенных для
реализации, есть другие методы, выполняющие определенный функционал. Справка об их предназначении и требования к их
реализации есть в Javadoc над ними.

Сортировка пузырьком уже реализована, на ее примере можно понять, как реализован механизм сравнения объектов (для этого
стоит внимательно посмотреть на `AbstractModel`).

Классы-модели и критерия их сравнения для вариантов:

1. Album - по убыванию года выпуска
2. Band - по увеличению числа выпущенных альбомов
3. Cafe - по увеличению средней цены
4. City - по уменьшению количества населения
5. Corporation - по увеличению количества сотрудников
6. Laptop - по уменьшению объема оперативной памяти
7. Movie - по уменьшению бюджета фильма
8. Office - по увеличению количества комнат для переговоров
9. Smartphone - по уменьшению стоимости устройства
10. University - по увеличению количества факультетов

Класс Stub нужен для тестирования по умолчанию и не соответствует ни одному из вариантов. На его примере можно понять
принцип реализации метода `compareToOtherModel`.

Варианты задания (сортировка + модель + команда для запуска тестов):

1. Quick + Album `mvn test -Dsort=Quick -Dmodel=album`
2. Merge + Band `mvn test -Dsort=Merge -Dmodel=band`
3. Tree + Cafe `mvn test -Dsort=Tree -Dmodel=cafe`
4. Quick + City `mvn test -Dsort=Quick -Dmodel=city`
5. Merge + Corporation `mvn test -Dsort=Merge -Dmodel=corp`
6. Tree + Laptop `mvn test -Dsort=Tree -Dmodel=lap`
7. Quick + Movie`mvn test -Dsort=Quick -Dmodel=movie`
8. Merge + Office `mvn test -Dsort=Merge -Dmodel=off`
9. Tree + Smartphone `mvn test -Dsort=Tree -Dmodel=phone`
10. Quick + University `mvn test -Dsort=Quick -Dmodel=uni`
11. Merge + Album `mvn test -Dsort=Merge -Dmodel=album`
12. Tree + Band `mvn test -Dsort=Tree -Dmodel=band`
13. Quick + Cafe `mvn test -Dsort=Quick -Dmodel=cafe`
14. Merge + City `mvn test -Dsort=Merge -Dmodel=city`
15. Tree + Corporation `mvn test -Dsort=Tree -Dmodel=corp`
16. Quick + Laptop `mvn test -Dsort=Quick -Dmodel=lap`
17. Merge + Movie `mvn test -Dsort=Merge -Dmodel=movie`
18. Tree + Office `mvn test -Dsort=Tree -Dmodel=off`
19. Quick + Smartphone `mvn test -Dsort=Quick -Dmodel=phone`
20. Merge + University `mvn test -Dsort=Merge -Dmodel=uni`
21. Tree + Album `mvn test -Dsort=Tree -Dmodel=album`
22. Quick + Band `mvn test -Dsort=Quick -Dmodel=band`
23. Merge + Cafe `mvn test -Dsort=Merge -Dmodel=cafe`
24. Tree + City `mvn test -Dsort=Tree -Dmodel=city`
25. Quick + Corporation `mvn test -Dsort=Quick -Dmodel=corp`
26. Merge + Laptop `mvn test -Dsort=Merge -Dmodel=laptop`
27. Tree + Movie `mvn test -Dsort=Tree -Dmodel=movie`
28. Quick + Office `mvn test -Dsort=Quick -Dmodel=off`
29. Merge + Smartphone `mvn test -Dsort=Merge -Dmodel=phone`
30. Tree + University `mvn test -Dsort=Tree -Dmodel=uni`

Команда для запуска тестов: `mvn test -Dsort=<your_sort> -Dmodel=<your_model>`, где sort - {Quick, Merge, Tree}, model -
{названия моделей; university и laptop можно сокращать до трех первых букв; corporation - до corp; smaptphone - до
phone; office - до off}

Пример: `mvn test -Dsort=Buble -Dmodel=stub`