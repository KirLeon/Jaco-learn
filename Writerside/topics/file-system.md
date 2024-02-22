# Работа с файловой системой

Файловая система
: Способ организации и хранения файлов на диске компьютера. В Java файловая система представляется
в виде иерархии файлов и каталогов, где каждый файл или каталог имеет свое имя и расположение.

Ресурсы файловой системы
: Файлы, каталоги, символические ссылки и другие объекты, с которыми можно взаимодействовать в Java приложениях.

Отличительной чертой многих языков программирования является работа с файлами и потоками. В Java основной функционал
работы с потоками сосредоточен в классах из пакетов java.io и java.nio.

Ключевым понятием здесь является понятие потока. Хотя понятие "поток" в программировании довольно перегружено и может
обозначать множество различных концепций. В данном случае применительно к работе с файлами и вводом-выводом речь будет
идти о потоке (stream), как об абстракции, которая используется для чтения или записи информации (файлов, сокетов,
текста консоли и т.д.).

Поток связан с реальным физическим устройством с помощью системы ввода-вывода Java. Так, может быть определен поток,
связанный с файлом и через который может идти чтение или запись файла. Это также может быть поток, связанный с
сетевым сокетом, с помощью которого можно получить или отправить данные в сети. Все эти задачи: чтение и запись
различных файлов, обмен информацией по сети, ввод-вывод в консоли решаются в Java с помощью потоков.

Объект, из которого можно считать данные, называется потоком ввода. В свою очередь, объект, в который можно записывать
данные, — потоком вывода. Например, если надо считать содержание файла, то применяется поток ввода, а если надо записать
в файл - то поток вывода. В основе всех классов, управляющих потоками байтов, находятся два абстрактных класса:
`InputStream` (представляющий потоки ввода) и `OutputStream` (представляющий потоки вывода). Поскольку работать с
байтами не очень удобно, то для работы с потоками символов были добавлены абстрактные классы `Reader`
(для чтения потоков символов) и `Writer` (для записи потоков символов).

Все остальные классы, работающие с потоками, являются наследниками этих абстрактных классов. Основные классы потоков:

![java-file-streams](filestreams.png)

## Класс File {collapsible="true"}

Класс `File`, определенный в пакете java.io, не работает напрямую с потоками. Его задачей является управление
информацией о файлах и каталогах. Хотя на уровне операционной системы файлы и каталоги отличаются, в Java они
описываются одним классом `File`.

Создание объекта `File`
Для создания объекта `File` можно использовать несколько конструкторов, принимающих имя файла или путь к файлу в виде
строки или объекта `Path`. Примеры:

```
// Создание объекта File для файла "example.txt"
File file1 = new File("example.txt");

// Создание объекта File для каталога "myFolder"
File file2 = new File("myFolder");

// Создание объекта File из объекта Path
Path path = Paths.get("example.txt");
File file3 = path.toFile();
```

Основные методы класса `File`:

* boolean createNewFile(): создает новый файл по пути, который передан в конструктор. В случае удачного создания
  возвращает true, иначе false
* boolean exists(): проверяет, существует ли по указанному в конструкторе пути файл или каталог. И если файл или каталог
  существует, то возвращает true, иначе возвращает false
* boolean isDirectory(): возвращает значение true, если по указанному пути располагается каталог
* boolean delete(): удаляет каталог или файл по пути, который передан в конструктор. При удачном удалении возвращает
  true.
* getName(): Возвращает имя файла или каталога.
* getPath(): Возвращает путь к файлу или каталогу.
* isDirectory(): Проверяет, является ли объект файлом или каталогом.
* isFile(): Проверяет, является ли объект файлом.
* mkdir(): Создает каталог.
* long lastModified(): возвращает время последнего изменения файла или каталога. Значение представляет количество
  миллисекунд, прошедших с начала эпохи Unix

Примеры использования

```
// Проверка существования файла
File file = new File("example.txt");
if (file.exists()) {
System.out.println("Файл существует");
} else {
System.out.println("Файл не существует");
}

// Создание каталога
File directory = new File("myFolder");
if (directory.mkdir()) {
System.out.println("Каталог создан");
} else {
System.out.println("Ошибка при создании каталога");
}

// Удаление файла или каталога
File toDelete = new File("toBeDeleted.txt");
if (toDelete.delete()) {
System.out.println("Файл удален");
} else {
System.out.println("Ошибка при удалении файла");
}
```

## Чтение и запись файлов {collapsible="true"}

Для считывания данных из файла предназначен класс `FileInputStream`, который является наследником класса `InputStream` и
поэтому реализует все его методы. Для создания объекта `FileInputStream` можно использовать ряд конструкторов. Одна из
версий конструктора в качестве параметра принимает путь к считываемому файлу:

`FileInputStream(String fileName) throws FileNotFoundException`

Если файл не может быть открыт, например, по указанному пути такого файла не существует, генерируется исключение
`FileNotFoundException`.

Пример обработки `IOException` в блоке кода, использующем `FileInputStream`:

```
import java.io.*;
 
public class Program {
  
    public static void main(String[] args) {
 
        try(FileInputStream fin=new FileInputStream("notes.txt"))
        {    
            int i;
            while((i=fin.read())!=-1){
              
                System.out.print((char)i);
            }   
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
    }
}
```

В примере выше экземпляр `FileInputStream` создается прямо в скобках блока `try`. Такая конструкция называется
**try-with-resources** и ее работа заключается в автоматической генерации блока `finally` с закрыванием потока,
созданного в блоке try. Для использования пользовательских классов внутри этой конструкции необходимо наследование
интерфейса `AutoCloseable`.

Подробнее о **try-with-resources** и `AutoCloseable` можно почитать здесь:
[Оператор try-with-resources](https://javarush.com/quests/lectures/questsyntaxpro.level15.lecture00)

Класс `FileOutputStream` предназначен для записи байтов в файл. Он является производным от класса `OutputStream`,
поэтому наследует всю его функциональность. Через конструктор класса `FileOutputStream` задается файл, в который
производится запись. Класс поддерживает несколько конструкторов:

* `FileOutputStream(String filePath)`
* `FileOutputStream(File fileObj)`
* `FileOutputStream(String filePath, boolean append)`
* `FileOutputStream(File fileObj, boolean append)`

Файл задается либо через строковый путь, либо через объект `File`. Второй параметр - append задает способ записи: если
он
равен true, то данные дозаписываются в конец файла, а при false - файл полностью перезаписывается

Использование класса Reader

```
import java.io.*;
 
public class Program {
  
    public static void main(String[] args) {
          
        String text = "Hello world!"; // строка для записи
        try(FileOutputStream fos=new FileOutputStream("notes.txt"))
        {
            // перевод строки в байты
            byte[] buffer = text.getBytes();
              
            fos.write(buffer, 0, buffer.length);
            System.out.println("The file has been written");
        }
        catch(IOException ex){
              
            System.out.println(ex.getMessage());
        }
    }
}
```

## Работа с байтовыми и символьными потоками {collapsible="true"}

В Java существуют два основных вида потоков: байтовые и символьные. Байтовые потоки (`InputStream` и `OutputStream`)
предназначены для работы с байтовыми данными, такими как изображения, аудиофайлы и другие двоичные данные.

Символьные потоки (`Reader` и `Writer`) предназначены для работы с текстовыми данными, где символы представлены в
кодировке Unicode. Они предоставляют удобные методы для работы с текстовыми данными, такими как чтение и запись строк, а
также автоматическое преобразование символов из и в Unicode. Они также обеспечивают локализацию и поддержку различных
кодировок.

Различия между байтовыми и символьными потоками
Основное различие между байтовыми и символьными потоками заключается в том, как они обрабатывают данные. Байтовые потоки
работают с байтами напрямую, в то время как символьные потоки используют символы и автоматически преобразуют их в байты
с помощью указанной кодировки.

Примеры использования:

**Байтовые потоки**

```

try (InputStream inputStream = new FileInputStream("image.jpg");
  OutputStream outputStream = new FileOutputStream("copy.jpg")) {
  byte[] buffer = new byte[1024];
  int bytesRead;
  while ((bytesRead = inputStream.read(buffer)) != -1) {
    outputStream.write(buffer, 0, bytesRead);
  }
} catch (IOException e) {
  e.printStackTrace();
}

```

**Символьные потоки**

```

try (Reader reader = new FileReader("text.txt"); Writer writer = new FileWriter("copy.txt")) {
  char[] buffer = new char[1024];
  int charsRead;
  while ((charsRead = reader.read(buffer)) != -1) {
    writer.write(buffer, 0, charsRead);
  }
} catch (IOException e) {
  e.printStackTrace();
}

```

## Работа с классом Files и интерфейсом Path {collapsible="true"}

Класс `Path` и интерфейс `Files` в пакете java.nio.file предоставляют удобные средства для работы с путями к файлам и
каталогам в Java. В этой части мы рассмотрим основные методы этих классов и интерфейсов, а также рассмотрим примеры их
использования.

Методы интерфейса `Path`:

* String getFileName(): возвращает имя файла или директории, на который указывает путь
* boolean isAbsolute(): возвращает true, если путь абсолютный, иначе false
* boolean exists(): возвращает true, если файл или директория по указанному пути существует, иначе false
* boolean isRegularFile(): возвращает true, если путь указывает на обычный файл, иначе false
* boolean isDirectory(): возвращает true, если путь указывает на директорию, иначе false
* Path getParent(): возвращает родительскую директорию пути
* Path toAbsolutePath(): возвращает абсолютный путь
* Path resolve(Path other): объединяет текущий путь с другим путем и возвращает новый путь
* Path relativize(Path other): возвращает относительный путь от текущего пути до другого пути

Пример использования интерфейса `Path`:

```
// Создание объекта Path из строки
Path path = Paths.get("folder", "file.txt");

// Создание объекта Path из URI
Path uriPath = Paths.get(new URI("file:///folder/file.txt"));

// Получение абсолютного пути
Path absolutePath = path.toAbsolutePath();
```

`Files` содержит статические методы для выполнения операций над файлами и каталогами в файловой системе. Он
предоставляет методы для чтения, записи, копирования, удаления файлов и многих других операций.

Основные методы интерфейса `Files`:

* static boolean exists(Path path, LinkOption... options): проверяет, существует ли файл или каталог по заданному пути
* static boolean isDirectory(Path path, LinkOption... options): проверяет, является ли путь каталогом
* static Path createDirectory(Path dir, FileAttribute<?>... attrs): создает каталог по заданному пути
* static long/Path copy(Path source, Path target, CopyOption... options): копирует файл или каталог
* static void delete(Path path): удаляет файл или каталог
* static void deleteIfExists(Path path): удаляет файл или каталог, если он существует

Примеры использования методов класса `Files` с применением интерфейса `Path`:

```
// Проверка существования файла
Path path = Paths.get("folder", "file.txt");
boolean exists = Files.exists(path);

// Создание каталога
Path directory = Paths.get("newFolder");
Files.createDirectory(directory);

// Копирование файла
Path sourcePath = Paths.get("sourceFile.txt");
Path targetPath = Paths.get("targetFolder", "targetFile.txt");
Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

// Удаление файла
Path fileToDelete = Paths.get("fileToDelete.txt");
Files.delete(fileToDelete);
```

## Использование буферизованных потоков

Для оптимизации операций ввода-вывода используются буферизуемые потоки. Эти потоки добавляют к стандартным специальный
буфер в памяти, с помощью которого повышается производительность при чтении и записи потоков.

Класс `BufferedInputStream` накапливает вводимые данные в специальном буфере без постоянного обращения к устройству
ввода. Он определяет два конструктора:

* `BufferedInputStream(InputStream inputStream)`
* `BufferedInputStream(InputStream inputStream, int bufSize)`
  Первый параметр - это поток ввода, с которого данные будут считываться в буфер. Второй параметр - размер буфера.

Пример использования `ByteArrayInputStream` для буферного считывания данных из потока:

``` 
import java.io.*;

public class Program {

    public static void main(String[] args) {
         
        String text = "Hello world!";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buffer);
         
        try(BufferedInputStream bis = new BufferedInputStream(in)){
             
            int c;
            while((c=bis.read())!=-1){
         
                System.out.print((char)c);
            }
        }
        catch(Exception e){
             
            System.out.println(e.getMessage());
        }
        System.out.println();
    } 

}
```

Класс `BufferedInputStream` в конструкторе принимает объект `InputStream`. В данном случае таким объектом является
экземпляр класса `ByteArrayInputStream`.

Как и все потоки ввода `BufferedInputStream` обладает методом `read()`, который считывает данные. При этом каждый байт
считывается при помощи метода read из массива buffer.

Фактически все то же самое можно было сделать и с помощью одного `ByteArrayInputStream`, не прибегая к буферизированному
потоку. Класс `BufferedInputStream` просто оптимизирует производительность при работе с потоком `ByteArrayInputStream`.
Естественно вместо `ByteArrayInputStream` может использоваться любой другой класс, который унаследован от `InputStream`.

Класс `BufferedOutputStream` аналогично создает буфер для потоков вывода. Этот буфер накапливает выводимые байты без
постоянного обращения к устройству. Когда буфер заполнится, будет произведена запись данных.

`BufferedOutputStream` определяет два конструктора:

* `BufferedOutputStream(OutputStream outputStream)`
* `BufferedOutputStream(OutputStream outputStream, int bufSize)`

Первый параметр - это поток вывода, который унаследован от `OutputStream`, а второй параметр - размер буфера.

Пример использования `BufferedOutputStream` для записи в файл:

``` 
import java.io.*;

public class Program {

    public static void main(String[] args) {
         
        String text = "Hello world!"; // строка для записи
        try(FileOutputStream out=new FileOutputStream("notes.txt"); 
                BufferedOutputStream bos = new BufferedOutputStream(out))
        {
            // перевод строки в байты
            byte[] buffer = text.getBytes();
            bos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
    } 

}
```

Класс `BufferedOutputStream` в конструкторе принимает в качестве параметра объект `OutputStream` - в данном случае это
файловый поток вывода `FileOutputStream`. И также производится запись в файл. Опять же `BufferedOutputStream` не
добавляет много новой функциональности, он просто оптимизирует действие потока вывода.

## Лучшие практики при работе с потоками ввода-вывода в Java: {collapsible="true"}

* Использование try-with-resources: Для автоматического закрытия потоков рекомендуется использовать конструкцию
  try-with-resources.
* Указание кодировки: При работе с символьными потоками не будет лишним указать нужную кодировку.
* Буферизация: Для увеличения производительности при работе с файлами рекомендуется использовать буферизацию с помощью
  классов BufferedReader и BufferedWriter.

## Контроль знаний

1. Дать определение файловой системе.
2. Какое исключение необходимо обрабатывать при работе с большинством методов файловой системы?
3. Для чего предназначены класс File и интерфейс Files и какие предоставляют методы?
4. Описать отличие байтовых потоков от символьных.
5. Какой интерфейс нужно реализовывать классу для его использования в конструкции try-with-resources?
6. Как работают и для чего нужны буферизованные потоки?