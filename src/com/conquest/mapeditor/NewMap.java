package com.conquest.mapeditor;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.conquest.utilities.Constants;

public class NewMap extends JFrame {

	public NewMap() {

		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.
			 * WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

	}
}
