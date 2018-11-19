package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.conquest.mapeditor.view.MapDashboard;
import com.conquest.utilities.Constants;
import org.apache.log4j.*;

/**
 * The Class MainMenuScreen.
 *
 * @author Rohit Gupta
 */
public class MainMenuScreen extends JFrame implements ActionListener {

	/** The button new game. */
	private JButton btnNewSingleGame;
	
	/** The btn load game. */
	private JButton btnLoadGame;

	/** The button map editor. */
	private JButton btnMapEditor;

	/** The button help describing rules and javaDoc. */
	private JButton btnNewTournament;

	/** The button exit. */
	private JButton btnExit;
	
	private static final Logger log = Logger.getLogger(MainMenuScreen.class);

	/**
	 * Instantiates a new main menu screen.
	 */
	public MainMenuScreen() {
		btnNewSingleGame = new JButton("New Single Game");
		btnNewSingleGame.setBounds(Constants.WIDTH / 2 - 50, 50, 150, 30);
		btnNewSingleGame.addActionListener(this);
		add(btnNewSingleGame);

		btnNewTournament = new JButton("New Tournament");
		btnNewTournament.setBounds(Constants.WIDTH / 2 - 50, 120, 150, 30);
		btnNewTournament.addActionListener(this);
		add(btnNewTournament);

		btnMapEditor = new JButton("Map Editor");
		btnMapEditor.setBounds(Constants.WIDTH / 2 - 50, 190, 150, 30);
		btnMapEditor.addActionListener(this);
		add(btnMapEditor);

		btnLoadGame = new JButton("Load Game");
		btnLoadGame.setBounds(Constants.WIDTH / 2 - 50, 260, 150, 30);
		btnLoadGame.addActionListener(this);
		add(btnLoadGame);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(Constants.WIDTH / 2 - 50, 330, 150, 30);
		btnExit.addActionListener(this);
		add(btnExit);
		setTitle("Main Menu");
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
				System.exit(0);
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

		if (event.getSource() == btnNewSingleGame) {
			log.info("New Single Game started");
			NewGameMenuScreen secondScreen = new NewGameMenuScreen();
			secondScreen.setVisible(true);
			dispose();
		} else if (event.getSource() == btnMapEditor) {
			log.info("Map Editor launched");
			MapDashboard mapDashboard = new MapDashboard();
			mapDashboard.setVisible(true);
			dispose();
		} else if (event.getSource() == btnNewTournament) {
			log.info("New Tournament started");
			NewTournamentMenuScreen newTournamentScreen = new NewTournamentMenuScreen();
			newTournamentScreen.setVisible(true);
			dispose();
		}else if (event.getSource() == btnLoadGame) {
			log.info("Game loaded");
			LoadGameWindow loadGameWindow = new LoadGameWindow();
//				loadGameWindow.setVisible(true);
			dispose();
		}else {
			System.exit(0);
		}

	}
}
