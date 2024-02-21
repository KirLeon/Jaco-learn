package by.mrk.kirleon.model;

public record Movie(String title, double budget) implements AbstractModel<Movie> {
	public Movie(String title) {
		this(title, random.nextDouble(100.0, 4000000000D));
	}
}
