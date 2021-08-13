package gameLogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import gameShip.Ship;

public class Board {
	
	public int[][] coordinates;
	private int verticalSize = 8;
	private int horizontalSize = 8;
	private static Random random = new Random();
	
	
	public int getHorizontalSize() {
		return horizontalSize;
	}
	public void setHorizontalSize(int horizontalBoardSize) {
		this.horizontalSize = horizontalBoardSize;
	}
	public int getVerticalSize() {
		return verticalSize;
	}
	public void setVerticalSize(int verticalBoardSize) {
		this.verticalSize = verticalBoardSize;
	}
	
	
	public void RandomShipPlacement(HashMap<String, Ship> fleet) {
		
		int row;
		int col;
		int size;
		boolean shipPlaced;
		
		coordinates = new int[getVerticalSize()][getHorizontalSize()];
		
		for (int[] rows : coordinates) {
			Arrays.fill(rows, 0);
		}
		
		for (String shipType : fleet.keySet()) {
			shipPlaced = false;
			size = fleet.get(shipType).getSize();
			
			while(!shipPlaced) {
			
				boolean horizontal = random.nextBoolean();
				boolean righttoleft = random.nextBoolean();
				
				row = random.nextInt(verticalSize);
				col = random.nextInt(horizontalSize);
				
				if (coordinates[row][col] > 0) {
					continue;
				}
				
				try {
					if (horizontal) {
						if (righttoleft) {
							for (int i = col; i > col - size; i--) {
								if (coordinates[row][i+1] != 0) {
									throw new ArrayIndexOutOfBoundsException("Index out of bounds");
								}
							}
							for (int i = col; i > col - size; i--) {
								coordinates[row][i+1] = size;
							}
							
						} else {
							for (int i = col; i < col + size; i++) {
								if (coordinates[row][i+1] != 0) {
									throw new ArrayIndexOutOfBoundsException("Index out of bounds");
								}
							}
							for (int i = col; i < col + size; i++) {
								coordinates[row][i+1] = size;
							}
						}
					} else {
						if (righttoleft) {
							for (int i = row; i > row - size; i--) {
								if (coordinates[i+1][col] != 0) {
									throw new ArrayIndexOutOfBoundsException("Index out of bounds");
								}
							}
							for (int i = row; i > row - size; i--) {
								coordinates[i+1][col] = size;
							}
						} else {
							for (int i = row; i < row + size; i++) {
								if (coordinates[i+1][col] != 0) {
									throw new ArrayIndexOutOfBoundsException("Index out of bounds");
								}
							}
							for (int i = row; i < row + size; i++) {
								coordinates[i+1][col] = size;
							}
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
				
				shipPlaced = true;
				
			}
		}
	}
	
	public void TextFileShipPlacement(HashMap<String, Ship> fleet) {
		
		String coordinatesFile = "coordinates.txt";
		
		try (BufferedReader reader = new BufferedReader(new FileReader(coordinatesFile))) {
		
		int dim = Integer.parseInt(reader.readLine());
		verticalSize = dim;
		horizontalSize = dim;
		coordinates = new int[verticalSize][horizontalSize];
		
		for (int[] rows : coordinates) {
		Arrays.fill(rows, 0);
		}

		String line = reader.readLine();
		
		while (line != null) {
			
			try (Scanner sc = new Scanner(line);) {
				sc.useDelimiter(Pattern.compile("(;)|(\\*)"));
				
				while (sc.hasNext()) {
					String shipType;
					shipType = sc.next();
					
					while (sc.hasNextInt()) {
						
						int i = sc.nextInt()-1;
						int j = sc.nextInt()-1;
						coordinates[i][j] = fleet.get(shipType).getSize();
						
					}
				}
			}		
			line = reader.readLine();				
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
	

