package by.mrk.kirleon.module3.factory;

import by.mrk.kirleon.module3.entity.Student;

import java.util.Date;
import java.util.Random;

public enum StudentFactory {
	
	INSTANCE;
	
	private final Random random = new Random();
	
	public Student getStudent(String firstName, String lastName) {
		return Student.builder().id(getId()).firstName(firstName).lastName(lastName).build();
	}
	
	public Student getStudent(String groupName) {
		return Student.builder().id(getId()).groupNumber(groupName).build();
	}
	
	public Student getStudent(Date date) {
		return Student.builder().id(getId()).dateOfBirth(date).build();
	}
	
	public Student getStudent(int course) {
		return Student.builder().id(getId()).course(course).build();
	}
	
	public long getId() {
		return random.nextLong();
	}
}
