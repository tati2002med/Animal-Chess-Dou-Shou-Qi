package app;

public class Move {

	protected int x;
	protected int y;
	protected String name;

	public Move(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	@Override
	public String toString() {
		return "(" + x + " , " + y + ") " + name + "\n";
	}

}
