package by.mrk.kirleon;

import by.mrk.kirleon.model.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public enum StudentFactory {
	
	INSTANCE;
	
	private final Random random = new Random();
	private final List<String> maleNames = List.of("Александр", "Алексей", "Андрей", "Арсений", "Артем",
		"Артур", "Борис", "Валерий", "Василий", "Виталий", "Владимир", "Владислав", "Георгий", "Глеб", "Григорий",
		"Даниил", "Денис", "Дмитрий", "Евгений", "Егор", "Иван", "Илья", "Кирилл", "Константин", "Леонид", "Максим",
		"Михаил", "Никита", "Николай", "Олег", "Павел", "Петр", "Роман", "Руслан", "Семен", "Сергей", "Станислав",
		"Тимофей", "Тимур", "Федор", "Юрий");
	
	private final List<String> femaleNames = List.of("Александра", "Алина", "Алиса", "Алла", "Алена",
		"Анастасия", "Анна", "Арина", "Валерия", "Варвара", "Вера", "Вероника", "Виктория", "Дарья", "Ева", "Евгения",
		"Екатерина", "Елена", "Елизавета", "Ирина", "Карина", "Кристина", "Ксения", "Людмила", "Маргарита", "Мария",
		"Милана", "Надежда", "Олеся", "Ольга", "Полина", "Светлана", "София", "Софья", "Татьяна", "Ульяна", "Элина",
		"Юлия", "Яна");
	
	private final List<String> maleLastNames = List.of("Артамонов", "Баженов", "Власов", "Воронов",
		"Воронцов", "Глушков", "Дементьев", "Емельянов", "Ермолаев", "Ермолов", "Захаров", "Зимин", "Злобин",
		"Иванов", "Иванов", "Калинин", "Карташов", "Козлов", "Комаров", "Кондратьев", "Коровин", "Королев",
		"Макаров", "Марков", "Мельников", "Мещеряков", "Мишин", "Павлов", "Петров", "Савельев", "Сазонов", "Седов",
		"Селезнев", "Сергеев", "Смирнов", "Соколов", "Соколов", "Соколов", "Сухой", "Тарасов", "Фомичев", "Харитонов",
		"Шаповалов");
	
	private final List<String> femaleLastNames = List.of("Алексеева", "Андреева", "Анисимова", "Афанасьева",
		"Бирюкова", "Богданова", "Васильева", "Герасимова", "Голубева", "Горшкова", "Егорова", "Кириллова",
		"Козловская", "Кольцова", "Кононова", "Кузнецова", "Лебедева", "Мартынова", "Никитина", "Николаева",
		"Овсянникова", "Орлова", "Петрова", "Полякова", "Попова", "Рудакова", "Медведева", "Свиридова", "Сидорова",
		"Смирнова", "Степанова", "Терентьева", "Тихомирова", "Фадеева", "Филимонова");
	
	public Student generateStudent(String groupName) {
		return random.nextInt(2) % 2 == 0
			? createFemaleStudent(groupName, List.of())
			: createMaleStudent(groupName, List.of());
	}
	
	public List<Student> generateStudentGroup(String groupName, int quantity) {
		if (groupName == null || groupName.isEmpty() || groupName.isBlank()) {
			groupName = String.format("%dк9%d91", random.nextInt(0, 10), random.nextInt(0, 6));
		}
		List<Student> students = new ArrayList<>(quantity + 1);
		for (int i = 0; i < quantity; i++) {
			students.add(generateStudent(groupName));
		}
		return students;
	}
	
	public void writeStudentsToFile(List<Student> students) {
		students.sort(Comparator.comparing(Student::getGroup));
		Path filePath = Paths.get("src", "main", "resources", "students.txt");
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(filePath))) {
			String group = null;
			Student student;
			int studentNumber = 1;
			for (int i = studentNumber; i < students.size() + 1; i++) {
				student = students.get(i - 1);
				if (!student.getGroup().equalsIgnoreCase(group)) {
					group = student.getGroup();
					studentNumber = 1;
					writer.printf("\n%s\n", group);
				}
				writer.printf("%d %s\n", studentNumber, student.writeStudent());
				studentNumber++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Student generateStudentWithUnderrateGrades(String groupName) {
		List<Double> grades = List.of(
			random.nextDouble(1.0, 5.0),
			random.nextDouble(1.0, 5.0),
			random.nextDouble(1.0, 5.0)
		);
		return random.nextInt(2) % 2 == 0
			? createFemaleStudent(groupName, grades)
			: createMaleStudent(groupName, grades);
	}
	
	public Student generateStudentWithIncorrectLastName(String groupName) {
		List<Double> grades = getUpperScoreGrades();
		return new Student(
			groupName,
			"error",
			"e500",
			grades.get(0),
			grades.get(1),
			grades.get(2),
			0.0
		);
	}
	
	private Student createFemaleStudent(String groupName, List<Double> marks) {
		List<Double> grades = marks.isEmpty() ? getUpperScoreGrades() : marks;
		return new Student(
			groupName,
			femaleLastNames.get(random.nextInt(femaleLastNames.size())),
			femaleNames.get(random.nextInt(femaleNames.size())),
			grades.get(0),
			grades.get(1),
			grades.get(2),
			0.0
		);
	}
	
	private Student createMaleStudent(String groupName, List<Double> marks) {
		List<Double> grades = (marks == null || marks.isEmpty()) ? getUpperScoreGrades() : marks;
		return new Student(
			groupName,
			maleLastNames.get(random.nextInt(maleLastNames.size())),
			maleNames.get(random.nextInt(maleNames.size())),
			grades.get(0),
			grades.get(1),
			grades.get(2),
			0.0
		);
	}
	
	private List<Double> getUpperScoreGrades() {
		return Stream.of(
				random.nextDouble(3.0, 10.0),
				random.nextDouble(4.0, 10.0),
				random.nextDouble(5.0, 10.0)
			)
			.map(value -> {
				String format = String.format("%.2f", value).replace(",", ".");
				return Double.valueOf(format);
			})
			.toList();
	}
}
