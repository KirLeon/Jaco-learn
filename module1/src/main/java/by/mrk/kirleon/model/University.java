package by.mrk.kirleon.model;

public record University(String title, int numberOfFaculties) implements AbstractModel<University> {
	public University(String title) {
		this(title, random.nextInt(1, 100));
	}
	
}
