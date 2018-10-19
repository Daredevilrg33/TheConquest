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

import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

/**
 * The Class NewGameMenuScreen.
 *
 * @author Rohit Gupta
 */
public class NewGameMenuScreen extends JFrame implements ActionListener {

	/** The button select file. */
	private JButton buttonSelectFile;

	/** The file path. */
	private String filePath;

	/** The file name. */
	private String fileName;

	/** The text field map. */
	private JTextField textFieldMap;

	/** The players list. */
	private String[] playersList = new String[] { "3", "4", "5" };

	/** The button start game. */
	private JButton buttonStartGame;

	/** The combo box select player. */
	private JComboBox<String> comboBoxSelectPlayer;

	/** The no of players. */
	private String noOfPlayers;

	/**
	 * NewGameMenuScreen Constructor Instantiates a new new game menu screen.
	 */
	public NewGameMenuScreen() {

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
				

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
				MainMenuScreen initialScreen = new MainMenuScreen();
				initialScreen.setVisible(true);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				
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
		if (event.getSource() == buttonSelectFile) {
			filePath = Utility.pickFile();
			System.out.println("File Path: " + filePath);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePath.split(pattern);
			fileName = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileName);
			textFieldMap.setText(fileName);
		} else if (event.getSource() == buttonStartGame) {
			noOfPlayers = (String) comboBoxSelectPlayer.getSelectedItem();
			if (fileName == null || filePath == null || fileName.isEmpty() || filePath.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			} else {

				Utility utility = new Utility();
				MapHierarchyModel mapModel = utility.parseAndValidateMap(filePath);
				mapModel.setConquestMapName(fileName);
				if (!mapModel.isValErrorFlag()) {
					dispose();
					GameWindow gameWindow = new GameWindow(mapModel, noOfPlayers);
					gameWindow.setVisible(true);
				} else {
					String valErrorMsg = mapModel.getErrorMsg();
					JOptionPane.showMessageDialog(this, valErrorMsg, "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
