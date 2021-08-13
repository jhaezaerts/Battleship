package main;

import javax.swing.SwingUtilities;

import gameGUI.MainMenu;
import gameLogic.Settings;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			Settings settings = new Settings();
			public void run() {
				new MainMenu(settings);
			}
		});
	}

}
