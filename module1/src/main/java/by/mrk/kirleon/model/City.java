package by.mrk.kirleon.model;

public record City(String name, int population) implements AbstractModel<City> {
	public City(String name) {
		this(name, random.nextInt(1, 14000000));
	}
}
