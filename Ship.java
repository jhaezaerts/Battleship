package gameShip;

import java.awt.Color;

public class Ship {
	
	private final String shipType;
	private int healthBar;
	
	public Ship(String shipType) {
		this.shipType = shipType;
		this.healthBar = getSize();
	}
	
	public String getShipType() {
		return this.shipType;
	}
	
	public int getHealthBar() {
		return this.healthBar;
	}
	
	public int hit() {
		this.healthBar --;
		return this.healthBar;
	}
	
	
	// use enum ShipType to get the constant values of the ship
	public int getSize() {
		ShipType ship = ShipType.valueOf(this.shipType.toUpperCase());
		return ship.getSize();
	}
	
	public Color getColor() {
		ShipType ship = ShipType.valueOf(this.shipType.toUpperCase());
		return ship.getColor();
	}
	
	
	public int getDamage() {
		ShipType ship = ShipType.valueOf(this.shipType.toUpperCase());
		return ship.getDamage();
	}

}
