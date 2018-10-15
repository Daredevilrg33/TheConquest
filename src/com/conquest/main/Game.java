/**
 * 
 */
package com.conquest.main;

import java.awt.Canvas;

import com.conquest.window.GameWindow;
import com.conquest.window.MainMenuScreen;

/**
 * @author Rohit Gupta
 *
 */
public class Game  {
	private static final long serialVersionUID = -4669025547633011027L;
	
	public Game() {
		
	}
	
	public static void main(String[] args) {

		MainMenuScreen initialScreen = new MainMenuScreen();
		initialScreen.setVisible(true);

	}



}
