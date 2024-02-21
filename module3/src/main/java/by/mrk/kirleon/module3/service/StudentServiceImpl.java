package by.mrk.kirleon.module3.service;

import by.mrk.kirleon.module3.entity.Student;
import by.mrk.kirleon.module3.repository.StudentRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class StudentServiceImpl implements StudentService {
	
	@Inject
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> findStudentsByLastName(String lastName) {
		return studentRepository.findStudentsByLastName(lastName);
	}
	
	@Override
	public List<Student> findStudentsByFirstName(String firstName) {
		return studentRepository.findStudentsByFirstName(firstName);
	}
	
	@Override
	public List<Student> findStudentsByGroup(String groupNumber) {
		return studentRepository.findStudentsByGroup(groupNumber);
	}
	
	@Override
	public List<Student> findStudentsByCourse(int course) {
		return studentRepository.findStudentsByCourse(course);
	}
	
	@Override
	public List<Student> findStudentsByBirthYear(int year) {
		return studentRepository.findStudentsByBirthYear(year);
	}
}