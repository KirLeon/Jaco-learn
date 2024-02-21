package by.mrk.kirleon.module3.repository;

import by.mrk.kirleon.module3.entity.Student;

import java.util.List;

public interface StudentRepository {
	List<Student> findStudentsByLastName(String lastName);
	
	List<Student> findStudentsByFirstName(String firstName);
	
	List<Student> findStudentsByGroup(String groupNumber);
	
	List<Student> findStudentsByCourse(int course);
	
	List<Student> findStudentsByBirthYear(int year);
}
