/**
 * 
 */
package com.conquest.main;

import java.awt.Canvas;

import com.conquest.window.GameWindow;
import com.conquest.window.MainMenuScreen;

/**
 * Game class Main class of project.
 *
 * @author Rohit Gupta
 */
public class Game {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = -4669025547633011027L;

	/**
	 * Game class Constructor.
	 */
	public Game() {

	}

	/**
	 * Main method of Game Object of MainMenuScreen is created initialScreen is Set
	 * visible true {@code 	initialScreen.setVisible(true); }
	 * 
	 * @param args argument of main function
	 */
	public static void main(String[] args) {
		MainMenuScreen initialScreen = new MainMenuScreen();
		initialScreen.setVisible(true);
	}
}
