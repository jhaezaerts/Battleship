package gameLogic;

import java.awt.Color;
import java.util.HashMap;

import gameGUI.Tile;
import gameShip.Ship;

public class SeaBattle {
	
	public HashMap<String, Ship> fleet = new HashMap<>();
	public Board board;
	public Settings settings;
	private int counter = 0;
	
	public SeaBattle(Board board, Settings settings) {
		this.board = board;
		this.settings = settings;
		this.settings.turn = "Player1";
		this.settings.starter = "Player1";
		
		fleet.put("Carrier", new Ship("Carrier"));
		fleet.put("Battleship", new Ship("Battleship"));
		fleet.put("Submarine", new Ship("Submarine"));
		fleet.put("Destroyer", new Ship("Destroyer"));
		
		this.placeShips();
				
	}
	
	public void placeShips() {
		if (settings.getShipPlacement() == "Random") {
			board.RandomShipPlacement(fleet);
		} else {
			board.TextFileShipPlacement(fleet);
		}
	}
	
	public int gamePlay(Tile hitTile) {
		
		int healthBar;
		Ship ship;
		int score = 0;
		int target = board.coordinates[hitTile.getRow()][hitTile.getCol()];
		Color col;
		
		switch (target) {
		
		case 0:
			hitTile.setBackground(Color.BLUE);
			score = 0;
			break;
		
		case 2:
			ship = fleet.get("Destroyer");
			col = (Color) ship.getColor();
			score = ship.getDamage();
			hitTile.setBackground((Color) col);
			healthBar = ship.hit();
			if (healthBar == 0) {
				fleet.remove("Destroyer");
				score = score*2;
			}
			break;
		
		case 3:
			ship = fleet.get("Submarine");
			col = (Color) ship.getColor();
			score = ship.getDamage();
			hitTile.setBackground(col);
			healthBar = ship.hit();
			if (healthBar == 0) {
				fleet.remove("Submarine");
				score = score*2;
			}
			break;
			
		case 4:
			ship = fleet.get("Battleship");
			col = (Color) ship.getColor();
			score = ship.getDamage();
			hitTile.setBackground(col);
			healthBar = ship.hit();
			if (healthBar == 0) {
				fleet.remove("Battleship");
				score = score*2;
			}
			break;
			
		case 5:
			ship = fleet.get("Carrier");
			col = (Color) ship.getColor();
			score = ship.getDamage();
			hitTile.setBackground(col);
			healthBar = ship.hit();
			if (healthBar == 0) {
				fleet.remove("Carrier");
				score = score*2;
			}
			break;
			
		}
		
		hitTile.setEnabled(false);

		if (!settings.scoringSystem.equals("Equal") && (!settings.turn.equals(settings.starter))) {
			if (counter < 8) {
					if (score != 0) {
						score = score - 4;
					}
			}
			counter ++;
		}

		return score;
		
	}
	
}
	

