package by.mrk.kirleon.model;

public record Album(String title, int releaseYear) implements AbstractModel<Album> {
	public Album(String title) {
		this(title, random.nextInt(1900, 2000));
	}
}
