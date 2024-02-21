# Алгоритмы сортировки

## Простые алгоритмы сортировки

Одним из основных критериев производительности алгоритма является его временная сложность (O), выражающая зависимость
количества операций или времени, необходимых для выполнения алгоритма в зависимости от размера входных данных.
Оптимальной сложностью является O(1). При данном значении алгоритм всегда затрачивает одинаковое количество операций и
времени вне зависимости от размера входных данных. Такой сложностью обладает, например, доступ к элементу массива. При
O(n) время выполнения алгоритма пропорционально размеру входных данных. Данной сложность, называющейся ещё линейной,
будет обладать просмотр всех элементов массива.Также зачастую используются O(log(n)), O(n(log(n))), O(n^2^), O(n^3^).

Классификация алгоритмов сортировки достаточно обширна и по этой причине тут будут приведены лишь основные критерии.
Кроме вышеупомянутой временной сложности алгоритма, часто называемой его эффективностью, алгоритмы сортировки также
обладают стабильностью (или устойчивостью), затратам дополнительной памяти, чувствительностью к начальному состоянию и
ещё нескольким критериям, которые, по мнению автора, не столь важны для образовательной цели и не будут упомянуты здесь.

Под стабильностью алгоритма понимается свойство, гарантирующее сохранение исходного порядка элементов с одинаковыми
значениями после сортировки. Затраты дополнительной памяти определяют количество памяти, необходимое для выполнения
сортировки, помимо исходного массива данных, включая временные структуры или массивы для обработки элементов. Наконец,
чувствительность к начальному состоянию отражает степень влияния изначального состояния (частичной отсортированности)
элементов на эффективность работы конкретного алгоритма сортировки.

Кроме того, по принципу действия среди алгоритмов сортировки можно выделить рекурсивные и нерекурсивные алгоритмы.
Первые из них используют принцип рекурсии, где задача сортировки разбивается на более мелкие части, которые решаются с
использованием того же алгоритма. Примерами рекурсивных сортировок являются быстрая сортировка и сортировка слиянием.
Нерекурсивные сортировки не используют принцип рекурсии и обычно реализуются через циклы или итеративные процессы.
Сортировка пузырьком и сортировка вставками являются их распространенными примерами.

В данной статье будут рассмотрены следующие алгоритмы сортировки: пузырьковая сортировка, быстрая сортировка, сортировка
слиянием и сортировка деревом.

Пузырьковая сортировка проста в реализации, но обладает достаточно низкой эффективностью. Она работает путем прохода по
массиву, меняя местами соседние элементы, если они находятся в неправильном порядке. Пузырьковая сортировка имеет
квадратичную временную сложность O(n^2^) в худшем и среднем случае и линейную O(n) в лучшем случае. Потребление
дополнительной памяти составляет O1 благодаря отсутствию необходимости создания дополнительных массивов. Алгоритм
быстрой сортировки выбирает опорный элемент, разделяет массив на части, меньшие и большие опорного, и рекурсивно
сортирует эти части. В худшем случае эффективность быстрой сортировки также может быть O(n^2^), но в среднем и лучшем
случае – Olog(n) . Наихудшая оценка достигается при неудачном выборе опорного элемента. Для быстрой сортировки затраты
дополнительной памяти характеризуются Olog(n) . Данная сортировка не является устойчивой, но может стать такой при
внесении в критерий сравнения двух элементов не только их значение, но и порядок в массиве.

При сортировке слиянием массив разделяется на две части и рекурсивно сортируется, после чего отсортированные части
объединяются в один массив. Данная рекурсивная сортировка достаточно сильно похожа на быструю, однако имеет всегда
одинаковую эффективность – O(log(n)) , за что платит большим потреблением дополнительной памяти – O(n) из-за
необходимости создания дополнительных массивов.

Сортировка деревом за счет дополнительной памяти быстро решает вопрос с добавлением очередного элемента в
отсортированную часть массива. Причём в роли отсортированной части массива выступает бинарное дерево. Дерево формируется
буквально на лету при переборе элементов. Основная затратная операция – вставка элемента на свое место в отсортированной
части массива здесь решена. Проблема здесь кроется в том, что шанс получения несбалансированного дерева из входящего
набора элементов велик, что приводит к временной сложности O(n^2^) в худшем случае. В основном же, эффективность
данного алгоритма составляет O(log(n) , а затраты на дополнительную память – O(n).

## Сортировка пузырьком {collapsible="true"}

Сортировка пузырьком – одна из самых очевидных и простых. Её часто используют в качестве примера при изучении
алгоритмов. Во время сортировки пузырьком для n элементов массива необходимо сделать n – 1 проходов по нему, во время
каждого из которых соседние элементы сравниваются и меняются местами при необходимости. То есть, если на вход подаётся
массив из 5 элементов: [2, 8, 3, 6, 1], недостаточно сделать одну итерацию по циклу, поменяв местами значения примерно
так: [2, 3, 6, 1, 8]. В случае нахождения наименьшего элемента на последней позиции для того, чтобы гарантированно
отсортировать массив, необходимо проитерироваться (n – 1) * (n – 1) раз, что и приводит к квадратичной сложности.

Пример сортировки пузырьком:

```
public void bubbleSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j] > array[j + 1]) {
					int swap = array[j];
					array[j] = array[j + 1];
					array[j + 1] = swap;
				}
			}
		}
	}
```

При квадратичном возрастании количества элементов время работы данного алгоритма будет значительно возрастать, что,
несмотря на константные затраты дополнительной памяти (O1), делает её неэффективным для прикладного применения.

## Быстрая сортировка {collapsible="true"}

Быстрая сортировка является достаточно распространённой и неплохо показывает себя на практике. Её основная идея в
рекурсивной сортировке левой и правой от разделительного элемента частей массива. Таким образом, важным этапом при её
разработке является оптимальный выбор разделительного (или опорного) элемента.
Основных подходов при выборе разделительного элемента существует несколько: выбор первого, среднего, последнего,
случайного или медианного элемента. Также существуют несколько методик более сложных и эффективных, о них можно
почитать, например, здесь: ссылка.
Сигнатура метода выбора разделительного элемента обычно выглядит следующим образом:

int partition (int begin, int end, int [] array) {}

Так как быстрая сортировка не использует дополнительных массивов и работает с ссылкой на исходный массив, в метод также
передаются индексы начального и конечного элемента для сортировки. Возвращаемое значение данного метода представляет
индекс опорного элемента, слева от которого по окончанию работы метода будут находиться элементы, меньшие, либо равные
опорному, а справа – большие опорного. Вариант реализации данного метода с выбором последнего элемента в качестве
разделителя (опорного, он же – pivot) представлена ниже:

```
int partition (int begin, int end, int [] array) {
    int pivot = array[end];
    int i = begin - 1;

    for (int j = begin; j < end; j++) {
        if (array[j] <= pivot) { 
            i++;
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	i++;
	array[end] = array[i];
	array[i] = pivot;
	return i;

}
```

Важно понять значение переменных `i` и `j`. `j`, как нетрудно догадаться, увеличивается с каждой итерацией, перебирая
элементы массива. Что же представляет `i`, почему оно на единицу меньше индекса начального элемента массива, и почему ее
значение увеличивается только при нахождении меньшего либо равного элемента, а также после цикла?

Когда цикл встречает элемент, меньший либо равный опорному, `i` увеличивается и после этого происходит обмен двух
элементов. С учетом того, что изначально `j` на единицу впереди `i`, если первые несколько элементов массива меньше либо
равны опорному, они меняются местами сами с собой. `i` попросту догоняет `j` на каждой итерации благодаря соблюдению
условия `if (array[j] <= pivot).`

Когда цикл встречает элемент, больший опорного, он идет дальше, увеличивая значение `j`, но оставляя `i` на том же
месте. Так, получается, что `i` всегда занимает позицию перед элементом, большим опорного, фактически отслеживая
подобные элементы. Наконец, после того, как снова встречается элемент меньший, либо равный опорному, `i` увеличивается,
указывая на позицию отслеживаемого элемента, и происходит обмен. Больший элемент уходит направо, а меньший – налево.
Что же происходит в конце, ведь опорный элемент (последний в данном случае) занимает позицию справа, хотя логично,
что он должен встать на место разделителя?

Именно с этой целью после окончания цикла значение `i` увеличивается вне зависимости от значения каких-либо других
переменных. Происходит смена мест опорного элемента, стоящего последним, и последнего элемента, на котором остановился
рост `i`. Получается, что даже при отсутствии при итерации элементов, выполняющих проверку `if (array[j] <= pivot)`,
больший опорного элементы все равно поменяется с ним местами, расставив все на корректные позиции – элементы меньшие,
чем опорный, и большие, чем опорный. В конце метода возвращается индекс разделительного элемента.
Теперь, о самом методе, выполняющим функции быстрой сортировки. Он может выглядеть, например, следующим образом:

```
public void quickSort(int begin, int end, int[] array) {
    if (begin < end) {
        int partitionIndex = partition(begin, end, array);
        quickSort(begin, partitionIndex - 1, array);
        quickSort(partitionIndex + 1, end, array);
    }
}
```

Здесь проверка в начале тела метода служит для того, чтобы проверить необходимость дальнейших действий. Она нужна не
столько для начала работы метода при его первичном вызове, сколько для того, чтобы рекурсивные вызовы внутри (вызываемые
без проверки значения аргументов) не выполнялись при длине отрезка массива, предназначенного для сортировки, (в данный
отрезок не включен опорный элемент), меньше двух элементов. К примеру, если значение `begin` = 0, а значение `end` = 1,
то отрезок, состоящий из двух элементов с индексами 0 и 1 может нуждаться в упорядочении. Это логично, ведь несмотря на
то,
что данные элементы стоят слева от опорного и, следовательно, имеют значение меньше либо равное значению опорного, между
собой они могут не соблюдать порядок (к примеру, [2, 1, 3 (опорный), 5, …]). В то же время при вызове метода для отрезка
с длиной 1, а следовательно, равными значениями переменных `begin` и `end`, тело метода не будет выполняться.

Далее, все достаточно просто, вызов метода `partition` (описанного выше) упорядочивает элементы, находя опорный,
перемещая
элементы с большим значением направо от него, а с меньшим, либо равным, – налево, и возвращая индекс опорного элемента.
После окончания его работы сортировка вызывается для левого и правого, по отношению к нахождению опорного элемента,
отрезков массива.

## Сортировка слиянием {collapsible="true"}

Сортировка слиянием – еще один из распространенных алгоритмов сортировки, который обеспечивает эффективную устойчивую
сортировку массивов. Основная идея этого метода заключается в разделении массива пополам, рекурсивной сортировке каждой
половины и последующем их объединении (слиянии) в упорядоченный массив.

Одним из ключевых шагов в данной сортировке является слияния двух упорядоченных массивов. Этот этап требует
дополнительной памяти, так как он создает временный массив для слияния элементов, однако он же обеспечивает устойчивость
сортировки, сохраняя порядок одинаковых элементов. Например, метод слияния может выглядеть следующим образом:

```
public void merge(int[] array, int[] tempArray, int leftIndex, int middleIndex, int rightIndex) {

	for (int i = leftIndex; i <= rightIndex; i++) {
		tempArray[i] = array[i];
	}
		
	int i = leftIndex;
	int j = middleIndex + 1;
	int resultArrayIndex = i;
		
	while (i <= middleIndex && j <= rightIndex) {
		if (tempArray[i] <= tempArray[j]) {
			array[resultArrayIndex] = tempArray[i];
			i++;
		} else {
			array[resultArrayIndex] = tempArray[j];
			j++;
		}
		resultArrayIndex++;
	}
		
	while (i <= middleIndex) {
		array[resultArrayIndex] = tempArray[i];
		resultArrayIndex++;
		i++;
	}

}
```

Для лучшего понимая того, что происходит в методе, стоит взглянуть на сортировку целиком. Ниже представлены методы sort
и `mergeSort`. В данном примере `sort` является точкой входа в сортировку. Внутри него создаётся и заполняется данными
копия оригинального массива, после чего вызывается метод `mergeSort`. Данный метод работает рекурсивно, постоянно
разбивая массив до размера одного элемента.

В данной реализации вместо настоящего создания массивов с все
меньшим количеством элементов в целях экономии памяти и повышения эффективности, данный метод работает с индексами
элементов, где `leftIndex` представляет собой индекс крайнего левого элемента для этой итерации разбиения массива,
`rightIndex` аналогично представляет собой индекс крайнего правого элемента, а `middleIndex` – индекс середины массива.
В тех случаях, когда значение `leftIndex` равняется значению `middleIndex`, работа идет с массивом, состоящим из двух
элементов.

```
public void mergeSort(int[] array) {
    int[] tempArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
        tempArray[i] = array[i];
    }
    mergeSort(array, tempArray, 0, array.length - 1);
}   

public void mergeSort(int[] array, int[] tempArray, int leftIndex, int rightIndex) {
    if (leftIndex < rightIndex) {
        int middleIndex = (leftIndex + rightIndex) / 2;
        mergeSort(array, tempArray, leftIndex, middleIndex);
        mergeSort(array, tempArray, middleIndex + 1, rightIndex);
        merge(array, tempArray, leftIndex, middleIndex, rightIndex);
    }
}
```

После попадания массива в метод `mergeSort`, куда также передается копия массива и индексы начального и конечного
элемента
для разбиения и последующей сортировки, происходит следующее:

- Проверяется размер массива для сортировки и разбиения. Если левый индекс больше либо равен правому, массив состоит из
  одного элемента и работа текущего вызова `mergeSort` не имеет смысла, работу нужно передавать дальше – либо работе
  `mergeSort` для следующей части массива, либо методу merge для их объединения. Если длина массива – 2 и больше, ищется
  индекс центрального элемента. После этого метод рекурсивно вызывает сам себя, передавая «координаты» нового массива.
  Так, получая на вход массив [3,7,2] метод разобьет его на массивы [3,7] и [2]. Массив [3,7] (`leftIndex` = 0,
  `rightIndex`, он же `middleIndex` для прошлой итерации, = 1) в свою очередь будет разбит на массивы [3] и [7].
  Здесь важно держать в голове то, что работа идет не с настоящими мини-массивами, а лишь с одним, где мы оперируем
  индексами начала и конца аналогичных срезов массивов. Про tempArray пока можно забыть, он передается лишь для хранения
  ссылки на себя, и работа с ним происходит в методе `merge`;
- После разбиения массива [3,7], у которого `middleIndex` = 0, на массивы [3] и [7] аргументы метода будут выглядеть
  следующим образом. Для [3] `leftIndex` и `rightIndex` будут равны 0 и 0 соответственно. Здесь значение `rightIndex`
  получено при вызове метода из переданного `middleIndex`. Для [7] `leftIndex` и `rightIndex` будут равны 1 и 1.
  Здесь, `leftIndex` получен при вызове метода из `middleIndex`, к которому прибавлен 1. Очень важно понять, откуда
  взялись эти цифры, потому что в дальнейшем многое будет на них опираться. Итак, равные значения `leftIndex` и
  `rightIndex` для обоих «мини-массивов» приводят к тому, что проверка `if (`leftIndex` < `rightIndex`)` не выполняется
  и
  действие данных рекурсивных вызовов оканчивается. Очередь переходит к методу `merge`;
- Метод `merge` принимает тот же массив. Кроме того, он принимает ссылку на массив-копию и индексы элементов, которые
  будут объединены в оригинальный массив. В примере, описанном выше, метод `merge` будет впервые вызван со значения
  аргументов `array = [3,7,2], tempArray = [3,7,2], leftIndex = 0, middleIndex = 0, rightIndex = 1`. Работа метода
  происходит после деления в методе `mergeSort` на массивы [3] и [7]. Здесь в самом начале освежается состояние
  массива-копии, так как в дальнейшем порядок хранения значений в нем влияет не только на устойчивость данного алгоритма
  сортировки, но и в целом на его корректность. Причем, достаточно освежить только ту часть, с которой сейчас будет
  работать метод – от `leftIndex` до `rightIndex` включительно. Следующим шагом является введение переменных `i` и `j`.
  Их
  задача – контролировать итерацию выбранной части массива (среза), а их ограничения – концы двух
  отрезков: `middleIndex` включительно для левой, а для правой, начинающейся со следующего после `middleIndex` элемента,
  им является `rightIndex`, также включительно. Переменная `resultArrayIndex` служит для хранения индекса массива, на
  который будет помещен следующий элемент из среза массива;
- После этого начинается магия: цикл `while` перебирает элементы из обоих частей массива и сравнивает их. Меньший
  попадает
  на свою позицию (`resultArrayIndex`) в оригинальный массив, заменяя стоящий там до него элемент. Счетчик для той части
  среза (левой или правой) увеличивается вместе с `resultArrayIndex`. Причем, вполне возможна ситуация, когда все
  элементы
  из левой части оказываются меньше даже первого элемента из правой и цикл заканчивает свою работу со значением i, не
  проходящим проверку. Кажется, что в таком случае логично после этого цикла сделать ещё один цикл `while` только для
  правого счетчика и аналогичный ему для левого, но не все так просто. Здесь важно помнить, что несмотря на то, что
  между собой элементы левой и правой части не отсортированы, внутри своего отрезка они гарантировано расположены по
  возрастанию. Таким образом, если все элементы из левой части меньше первого элемента из правой (где все элементы
  расположены по возрастанию), элементы из левой части перезапишут значения, после которых в правильном порядке пойдут
  элементы из правой части среза.
- Так, когда метод `merge` срабатывает впервые, он работает с отрезками, состоящими из одного элемента. Он сравнивает 3
  и 7 и так, как 3 меньше, заменяет 3 в оригинальном массиве само на себя, после чего заканчивает свою работу. 7
  остается на том же месте, что и была. По итогу срезы массивов [3] и [7] объединены в [3,7]. В конце работы метода
  `merge` стек вызовов методов «раскручивается» обратно, вызывая метод `merge` на срезы, предшествующие [3] и [7]
  (оригинальный массив – [3,7,2]). Так, метод `merge` вызывается на [3,7] и [2], где следуя изложенным выше принципам 2
  занимает первую позицию. После замены вместе с `resultArrayIndex` увеличивается и значение `j`, тем самым, завершив
  работу первого цикла ``while`` (`rightIndex` = 2, `j` становится равным 3). Тут и пригождается второй цикл `while`.
  После замены array состоит из [2,7,2], `i` остается равным 0, `middleIndex` – 1, `resultArrayIndex` также равен 1.
  Копия массива, обновленная в начале метода `merge` содержит элементы [3,7,2]. Начиная с элемента с индексом `i` (0) и
  до тех пор, пока выполняется условие `while` (i <= `middleIndex`), элементы из tempArray будут замещать элементы
  array, начиная с позиции `resultArrayIndex` (1). Так, спустя две итерации array будет выглядеть следующим образом:
  [2,3,7].

## Сортировка бинарным деревом поиска {collapsible="true"}

В основе данной сортировки, как и следует из названия, лежит бинарное дерево поиска. Первый элемент становится корнем,
дальнейшие элементы со значением больше движутся направо от него, со значением меньше либо равные ему – налево. Для
того, чтобы элементы ссылались на своих потомков, имеет смысл применить тип данных подобный Node:

```
class Node{
  int val;
  Node leftNode;
  Node rightNode;
}
```

Для формирования дерева по очереди рекурсивно размещаем элементы:

``` 
private void addNote(Node node, int value) {
	if (value > node.value) {
		if (node.rightNode == null) node.rightNode = new Node(value);
		else addNote(node.rightNode, value);
	} else if (node.leftNode == null) node.leftNode = new Node(value);
	else addNote(node.leftNode, value);
}
```

После чего симметрично обходим дерево в следующем порядке:

- Левое поддерево
- Текущий элемент
- Правое поддерево

``` 
private int inorderTraversal(Node node, int[] array, int index) {
	if (node != null) {
		index = inorderTraversal(node.leftNode, array, index);
		array[index++] = node.value;
		index = inorderTraversal(node.rightNode, array, index);
	}
	return index;
}
```