package gameGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gameLogic.Board;
import gameLogic.Settings;
import gamePlayers.Player;


public class MainMenu {
	
	private Player playerOne;
	private Player playerTwo;
	
	private Board board;
	private Settings settings;
	
	// Main menu design elements
	private JFrame frame = new JFrame();
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p2in = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p3in = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel p4in = new JPanel();
	private JPanel p5 = new JPanel();
	
	private JLabel l1 = new JLabel("Welcome to Battleship");
	private JLabel l2 = new JLabel("board size:");
	private JLabel l3 = new JLabel("number of rows");
	private JLabel l4 = new JLabel("number of columns");
	
	private JButton b1 = new JButton("Choose Ship Placement");
	private JButton b2 = new JButton("Choose Scoring System");
	private JButton b3 = new JButton("Start Game");
	private JButton b4 = new JButton("Rules");
	private JButton b5 = new JButton("High Scores");
	private JButton b6 = new JButton("Exit");
	
	private static SpinnerNumberModel spinmodel1 = new SpinnerNumberModel(8, 8, 16, 1);
	private static SpinnerNumberModel spinmodel2 = new SpinnerNumberModel(8, 8, 16, 1);
	private static JSpinner spin1 = new JSpinner(spinmodel1);
	private static JSpinner spin2 = new JSpinner(spinmodel2);
	
	
	// ship placement design elements
	JLabel shipLabel = new JLabel("How would you like your ships to be placed?");
	JButton txtButton = new JButton("Preset");
	JButton randButton = new JButton("Random");
	JFrame b1Frame = new JFrame("Ship Placement");
	JPanel b1Panel1 = new JPanel();
	JPanel b1Panel2 = new JPanel();
	
	// scoring system design elements
	JFrame b2Frame = new JFrame("Scoring System");
	
	
	public MainMenu(Settings settings) {
		
		this.settings = settings;
		this.board = new Board();
		playerOne = new Player("Player 1");
		playerTwo = new Player("Player 2");
		
		b1.addActionListener(new ShipPlacementListener());
		b2.addActionListener(new ScoringSystemListener());
		b3.addActionListener(new StartGameListener());
		b4.addActionListener(new RulesListener());
		b5.addActionListener(new HighScoresListener());
		b6.addActionListener(new ExitListener());
		
		spin1.addChangeListener(new verticalLengthListener());
		spin2.addChangeListener(new horizontalLengthListener());
		
		// set up the frame
		frame.setPreferredSize(new Dimension(800,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Battleship: main menu");
		
		// set up the panels, labels, spinners and buttons
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setBorder(BorderFactory.createEmptyBorder(75,50,75,50));
		l1.setFont(new Font("Monospaced", Font.BOLD, 50));
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		p1.add(l1);

		p2in.setLayout(new BoxLayout(p2in, BoxLayout.Y_AXIS));
		l2.setFont(new Font("Monospaced", Font.BOLD, 12));
		l3.setFont(new Font("Monospaced", Font.PLAIN, 12));
		l4.setFont(new Font("Monospaced", Font.PLAIN, 12));
		l2.setPreferredSize(new Dimension(160,29));
		p2in.add(l2);
		p2in.add(l3);
		p2in.add(spin1);
		p2in.add(l4);
		p2in.add(spin2);
		p2.add(p2in);
				
		p3in.setLayout(new BoxLayout(p3in, BoxLayout.Y_AXIS));
		b1.setFont(new Font("Monospaced", Font.PLAIN, 12));
		b2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		b1.setPreferredSize(new Dimension(200,29));
		b2.setPreferredSize(new Dimension(200,29));
		p3in.add(b1);
		p3in.add(new JLabel(" "));
		p3in.add(new JLabel(" "));
		p3in.add(new JLabel(" "));
		p3in.add(b2);
		p3.add(p3in);
		
		p4in.setLayout(new BoxLayout(p4in, BoxLayout.Y_AXIS));
		b3.setFont(new Font("Monospaced", Font.BOLD, 30));
		b3.setPreferredSize(new Dimension(250,75));
		p4in.add(new JLabel(" "));
		p4in.add(new JLabel(" "));
		p4in.add(b3);
		p4.add(p4in);
		
		b4.setFont(new Font("Monospaced", Font.PLAIN, 12));
		b5.setFont(new Font("Monospaced", Font.PLAIN, 12));
		b6.setFont(new Font("Monospaced", Font.PLAIN, 12));
		b4.setPreferredSize(new Dimension(250,29));
		b5.setPreferredSize(new Dimension(250,29));
		b6.setPreferredSize(new Dimension(250,29));
		p5.add(b4, BorderLayout.WEST);
		p5.add(b5, BorderLayout.CENTER);
		p5.add(b6, BorderLayout.EAST);
		
		// design the menu
		p1.setBackground(Color.yellow);
		p2.setBackground(Color.yellow);
		p2in.setBackground(Color.yellow);
		p3.setBackground(Color.yellow);
		p3in.setBackground(Color.yellow);
		p4.setBackground(Color.yellow);
		p4in.setBackground(Color.yellow);
		p5.setBackground(Color.yellow);
		
		b1.setForeground(Color.white);
		b1.setBackground(Color.BLACK);
		b1.setOpaque(true);
		b1.setBorderPainted(false);
		b2.setForeground(Color.white);
		b2.setBackground(Color.BLACK);
		b2.setOpaque(true);
		b2.setBorderPainted(false);
		b3.setForeground(Color.white);
		b3.setBackground(Color.BLACK);
		b3.setOpaque(true);
		b3.setBorderPainted(false);
		b4.setForeground(Color.white);
		b4.setBackground(Color.BLACK);
		b4.setOpaque(true);
		b4.setBorderPainted(false);
		b5.setForeground(Color.white);
		b5.setBackground(Color.BLACK);
		b5.setOpaque(true);
		b5.setBorderPainted(false);
		b6.setForeground(Color.white);
		b6.setBackground(Color.BLACK);
		b6.setOpaque(true);
		b6.setBorderPainted(false);
		
		// filling the frame
		frame.getContentPane().add(p1, BorderLayout.NORTH);
		frame.getContentPane().add(p2, BorderLayout.EAST);
		frame.getContentPane().add(p3, BorderLayout.WEST);
		frame.getContentPane().add(p4, BorderLayout.CENTER);
		frame.getContentPane().add(p5, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	private class StartGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new GameBoard(board, settings, playerOne, playerTwo);
				}
			});
		}
	}
	
	
	private class ShipPlacementListener implements ActionListener {
		JLabel shipLabel = new JLabel("How would you like your ships to be placed?");
		JButton txtButton = new JButton("Preset");
		JButton randButton = new JButton("Random");
		JFrame b1Frame = new JFrame("Ship Placement");
		JPanel b1Panel1 = new JPanel();
		JPanel b1Panel2 = new JPanel();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			txtButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					settings.setShipPlacement("Preset");
					b1Frame.dispose();	
				}
			});
			
			randButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					settings.setShipPlacement("Random");
					b1Frame.dispose();
				}
			});	
			
			b1Panel1.setLayout(new BoxLayout(b1Panel1, BoxLayout.Y_AXIS));
			shipLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
			txtButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
			randButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
			shipLabel.setBackground(Color.yellow);
			txtButton.setForeground(Color.white);
			txtButton.setBackground(Color.BLACK);
			txtButton.setOpaque(true);
			txtButton.setBorderPainted(false);
			randButton.setForeground(Color.white);
			randButton.setBackground(Color.BLACK);
			randButton.setOpaque(true);
			randButton.setBorderPainted(false);
			
			b1Panel1.setBackground(Color.yellow);
			b1Panel1.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			b1Panel2.setBackground(Color.yellow);
			b1Panel2.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			b1Panel1.add(shipLabel);
			b1Panel2.add(txtButton, BorderLayout.WEST);
			b1Panel2.add(new JLabel(" "));
			b1Panel2.add(randButton, BorderLayout.EAST);
			
			b1Frame.setPreferredSize(new Dimension(420,250));
			b1Frame.setBackground(Color.YELLOW);
			b1Frame.getContentPane().add(b1Panel1, BorderLayout.NORTH);
			b1Frame.getContentPane().add(b1Panel2, BorderLayout.SOUTH);
			b1Frame.pack();
			b1Frame.setVisible(true);
		}
	}

	
	private class RulesListener implements ActionListener {
		JFrame b4Frame = new JFrame("Rules");
		JPanel b4Panel = new JPanel();
		JLabel b4Label = new JLabel(
				"<html><b>Players</b><br>"
				+ "- 2<br>"
				+ " <br>"
				+ "<b>Goal</b><br>"
				+ "- Sink all ships<br>"
				+ " <br>"
				+ "<b>Setup</b><br>"
				+ "- Customizable gameboard<br>"
				+ "- 4 ships<br>"
				+ " <br>"
				+ "<b>Rules</b><br>"
				+ "- Take turns firing missiles<br>"
				+ "- Carrier (5 tiles): 8 points per hit<br>"
				+ "- Battleship (4 tiles): 20 points per hit<br>"
				+ "- Submarine (3 tiles): 25 points per hit<br>"
				+ "- Destroyer (2 tiles): 50 points per hit<br>"
				+ "- Sinking a ship earns double points<br>"
				+ "- Play until all ships have sunk<br>"
				+ "- Player with most points wins"
				);
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			b4Label.setFont(new Font("Monospaced", Font.PLAIN, 12));
			b4Panel.setBackground(Color.yellow);
			b4Panel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			b4Panel.add(b4Label);
			b4Frame.getContentPane().add(b4Panel);
			b4Frame.pack();
			b4Frame.setVisible(true);
		}
	}
	
	
	private class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			System.exit(1);
		}
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
	
	private class ScoringSystemListener implements ActionListener{
		private JLabel scoringLabel = new JLabel("							Please select a scoring system.");
		private JButton equalButton = new JButton("Equal Scores");
		private JButton firstPlayerButton = new JButton("First Player Advantage");
		private JPanel b2Panel1 = new JPanel();
		private JPanel b2Panel2 = new JPanel();

		@Override
		public void actionPerformed(ActionEvent e) {
			equalButton.addActionListener(new SelectScoringSystemListener());
			firstPlayerButton.addActionListener(new SelectScoringSystemListener());

			b2Panel1.setLayout(new BoxLayout(b2Panel1, BoxLayout.Y_AXIS));
			scoringLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
			equalButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
			firstPlayerButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
			scoringLabel.setBackground(Color.yellow);
			equalButton.setForeground(Color.white);
			equalButton.setBackground(Color.BLACK);
			equalButton.setOpaque(true);
			equalButton.setBorderPainted(false);
			equalButton.setPreferredSize(new Dimension(200,29));
			firstPlayerButton.setForeground(Color.white);
			firstPlayerButton.setBackground(Color.BLACK);
			firstPlayerButton.setOpaque(true);
			firstPlayerButton.setBorderPainted(false);
			firstPlayerButton.setPreferredSize(new Dimension(200,29));
			
			b2Panel1.setBackground(Color.yellow);
			b2Panel1.setBorder(BorderFactory.createEmptyBorder(50,150,50,50));
			b2Panel2.setBackground(Color.yellow);
			b2Panel2.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			b2Panel1.add(scoringLabel);
			b2Panel2.add(equalButton, BorderLayout.WEST);
			b2Panel2.add(new JLabel(" "));
			b2Panel2.add(firstPlayerButton, BorderLayout.EAST);
			
			b2Frame.setPreferredSize(new Dimension(600,250));
			b2Frame.setBackground(Color.YELLOW);
			b2Frame.getContentPane().add(b2Panel1, BorderLayout.NORTH);
			b2Frame.getContentPane().add(b2Panel2, BorderLayout.SOUTH);
			b2Frame.pack();
			b2Frame.setVisible(true);

		}
	}

	private class SelectScoringSystemListener implements ActionListener{		
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = ((JButton) e.getSource()).getText();

			if (action.equals("Equal Scores")) {
				settings.setScoringSystem("Equal");
			} else{
				settings.setScoringSystem("notEqual");
			}

			b2Frame.dispose();

		}
	}

	private class verticalLengthListener implements ChangeListener{
		private int verticalSize;
		@Override
		public void stateChanged(ChangeEvent e) {
			verticalSize = Integer.parseInt(spin1.getValue().toString());
			board.setVerticalSize(verticalSize);
		}
	}

	private class horizontalLengthListener implements ChangeListener{
		private int horizontalSize;
		@Override
		public void stateChanged(ChangeEvent e) {
			horizontalSize = Integer.parseInt(spin2.getValue().toString());
			board.setHorizontalSize(horizontalSize);
		}	
	}

}



