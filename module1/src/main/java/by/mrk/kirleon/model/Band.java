package by.mrk.kirleon.model;

public record Band(String name, int numberOfAlbums) implements AbstractModel<Band> {
	public Band(String name) {
		this(name, random.nextInt(0, 100));
	}
}
