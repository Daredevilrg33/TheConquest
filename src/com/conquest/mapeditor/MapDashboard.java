/**
 * 
 */
package com.conquest.mapeditor;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.conquest.utilities.Constants;
import com.conquest.window.SecondScreen;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Nancy Goyal
 *
 */
public class MapDashboard extends JFrame implements ActionListener{
	private JButton btnNewMap;
	private JButton btnLoadMap;
	
	public MapDashboard() {
		
		
		setResizable(false);
		setSize(Constants.WIDTH,Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		btnNewMap  = new JButton("New Map");
		btnNewMap.setBounds(Constants.WIDTH/2-50, 50, 100, 30);
		btnNewMap.addActionListener(this);
		add(btnNewMap);
		
		btnLoadMap  = new JButton("Load Map");
		btnLoadMap.setBounds(Constants.WIDTH/2-50, 120, 100, 30);
		btnLoadMap.addActionListener(this);
		add(btnLoadMap);
		
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
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == btnNewMap)
		{
			NewMap newMap = new NewMap();
			newMap.setVisible(true);
			dispose();
		}else if(event.getSource() == btnLoadMap)
		{
			MapDashboard mapDashboard = new MapDashboard();
			mapDashboard.setVisible(true);
			dispose();
		}else {
			System.exit(0);
		}
		
				
	}

}
