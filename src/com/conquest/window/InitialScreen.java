/**
 * 
 */
package com.conquest.window;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.conquest.utilities.Constants;

/**
 * @author Rohit Gupta
 *
 */
public class InitialScreen extends Frame implements ActionListener{

	public InitialScreen() {
		Button btnNewGame  = new Button("New Game");
		btnNewGame.setBounds(Constants.WIDTH/2-50, 50, 100, 50);
		add(btnNewGame);
		btnNewGame.addActionListener(this);
		Button btnMapEditor  = new Button("Map Editor");
		btnMapEditor.setBounds(Constants.WIDTH/2-50, 120, 100, 50);
		add(btnMapEditor);
		Button btnAboutUs  = new Button("About Us");
		btnAboutUs.setBounds(Constants.WIDTH/2-50, 190, 100, 50);
		add(btnAboutUs);
		Button btnExit  = new Button("Exit");
		btnExit.setBounds(Constants.WIDTH/2-50,260, 100, 50);
		add(btnExit);
		
		setResizable(false);
		setSize(Constants.WIDTH,Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		dispose();
	}
	
	
}
