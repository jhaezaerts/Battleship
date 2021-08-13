package gameGUI;

import java.awt.Color;

import javax.swing.JButton;

public class Tile extends JButton {
	
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	
	public Tile(int row, int col) {
		
		super();
		this.row = row;
		this.col = col;
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setBackground(Color.LIGHT_GRAY);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	

}
