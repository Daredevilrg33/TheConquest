/**
 * 
 */
package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.conquest.mapeditor.MapDashboard;
import com.conquest.utilities.Constants;

/**
 * @author Rohit Gupta
 *
 */
public class InitialScreen extends JFrame implements ActionListener{

	private JButton btnNewGame;
	private JButton btnMapEditor;
	private JButton btnAboutUs;
	private JButton btnExit;
	
	public InitialScreen() {
		btnNewGame  = new JButton("New Game");
		btnNewGame.setBounds(Constants.WIDTH/2-50, 50, 100, 30);
		btnNewGame.addActionListener(this);
		add(btnNewGame);
		
		btnMapEditor  = new JButton("Map Editor");
		btnMapEditor.setBounds(Constants.WIDTH/2-50, 120, 100, 30);
		btnMapEditor.addActionListener(this);
		add(btnMapEditor);
		
		btnAboutUs  = new JButton("About Us");
		btnAboutUs.setBounds(Constants.WIDTH/2-50, 190, 100, 30);
		btnAboutUs.addActionListener(this);
		add(btnAboutUs);
		
		btnExit  = new JButton("Exit");
		btnExit.setBounds(Constants.WIDTH/2-50,260, 100, 30);
		btnExit.addActionListener(this);
		add(btnExit);
		
		setResizable(false);
		setSize(Constants.WIDTH,Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
		/* (non-Javadoc)
		 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		});
		
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
