package ac.uk.york.typhon.query.generator.helper;

public enum EventEnum {
	INSERT, SELECT, UPDATE, DELETE;

	public static EventEnum getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}
}