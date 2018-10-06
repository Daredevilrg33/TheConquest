/**
 * 
 */
package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

/**
 * @author Rohit Gupta
 *
 */
public class SecondScreen extends JFrame implements ActionListener {

	private JButton buttonSelectFile;
	private String filePath;
	private String fileName;
	private JTextField textFieldMap;
	private String[] playersList = new String[] { "3", "4", "5" };
	private JButton buttonStartGame;
	private JComboBox<String> comboBoxSelectPlayer;

	public SecondScreen() {

		JLabel labelSelectMap = new JLabel();
		labelSelectMap.setText("Select Map");
		labelSelectMap.setBounds(Constants.WIDTH / 2 - 150, 50, 100, 30);
		add(labelSelectMap);

		textFieldMap = new JTextField();
		textFieldMap.setBounds(Constants.WIDTH / 2 - 30, 50, 100, 30);
		add(textFieldMap);

		buttonSelectFile = new JButton();
		buttonSelectFile.setText("Select File");
		buttonSelectFile.setBounds(Constants.WIDTH / 2 + 90, 50, 100, 30);
		buttonSelectFile.addActionListener(this);
		add(buttonSelectFile);

		JLabel labelSelectPlayer = new JLabel();
		labelSelectPlayer.setText("Select Players");
		labelSelectPlayer.setBounds(Constants.WIDTH / 2 - 150, 100, 100, 30);
		add(labelSelectPlayer);

		comboBoxSelectPlayer = new JComboBox<>(playersList);
		comboBoxSelectPlayer.setBounds(Constants.WIDTH / 2 - 30, 100, 100, 30);
		add(comboBoxSelectPlayer);

		buttonStartGame = new JButton("Start Game");
		buttonStartGame.setBounds(Constants.WIDTH / 2 - 50, Constants.HEIGHT - 200, 100, 30);
		buttonStartGame.addActionListener(this);
		add(buttonStartGame);
		setTitle("New Game Menu");
		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == buttonSelectFile) {
			filePath = Utility.pickFile();
			System.out.println("File Path: " + filePath);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePath.split(pattern);
			fileName = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileName);
			textFieldMap.setText(fileName);
		} else if (event.getSource() == buttonStartGame) {
			String noOfPlayers = (String) comboBoxSelectPlayer.getSelectedItem();
			if(fileName == null || filePath==null || fileName.isEmpty() || filePath.isEmpty()) {
				JOptionPane.showMessageDialog(this,
					    "Select Map to continue.",
					    "Error Message",
					    JOptionPane.ERROR_MESSAGE);
			}else
			{
				dispose();
				GameWindow gameWindow = new GameWindow();
				gameWindow.setVisible(true);
			}
		}
	}
}
