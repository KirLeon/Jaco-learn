package by.mrk.kirleon.model;

public record Office(String location, int numberOfMeetingRooms) implements AbstractModel<Office> {
	public Office(String location) {
		this(location, random.nextInt(0, 100));
	}
}
