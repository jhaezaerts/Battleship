package gameGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gameLogic.Board;
import gameLogic.SeaBattle;
import gameLogic.Settings;
import gamePlayers.Player;
import gameShip.Ship;


public class GameBoard {
	
	private HashMap<String, Ship> fleet;
	private Player playerOne;
	private Player playerTwo;
	private Board board;
	private Settings settings;
	private SeaBattle battle;
	private Color col;
	
	private JFrame frame= new JFrame("Battleship");
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	
	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	
	JButton b1 = new JButton("High Scores");
	JButton b2 = new JButton("Quit Game");
	
	JLabel l1 = new JLabel("Player 1 score: ");
	JLabel l2 = new JLabel("Turn: ");
	JLabel l3 = new JLabel("Player 2 score: ");
	JLabel l4 = new JLabel("0");
	JLabel l5 = new JLabel("Player 1");
	JLabel l6 = new JLabel("0");

	
	public GameBoard(Board board, Settings settings, Player playerOne, Player playerTwo) {
		battle = new SeaBattle(board, settings);
		this.board = board;
		this.settings = settings;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		
		b1.addActionListener(new HighScoresListener());
		b2.addActionListener(new QuitGameListener());
		
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
			
		topPanel.setLayout(new GridLayout(1, 5));
		bottomPanel.setLayout(new GridLayout(board.getVerticalSize(),board.getHorizontalSize(),5,5));
			
		p1.add(b1);
		p2.add(l1);
		p2.add(l4);
		p3.add(l2);
		p3.add(l5);
		p4.add(l3);
		p4.add(l6);
		p5.add(b2);
		
		topPanel.add(p1);
		topPanel.add(p2);
		topPanel.add(p3);
		topPanel.add(p4);
		topPanel.add(p5);
		
		
		//set up the board
		for(int i = 0; i < board.getVerticalSize(); i++) {
			for (int j = 0; j < board.getHorizontalSize(); j++) {
				Tile tile = new Tile(i, j);
				tile.addActionListener(new TileListener());
				bottomPanel.add(tile);
			}
		}
		
		frame.setPreferredSize(new Dimension(900,1200));
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(bottomPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
			
		b1.setFont(new Font("Monospaced", Font.PLAIN, 12));
		b2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		l1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		l2.setFont(new Font("Monospaced", Font.PLAIN, 15));
		l3.setFont(new Font("Monospaced", Font.PLAIN, 15));
		l4.setFont(new Font("Monospaced", Font.BOLD, 15));
		l5.setFont(new Font("Monospaced", Font.BOLD, 15));
		l6.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		b2.setAlignmentX(p5.RIGHT_ALIGNMENT);
		l2.setAlignmentX(p4.CENTER_ALIGNMENT);
		l5.setAlignmentX(p4.CENTER_ALIGNMENT);
		l1.setAlignmentX(p3.CENTER_ALIGNMENT);
		l4.setAlignmentX(p3.CENTER_ALIGNMENT);
		l3.setAlignmentX(p2.CENTER_ALIGNMENT);
		l6.setAlignmentX(p2.CENTER_ALIGNMENT);
		
		b1.setForeground(Color.white);
		b1.setBackground(Color.BLACK);
		b1.setOpaque(true);
		b1.setBorderPainted(false);
		b2.setForeground(Color.white);
		b2.setBackground(Color.BLACK);
		b2.setOpaque(true);
		b2.setBorderPainted(false);
		
		frame.getContentPane().setBackground(Color.white);
		topPanel.setBackground(Color.white);
		bottomPanel.setBackground(Color.white);
		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		p3.setBackground(Color.white);
		p4.setBackground(Color.white);
		p5.setBackground(Color.white);
		
	}

	
	private class HighScoresListener implements ActionListener {
		private JFrame b5Frame = new JFrame ("Highest Scores");
		private String text;
		private String highscoresFile = "highscores.txt";
		@Override
		public void actionPerformed(ActionEvent ev) {
			text = "";
			int counter = 0;
			try (BufferedReader reader = new BufferedReader(new FileReader(highscoresFile))) {
				String line;
				while ((line = reader.readLine()) != null) {
					if (counter == 0) {
						counter++;
						text = "" + line;
					} else {
						text = text + "\n" + line;
					}
				}
			} catch (FileNotFoundException fnf) {
				JOptionPane.showMessageDialog(b5Frame, fnf.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
			} catch (IOException io) {
				JOptionPane.showMessageDialog(b5Frame, io.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
			}
			
			JTextArea b5Text = new JTextArea(text);
			b5Text.setEditable(false);
			b5Text.setFont(new Font("Monospaced", Font.PLAIN, 12));
			b5Text.setBackground(Color.yellow);
			b5Text.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			b5Frame.getContentPane().add(b5Text);
			b5Frame.pack();
			b5Frame.setVisible(true);
			
		}
	}
	
	private class QuitGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		    playerOne.setScore(0);
		    playerTwo.setScore(0);
			frame.dispose();
		}
	}
	
	private class TileListener implements ActionListener {
		private int score;
		private String winner;
		private JFrame f = new JFrame();
		@Override
		public void actionPerformed(ActionEvent e) {
			Tile tileClicked = (Tile) e.getSource();
			score = battle.gamePlay(tileClicked);
			
			if (settings.turn == "Player2") {
				playerTwo.setScore(playerTwo.getScore() + score);
				l6.setText(String.valueOf(playerTwo.getScore()));
				l5.setText("Player 1");
				settings.turn = "Player1";
			} else {
				playerOne.setScore(playerOne.getScore() + score);
				l4.setText(String.valueOf(playerOne.getScore()));
				l5.setText("Player 2");
				settings.turn = "Player2";
			}
 
			
			if (battle.fleet.isEmpty()) {
				if (playerOne.getScore() > playerTwo.getScore()){
					winner = playerOne.getName();
					JOptionPane.showMessageDialog(f, winner + " has won the game.", "Game Over", JOptionPane.PLAIN_MESSAGE);
				} else if (playerOne.getScore() < playerTwo.getScore()) {
					winner = playerTwo.getName();
					JOptionPane.showMessageDialog(f, winner + " has won the game.", "Game Over", JOptionPane.PLAIN_MESSAGE);
				} else {
					frame.dispose();
				}
				
				settings.setHighScores(playerOne, playerTwo);

			    playerOne.setScore(0);
			    playerTwo.setScore(0);
			    
			}
		}
	}
	
}

			
