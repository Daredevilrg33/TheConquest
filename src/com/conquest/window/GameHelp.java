package com.conquest.window;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.conquest.utilities.Constants;

/**
 * The Class GameHelp.
 * @author Nancy Goyal
 * @version 1.0.0
 */
public class GameHelp extends JFrame {

	/**
	 * Instantiates a new game help window.
	 */
	public GameHelp() {

		setTitle("Help Window");
		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		JLabel label = new JLabel("Coming Soon");
		label.setFont(new java.awt.Font("dialog", 1, 15));
		label.setBounds(Constants.WIDTH / 2 - 50, Constants.HEIGHT / 2, 100, 30);
		add(label);
	}
}
