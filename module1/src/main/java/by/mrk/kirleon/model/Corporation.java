package by.mrk.kirleon.model;

public record Corporation(String name, int numberOfEmployees) implements AbstractModel<Corporation> {
	public Corporation(String name) {
		this(name, random.nextInt(1, 10000000));
	}
}
