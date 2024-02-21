package by.mrk.kirleon.model;

public record Laptop(String model, int ramSizeGB) implements AbstractModel<Laptop> {
	public Laptop(String model) {
		this(model, (random.nextInt(1, 8) * 4));
	}
}
