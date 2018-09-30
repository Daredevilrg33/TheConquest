/**
 * 
 */
package com.conquest.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.conquest.utilities.Constants;

/**
 * @author Rohit Gupta
 *
 */
public class SecondScreen extends JFrame {
	
	
	public SecondScreen()
	{
		
		JLabel labelSelectMap = new JLabel();
		labelSelectMap.setText("Select Map");
		labelSelectMap.setBounds(Constants.WIDTH/2-100, 50, 100, 30);
		add(labelSelectMap);
		
		setResizable(false);
		setSize(Constants.WIDTH,Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
			dispose();
			InitialScreen initialScreen = new InitialScreen();
			initialScreen.setVisible(true);
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	

}
