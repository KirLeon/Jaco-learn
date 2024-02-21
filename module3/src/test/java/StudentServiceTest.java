import by.mrk.kirleon.module3.entity.Student;
import by.mrk.kirleon.module3.repository.StudentRepositoryImpl;
import by.mrk.kirleon.module3.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	@Mock
	private StudentRepositoryImpl studentRepository = Mockito.mock(StudentRepositoryImpl.class);
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	private List<Student> students;
	
	@BeforeEach
	public void setUp() {
		students = new ArrayList<>();
		students.add(new Student(1L, "Иван", "Иванов", new Date(), "0к9191", 1, 8.5));
		students.add(new Student(2L, "Анна", "Иванов", new Date(), "0к9291", 2, 7.7));
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*1\\d*")
	public void testFindStudentsByLastName() {
		when(studentRepository.findStudentsByLastName("Иванов")).thenReturn(students);
		
		List<Student> foundStudents = studentService.findStudentsByLastName("Иванов");
		
		assertEquals(students, foundStudents);
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*2\\d*")
	public void testFindStudentsByFirstName() {
		when(studentRepository.findStudentsByFirstName("Иван")).thenReturn(students);
		
		List<Student> foundStudents = studentService.findStudentsByFirstName("Иван");
		
		assertEquals(students, foundStudents);
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*3\\d*")
	public void testFindStudentsByGroup() {
		when(studentRepository.findStudentsByGroup("0к9191")).thenReturn(students);
		
		List<Student> foundStudents = studentService.findStudentsByGroup("0к9191");
		
		assertEquals(students, foundStudents);
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*4\\d*")
	public void testFindStudentsByCourse() {
		when(studentRepository.findStudentsByCourse(1)).thenReturn(students);
		
		List<Student> foundStudents = studentService.findStudentsByCourse(1);
		
		assertEquals(students, foundStudents);
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*5\\d*")
	public void testFindStudentsByBirthYear() {
		when(studentRepository.findStudentsByBirthYear(2000)).thenReturn(students);
		
		List<Student> foundStudents = studentService.findStudentsByBirthYear(2000);
		
		assertEquals(students, foundStudents);
	}
}
