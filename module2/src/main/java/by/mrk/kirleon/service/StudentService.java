package by.mrk.kirleon.service;

import by.mrk.kirleon.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public enum StudentService {
	INSTANCE;
	private final Pattern GROUP_NAME_REGEX = Pattern.compile("[0-9]к9[0-5][9|1]1");
	private final Pattern STUDENT_FIRST_AND_LAST_NAME_REGEX = Pattern.compile("[А-Я][а-я]+\\s[А-Я][а-я]+");
	private final Pattern STUDENT_GRADES_REGEX = Pattern.compile("([1-9].\\d+\\s){2}[1-9].\\d+");
	
	public List<Student> parseStudentsFromFile() {
		throw new UnsupportedOperationException();
	}
	
	private Student parseStudentFromLine(String groupName, String line) {
		throw new UnsupportedOperationException();
	}
	
	public Optional<Student> findStudentByLastName(String lastName, List<Student> students) {
		throw new UnsupportedOperationException();
	}
	
	public List<Student> getSortedStudentsByGroupAverage(String groupName, List<Student> students) {
		throw new UnsupportedOperationException();
	}
	
	public void validateGroupAverage(List<Student> students, String groupName) {
		throw new UnsupportedOperationException();
	}
	
	public void validateAlphabeticOrder(List<Student> students, String groupName) {
		throw new UnsupportedOperationException();
	}
	
	public void validateUniqueStudentsInGroup(List<Student> students, String groupName) {
		throw new UnsupportedOperationException();
	}
	
	private List<Student> filterStudentsByGroup(List<Student> students, String groupName) {
		throw new UnsupportedOperationException();
	}
	
	private void calculateStudentsAverageGrade(List<Student> students) {
		throw new UnsupportedOperationException();
	}
}


