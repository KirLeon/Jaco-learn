package by.mrk.kirleon.module3.service;

import by.mrk.kirleon.module3.entity.Student;

import java.util.List;

public interface StudentService {
	List<Student> findStudentsByLastName(String lastName);
	
	List<Student> findStudentsByFirstName(String firstName);
	
	List<Student> findStudentsByGroup(String groupNumber);
	
	List<Student> findStudentsByCourse(int course);
	
	List<Student> findStudentsByBirthYear(int year);
}
