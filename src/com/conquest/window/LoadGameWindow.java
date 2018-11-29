package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.conquest.model.GameModel;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

/**
 * The Class LoadGameWindow.
 */
public class LoadGameWindow extends JFrame implements ActionListener {

	/** The Label select game. */
	JLabel labelSelectGame;

	/** The button Select game file. */
	private JButton buttonSelectGame;

	/**
	 * Instantiates a new load game window.
	 */
	public LoadGameWindow() {

		labelSelectGame = new JLabel();
		labelSelectGame.setText("Select Game");
		labelSelectGame.setBounds(Constants.WIDTH / 2 - 150, 50, 100, 30);
		add(labelSelectGame);

		buttonSelectGame = new JButton();
		buttonSelectGame.setText("Select Game File");
		buttonSelectGame.setBounds(Constants.WIDTH / 2 + 50, 50, 150, 30);
		buttonSelectGame.addActionListener(this);
		add(buttonSelectGame);

		setTitle("Load Game Menu");
		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				MainMenuScreen mainMenuScreen = new MainMenuScreen();
				mainMenuScreen.setVisible(true);
				dispose();
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
		if (event.getSource() == buttonSelectGame) {
			loadGame();
		}
	}

	/**
	 * Handler used to load game.
	 */
	private void loadGame() {
		String inputFileName;
		JFileChooser chooser;
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("./save"));
		chooser.setDialogTitle("Choose saved game file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith(".bin") || f.isDirectory())
					return true;
				else
					return false;
			}

			public String getDescription() {
				return "Saved game files(*.bin)";
			}
		});
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			inputFileName = chooser.getSelectedFile().getAbsolutePath();
			if (inputFileName.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "File name invalid");
			} else {
				loadGame(inputFileName.trim());
			}
		}
	}

	/**
	 * Method to load game from disk.
	 *
	 * @param inputFile The file that need to be loaded
	 */
	public void loadGame(String inputFile) {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream(inputFile));
			GameModel myGameModel = (GameModel) input.readObject();
			dispose();
			GameWindow gameWindow = new GameWindow(myGameModel);
			gameWindow.setVisible(true);
			if (myGameModel.getGamePhaseStage() == 2) {
				AttackPhaseWindow attackPhaseWindow = new AttackPhaseWindow(myGameModel);
				attackPhaseWindow.setVisible(true);
			}
			if (myGameModel.getGamePhaseStage() == 3) {
				FortificationWindow fortificationWindow = new FortificationWindow(myGameModel,
						myGameModel.getCurrPlayer());
				fortificationWindow.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
