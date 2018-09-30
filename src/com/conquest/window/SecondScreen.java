/**
 * 
 */
package com.conquest.window;

import java.awt.Frame;
import java.awt.TextField;

import com.conquest.utilities.Constants;

/**
 * @author Rohit Gupta
 *
 */
public class SecondScreen extends Frame {
	
	
	public SecondScreen()
	{
		
		TextField textField = new TextField();
		textField.setText("Select Map");
		textField.setBounds(Constants.WIDTH/2-100, 50, 100, 30);
		setResizable(false);
		setSize(Constants.WIDTH,Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		
	}
	
	

}
