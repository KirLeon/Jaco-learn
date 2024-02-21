import by.mrk.kirleon.module3.StudentServlet;
import by.mrk.kirleon.module3.entity.Student;
import by.mrk.kirleon.module3.factory.StudentFactory;
import by.mrk.kirleon.module3.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServletTest {
	
	@Mock
	private StudentService studentService;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	@InjectMocks
	private StudentServlet studentServlet;
	
	private final StudentFactory factory = StudentFactory.INSTANCE;
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*1\\d*")
	public void testHandleLastName() throws Exception {
		// Arrange
		List<Student> students = new ArrayList<>();
		String lastName = "Иванов";
		students.add(factory.getStudent("Петр", lastName));
		students.add(factory.getStudent("Петров", "Иван"));
		List<Student> expectedStudents = students.stream()
			.filter(student -> student.getLastName().equalsIgnoreCase("Иванов"))
			.toList();
		
		when(request.getParameter("value")).thenReturn(lastName);
		when(request.getPathInfo()).thenReturn("/lastName");
		when(studentService.findStudentsByLastName(lastName)).thenReturn(expectedStudents);
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		// Act
		studentServlet.service(request, response);
		writer.flush();
		
		// Assert
		verify(response).setContentType("text/html");
		verify(response).setCharacterEncoding("UTF-8");
		verify(response, atLeast(1)).getWriter();
		verify(studentService).findStudentsByLastName(lastName);
		StringBuilder expectedOutput = new StringBuilder();
		expectedStudents.forEach(expectedOutput::append);
		expectedOutput.append("\n");
		assertEquals(expectedOutput.toString().strip(), stringWriter.toString().strip());
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*2\\d*")
	public void testHandleFirstName() throws Exception {
		// Arrange
		List<Student> students = new ArrayList<>();
		String firstName = "Анна";
		students.add(factory.getStudent(firstName, "Борисенко"));
		students.add(factory.getStudent("Дима", "Борисенко"));
		List<Student> expectedStudents = students.stream()
			.filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
			.toList();
		
		when(request.getParameter("value")).thenReturn(firstName);
		when(request.getPathInfo()).thenReturn("/firstName");
		when(studentService.findStudentsByFirstName(firstName)).thenReturn(expectedStudents);
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		// Act
		studentServlet.service(request, response);
		writer.flush();
		
		// Assert
		verify(response).setContentType("text/html");
		verify(response).setCharacterEncoding("UTF-8");
		verify(response, atLeast(1)).getWriter();
		verify(studentService).findStudentsByFirstName(firstName);
		StringBuilder expectedOutput = new StringBuilder();
		expectedStudents.forEach(expectedOutput::append);
		expectedOutput.append("\n");
		assertEquals(expectedOutput.toString().strip(), stringWriter.toString().strip());
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*3\\d*")
	public void testHandleGroup() throws Exception {
		// Arrange
		List<Student> students = new ArrayList<>();
		String groupName = "0к9291";
		students.add(factory.getStudent(groupName));
		students.add(factory.getStudent("9к9091"));
		List<Student> expectedStudents = students.stream()
			.filter(student -> student.getGroupNumber().equalsIgnoreCase(groupName))
			.toList();
		
		when(request.getParameter("value")).thenReturn(groupName);
		when(request.getPathInfo()).thenReturn("/group");
		when(studentService.findStudentsByGroup(groupName)).thenReturn(expectedStudents);
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		// Act
		studentServlet.service(request, response);
		writer.flush();
		
		// Assert
		verify(response).setContentType("text/html");
		verify(response).setCharacterEncoding("UTF-8");
		verify(response, atLeast(1)).getWriter();
		verify(studentService).findStudentsByGroup(groupName);
		StringBuilder expectedOutput = new StringBuilder();
		expectedStudents.forEach(expectedOutput::append);
		expectedOutput.append("\n");
		assertEquals(expectedOutput.toString().strip(), stringWriter.toString().strip());
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*4\\d*")
	public void testHandleCourse() throws Exception {
		// Arrange
		List<Student> students = new ArrayList<>();
		int course = 3;
		students.add(factory.getStudent(course));
		students.add(factory.getStudent(course + 1));
		List<Student> expectedStudents = students.stream()
			.filter(student -> student.getCourse() == course)
			.toList();
		
		when(request.getParameter("value")).thenReturn(String.valueOf(course));
		when(request.getPathInfo()).thenReturn("/course");
		when(studentService.findStudentsByCourse(course)).thenReturn(expectedStudents);
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		// Act
		studentServlet.service(request, response);
		writer.flush();
		
		// Assert
		verify(response).setContentType("text/html");
		verify(response).setCharacterEncoding("UTF-8");
		verify(response, atLeast(1)).getWriter();
		verify(studentService).findStudentsByCourse(course);
		StringBuilder expectedOutput = new StringBuilder();
		expectedStudents.forEach(expectedOutput::append);
		expectedOutput.append("\n");
		assertEquals(expectedOutput.toString().strip(), stringWriter.toString().strip());
	}
	
	@Test
	@EnabledIfSystemProperty(named = "func", matches = "\\d*5\\d*")
	public void testHandleBirthYear() throws Exception {
		// Arrange
		List<Student> students = new ArrayList<>();
		Date birthDate = Date.from(
			LocalDateTime
				.of(2020, Month.JULY, 10, 10, 10)
				.atZone(ZoneId.systemDefault())
				.toInstant()
		);
		int yearOfBirth = birthDate.getYear();
		students.add(factory.getStudent(birthDate));
		students.add(factory.getStudent(new Date()));
		List<Student> expectedStudents = students.stream()
			.filter(student -> student.getDateOfBirth().equals(birthDate))
			.toList();
		
		when(request.getParameter("value")).thenReturn(String.valueOf(yearOfBirth));
		when(request.getPathInfo()).thenReturn("/birthYear");
		when(studentService.findStudentsByBirthYear(yearOfBirth)).thenReturn(expectedStudents);
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		// Act
		studentServlet.service(request, response);
		writer.flush();
		
		// Assert
		verify(response).setContentType("text/html");
		verify(response).setCharacterEncoding("UTF-8");
		verify(response, atLeast(1)).getWriter();
		verify(studentService).findStudentsByBirthYear(yearOfBirth);
		StringBuilder expectedOutput = new StringBuilder();
		expectedStudents.forEach(expectedOutput::append);
		expectedOutput.append("\n");
		assertEquals(expectedOutput.toString().strip(), stringWriter.toString().strip());
	}
	
}
