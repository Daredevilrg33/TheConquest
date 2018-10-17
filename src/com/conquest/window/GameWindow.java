package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.conquest.controller.GameWindowController;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

/**
 * The Class GameWindow.
 *
 * @author Rohit Gupta
 */
public class GameWindow extends JFrame implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 705136013521611381L;
	
	/** The game window controller. */
	private GameWindowController gameWindowController;
	
	/** The j player label. */
	private JLabel jPlayerLabel;
	
	/** The j player armies. */
	private JLabel jPlayerArmies;
	
	/** The j combo box countries. */
	private JComboBox<String> jComboBoxCountries;
	
	/** The j button place. */
	private JButton jButtonPlace;
	
	/** The selected country. */
	private String selectedCountry;
	
	/**
	 * GameWindow Parameterized Constructor 
	 * Instantiates a new game window.
	 * @param mapModel the map model
	 * @param noOfPlayers the no of players
	 */
	public GameWindow(MapHierarchyModel mapModel, String noOfPlayers ) {
		
		setTitle("Game Window");
		setResizable(false);
		setSize(Constants.MAP_EDITOR_WIDTH,Constants.MAP_EDITOR_HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		jPlayerLabel = new JLabel();
		jPlayerLabel.setBounds(50, 50, 100, 30);
		add(jPlayerLabel);

		jComboBoxCountries = new JComboBox<>();
		jComboBoxCountries.setBounds(170, 50, 100, 30);
		add(jComboBoxCountries);
		jButtonPlace = new JButton("Place");
		jButtonPlace.setBounds(290,50,100,30);
		jButtonPlace.addActionListener(this);
		add(jButtonPlace);
		
		jPlayerArmies = new JLabel();
		jPlayerArmies.setBounds(410, 50, 200, 30);
		add(jPlayerArmies);
		
		gameWindowController = new GameWindowController(this,Integer.parseInt(noOfPlayers),mapModel);
		
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
				MainMenuScreen initialScreen = new MainMenuScreen();
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
	
	/**
	 * updatePlayerLabel Method
	 * Update player label.
	 * @param playerName the player name
	 */
	public void updatePlayerLabel (String playerName)
	{
//		System.out.println("updatePlayerLabel Value: "+  playerName);
		jPlayerLabel.setText(playerName);
	}
	
	/**
	 * updatePlayerArmies Method
	 * Update player armies.
	 * @param RemainingArmies the remaining armies
	 */
	public void updatePlayerArmies (int RemainingArmies)
	{
//		System.out.println("updatePlayerLabel Value: "+  playerName);
		jPlayerArmies.setText("Remaining Armies with player: " +RemainingArmies);
	}
	
	/**
	 * updateComboBoxCountries Method
	 * Update combo box countries.
	 * @param countryModels the country models
	 */
	public void updateComboBoxCountries(List<CountryModel> countryModels)
	{
		jComboBoxCountries.removeAllItems();
		for(CountryModel countryModel: countryModels)
		{
//			System.out.println("updateComboBoxCountries Value: "+  countryModel.getCountryName());
			jComboBoxCountries.addItem(countryModel.getCountryName());
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Place":
			System.out.println("Selected Player Name: " +jPlayerLabel.getText().toString());
			System.out.println("Selected Country Name: " + jComboBoxCountries.getSelectedItem().toString());
			
			selectedCountry = jComboBoxCountries.getSelectedItem().toString();
			gameWindowController.checking(selectedCountry);
			
			gameWindowController.updateUIInfo();
			
			break;
		default:
			break;
		}
	}
//	
//	public String transferCountry() {
//		return selectedCountry;
//	}
}
