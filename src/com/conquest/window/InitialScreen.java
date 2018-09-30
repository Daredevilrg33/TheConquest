/**
 * 
 */
package com.conquest.window;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.conquest.mapeditor.MapDashboard;
import com.conquest.utilities.Constants;

/**
 * @author Rohit Gupta
 *
 */
public class InitialScreen extends Frame implements ActionListener{

	private Button btnNewGame;
	private Button btnMapEditor;
	private Button btnAboutUs;
	private Button btnExit;
	
	public InitialScreen() {
		btnNewGame  = new Button("New Game");
		btnNewGame.setBounds(Constants.WIDTH/2-50, 50, 100, 30);
		btnNewGame.addActionListener(this);
		add(btnNewGame);
		
		btnMapEditor  = new Button("Map Editor");
		btnMapEditor.setBounds(Constants.WIDTH/2-50, 120, 100, 30);
		btnMapEditor.addActionListener(this);
		add(btnMapEditor);
		
		btnAboutUs  = new Button("About Us");
		btnAboutUs.setBounds(Constants.WIDTH/2-50, 190, 100, 30);
		btnAboutUs.addActionListener(this);
		add(btnAboutUs);
		
		btnExit  = new Button("Exit");
		btnExit.setBounds(Constants.WIDTH/2-50,260, 100, 30);
		btnExit.addActionListener(this);
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
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == btnNewGame)
		{
			SecondScreen secondScreen = new SecondScreen();
			secondScreen.setVisible(true);
			dispose();
		}else if(event.getSource() == btnMapEditor)
		{
			MapDashboard mapDashboard = new MapDashboard();
			mapDashboard.setVisible(true);
			dispose();
		}else if(event.getSource() == btnAboutUs) {
			dispose();
		}else {
			System.exit(0);
		}
		
				
	}
	
	
}
