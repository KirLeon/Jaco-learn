package by.mrk.kirleon.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Student {
	
	private String group;
	
	private String lastName;
	
	private String firstName;
	
	private double schoolDisciplinesAvgGrade;
	
	private double techDisciplinesAvgGrade;
	
	private double humDisciplinesAvgGrade;
	
	private double totalAvgGrade;
	
	public double getTotalAvgGrade() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", writeStudent(), totalAvgGrade);
	}
	
	public String writeStudent() {
		return String.format(
			"%s %s %s %s %s",
			lastName,
			firstName,
			schoolDisciplinesAvgGrade,
			techDisciplinesAvgGrade,
			humDisciplinesAvgGrade
		);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Student student = (Student) o;
		
		if (Double.compare(schoolDisciplinesAvgGrade, student.schoolDisciplinesAvgGrade) != 0) return false;
		if (Double.compare(techDisciplinesAvgGrade, student.techDisciplinesAvgGrade) != 0) return false;
		if (Double.compare(humDisciplinesAvgGrade, student.humDisciplinesAvgGrade) != 0) return false;
		if (Double.compare(totalAvgGrade, student.totalAvgGrade) != 0) return false;
		if (!group.equals(student.group)) return false;
		if (!lastName.equals(student.lastName)) return false;
		return firstName.equals(student.firstName);
	}
	
	@Override
	public int hashCode() {
		int result = group.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + firstName.hashCode();
		return result;
	}
}
