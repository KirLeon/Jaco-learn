package by.mrk.kirleon.model;

public record Stub(int stubCode) implements AbstractModel<Stub> {
	public Stub() {
		this(random.nextInt(1, 100));
	}
	
	@Override
	public int compareToOtherModel(Stub model) {
		return this.stubCode - model.stubCode;
	}
}
