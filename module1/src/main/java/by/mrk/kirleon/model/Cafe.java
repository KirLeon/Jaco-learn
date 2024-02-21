package by.mrk.kirleon.model;

public record Cafe(String title, double avgPrice) implements AbstractModel<Cafe> {
	public Cafe(String title) {
		this(title, random.nextDouble(2.0, 999.9));
	}
}
