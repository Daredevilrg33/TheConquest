package com.conquest.window;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.conquest.main.Game;

/**
 * @author Rohit Gupta
 *
 */
public class GameWindow extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 705136013521611381L;


	public GameWindow(int width, int height, String title,Game game) {
		
		
		JFrame jFrame = new JFrame(title);
		jFrame.setPreferredSize(new Dimension(width, height));
		jFrame.setMinimumSize(new Dimension(width, height));
		jFrame.setMaximumSize(new Dimension(width, height));
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.add(game);
		jFrame.setVisible(true);
		game.start();
		
		
		
	}
}
