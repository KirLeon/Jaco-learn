import by.mrk.kirleon.StudentFactory;
import by.mrk.kirleon.exception.*;
import by.mrk.kirleon.model.Student;
import by.mrk.kirleon.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariables;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	private final StudentFactory factory = StudentFactory.INSTANCE;
	private final Random random = new Random();
	@Spy
	private final StudentService studentService = StudentService.INSTANCE;
	private List<Student> students;
	
	//can provide duplicateGroupsName
	@BeforeEach
	public void setup() {
		System.out.println(System.getProperty("func"));
		students = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			students.addAll(factory.generateStudentGroup("", 10));
		}
	}
	
	@Test
	public void parseStudentsFromFile() {
		factory.writeStudentsToFile(students);
		List<Student> actualStudentList = studentService.parseStudentsFromFile();
		
		Assertions.assertEquals(students.size(), actualStudentList.size());
		actualStudentList.removeAll(students);
		Assertions.assertEquals(0, actualStudentList.size());
	}
	
	@Test
	public void parseStudentsFromFileWithIncorrectGroupName() {
		List<Student> studentsWithIncorrectGroupName =
			Stream.of(
				String.format("%d%dк9%d91",
					random.nextInt(0, 10),
					random.nextInt(0, 10),
					random.nextInt(0, 6)),
				String.format("%dк9%d91",
					random.nextInt(0, 10),
					random.nextInt(6, 9)),
				String.format("%dк9%d83",
					random.nextInt(0, 10),
					random.nextInt(0, 6))
			).map(factory::generateStudent).toList();
		students.addAll(studentsWithIncorrectGroupName);
		
		for (int i = 0; i < 3; i++) {
			factory.writeStudentsToFile(students);
			Assertions.assertThrows(IncorrectGroupNameException.class, studentService::parseStudentsFromFile);
			students.remove(studentsWithIncorrectGroupName.get(i));
		}
	}
	
	@Test
	public void parseStudentsFromFileWithIncorrectStudentName() {
		students.add(factory.generateStudentWithIncorrectLastName("0к9011"));
		students.addAll(factory.generateStudentGroup("0к9011", 9));
		factory.writeStudentsToFile(students);
		
		Assertions.assertThrows(IncorrectStudentNameException.class, studentService::parseStudentsFromFile);
	}
	
	@Test
	public void parseStudentsFromFileWithIncorrectStudentGrades() {
		students.get(29).setHumDisciplinesAvgGrade(15.55);
		factory.writeStudentsToFile(students);
		Assertions.assertThrows(IncorrectGradesException.class, studentService::parseStudentsFromFile);
	}
	
	@Test
	public void parseStudentsFromFileWithLowStudentGrades() {
		students.addAll(factory.generateStudentGroup("7к9411", 9));
		students.add(factory.generateStudentWithUnderrateGrades("7к9411"));
		factory.writeStudentsToFile(students);
		
		Assertions.assertThrows(GradesAreTooLowException.class, studentService::parseStudentsFromFile);
	}
	
	@EnabledIfSystemProperty(named = "func", matches = "\\d*5\\d*")
	@Test
	public void findStudentBySurname() {
		String realLastName = students.get(random.nextInt(5, 25)).getLastName();
		String unrealLastName = students.get(random.nextInt(5, 25)).getLastName() + "ъ";
		
		Assertions.assertTrue(studentService.findStudentByLastName(realLastName, students).isPresent());
		Assertions.assertFalse(studentService.findStudentByLastName(unrealLastName, students).isPresent());
	}
	
	@EnabledIfSystemProperty(named = "func", matches = "\\d*6\\d*")
	@Test
	public void getSortedStudentsByGroupAverage() {
		String groupName = students.get(12).getGroup();
		List<Student> sortedStudentsByGroupAvg =
			students
				.stream()
				.filter(student -> student.getGroup().equalsIgnoreCase(groupName))
				.sorted(Comparator.comparing(Student::getTotalAvgGrade))
				.toList();
		
		List<Student> actualSortedStudentsByGroupAvg =
			studentService.getSortedStudentsByGroupAverage(groupName, students);
		Assertions.assertEquals(sortedStudentsByGroupAvg.size(), actualSortedStudentsByGroupAvg.size());
		for (int i = 0; i < sortedStudentsByGroupAvg.size(); i++) {
			Assertions.assertEquals(sortedStudentsByGroupAvg.get(i), actualSortedStudentsByGroupAvg.get(i));
		}
	}
	
	@EnabledIfSystemProperty(named = "func", matches = "\\d*7\\d*")
	@Test
	public void validateGroupAverage() {
		String groupName = "8к9391";
		List<Student> studentsToAdd = factory.generateStudentGroup(groupName, 30);
		for (int i = 0; i < 11; i++) {
			studentsToAdd.get(i).setTotalAvgGrade(4.3);
		}
		
		students.addAll(studentsToAdd);
		Assertions.assertThrows(UnderachievingGroupException.class,
			() -> studentService.validateGroupAverage(students, groupName));
		students
			.stream()
			.filter(student -> student.getGroup().equals(groupName) && student.getTotalAvgGrade() < 4.5)
			.forEach(student -> student.setTotalAvgGrade(5.0));
		Assertions.assertDoesNotThrow(() -> studentService.validateGroupAverage(students, groupName));
	}
	
	
	@EnabledIfSystemProperty(named = "func", matches = "\\d*8\\d*")
	@Test
	public void validateAlphabeticOrder() {
		String firstGroupName = students.get(8).getGroup();
		String thirdGroupName = students.get(23).getGroup();
		
		List<Student> studentsWithCorrectAlphabeticOrder = students
			.stream()
			.filter(student -> student.getGroup().equals(firstGroupName))
			.sorted(Comparator.comparing(student -> student.getLastName() + student.getFirstName()))
			.toList();
		List<Student> studentsWithWrongAlphabeticOrder = students
			.stream()
			.filter(student -> student.getGroup().equals(thirdGroupName))
			.sorted((o1, o2) ->
				(o2.getLastName() + o2.getFirstName()).compareTo(o1.getLastName() + o1.getFirstName()))
			.toList();
		
		Assertions.assertDoesNotThrow(
			() -> studentService.validateAlphabeticOrder(studentsWithCorrectAlphabeticOrder, firstGroupName));
		Assertions.assertThrows(IncorrectGroupAlphabeticOrderException.class,
			() -> studentService.validateAlphabeticOrder(studentsWithWrongAlphabeticOrder, thirdGroupName));
	}
	
	@EnabledIfSystemProperty(named = "func", matches = "\\d*9\\d*")
	@Test
	public void validateUniqueStudentsInGroup() {
		Set<String> studentNameSet = new HashSet<>();
		Student duplicate = null;
		String groupName;
		for (Student student : students) {
			if (!studentNameSet.add(student.getLastName() + student.getFirstName())) duplicate = student;
		}
		if (duplicate == null) {
			Student copy = students.get(random.nextInt(0, 30));
			groupName = copy.getGroup();
			duplicate = Student.builder()
				.group(groupName)
				.lastName(copy.getLastName())
				.firstName(copy.getFirstName())
				.build();
			students.add(duplicate);
		} else groupName = duplicate.getGroup();
		
		Assertions.assertThrows(DuplicateStudentRecordException.class,
			() -> studentService.validateUniqueStudentsInGroup(students, groupName));
		studentNameSet.clear();
		List<Student> uniqueStudentsInGroup = students
			.stream()
			.filter(student -> student.getGroup().equals(groupName))
			.filter(student -> {
				String name = student.getLastName() + student.getFirstName();
				return studentNameSet.add(name);
			}).toList();
		Assertions.assertDoesNotThrow(()
			-> studentService.validateUniqueStudentsInGroup(uniqueStudentsInGroup, groupName));
	}
}
