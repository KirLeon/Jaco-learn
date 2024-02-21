package by.mrk.kirleon.module3.repository;

import by.mrk.kirleon.module3.entity.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class StudentRepositoryImpl implements StudentRepository {
	@PersistenceContext(unitName = "students")
	private EntityManager entityManager;
	
	@Override
	public List<Student> findStudentsByLastName(String lastName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<Student> findStudentsByFirstName(String firstName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<Student> findStudentsByGroup(String groupNumber) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<Student> findStudentsByCourse(int course) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<Student> findStudentsByBirthYear(int year) {
		throw new UnsupportedOperationException();
	}
}