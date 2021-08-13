package gameLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import gamePlayers.Player;

public class Settings {
	
	public int highScorePlayerOne = 0;
	public int highScorePlayertwo = 0;
	public String scoringSystem = "Equal";
	public String shipPlacement = "Random";
	public String turn = "Player1";
	public String starter = "Player 1";
	
	
	public int getHighScorePlayerOne() {
		return highScorePlayerOne;
	}
	public void setHighScorePlayerOne(int highScorePlayerOne) {
		this.highScorePlayerOne = highScorePlayerOne;
	}
	public int getHighScorePlayertwo() {
		return highScorePlayertwo;
	}
	public void setHighScorePlayertwo(int highScorePlayertwo) {
		this.highScorePlayertwo = highScorePlayertwo;
	}
	public String getScoringSystem() {
		return scoringSystem;
	}
	public void setScoringSystem(String scoringSystem) {
		this.scoringSystem = scoringSystem;
	}
	public String getShipPlacement() {
		return shipPlacement;
	}
	public void setShipPlacement(String shipPlacement) {
		this.shipPlacement = shipPlacement;
	}
	
	
	public void setHighScores(Player p1, Player p2) {
		
		String highscoreFile = "highscores.txt";
		String highscore = "";
		
		try (BufferedReader reader = new BufferedReader(new FileReader(highscoreFile));) {
			
			String line;
			int counter = 0;
			
			while ((line = reader.readLine()) != null) {
				String[] singleLine = line.split(":");
				if (counter == 0) {
					counter ++;
					if (!(Integer.parseInt(singleLine[1].trim()) > p1.getScore())) {
						highscore = "Player One Highscore: " + String.valueOf(p1.getScore());
					} else {
						highscore = line;
					}
				} else {
					if (!(Integer.parseInt(singleLine[1].trim()) > p2.getScore())) {
						highscore = highscore + "\n" + "Player Two Highscore: " + String.valueOf(p2.getScore());
					} else {
						highscore = highscore + "\n" + line;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (Writer out = new BufferedWriter(new FileWriter(highscoreFile));) {
			out.write(highscore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
