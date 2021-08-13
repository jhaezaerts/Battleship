package gameShip;

import java.awt.Color;

public enum ShipType {
	
	CARRIER(5, Color.RED, 8),
	BATTLESHIP(4, Color.ORANGE, 20),
	SUBMARINE(3, Color.PINK, 25),
	DESTROYER(2, Color.MAGENTA, 50);
	
	private final int size;
	private final Color color;
	private final int damage;
	
	private ShipType(int s, Color c, int d) {
		this.size = s;
		this.color = c;
		this.damage = d;
	}
	
	public int getSize() {
		return size;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getDamage() {
		return damage;
	}

}
