package gamePlayers;

public class Player implements PlayerInterface {
	
	private int score = 0;
	private String name;
	
	public Player(String n) {
		this.name = n;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}
	
}
