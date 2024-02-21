package by.mrk.kirleon.model;

public record Smartphone(String model, double price) implements AbstractModel<Smartphone> {
	public Smartphone(String model) {
		this(model, random.nextDouble(50.0, 9999.9));
	}
}
