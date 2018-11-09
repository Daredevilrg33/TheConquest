package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.conquest.controller.FortificationWindowController;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.model.GameModel;
import com.conquest.utilities.Constants;

/**
 * The Class FortificationWindow.
 */
public class FortificationWindow extends JFrame implements ActionListener {

	/** The reinforcement window controller. */
	private FortificationWindowController reinforcementWindowController;

	/** The j combo box source countries. */
	private JComboBox<String> jComboBoxSourceCountries;

	/** The j combo box destination countries. */
	private JComboBox<String> jComboBoxDestinationCountries;

	/** The j combo box chosen no of armies. */
	private JComboBox<String> jComboBoxChosenNoOfArmies;

	/** The j button send. */
	private JButton jButtonSend;

	/** The j button finish. */
	private JButton jButtonFinish;

	/** The selected source country. */
	private String selectedSourceCountry;

	/** The armies. */
	private int armies;

	/** The selected destination country. */
	private String selectedDestinationCountry;

	/** The j source army label. */
	private JLabel jSourceArmyLabel;

	/** The j destination army label. */
	private JLabel jDestinationArmyLabel;

	/** The j player label. */
	private JLabel jPlayerLabel;

	/** The j source country label. */
	private JLabel jSourceCountryLabel;

	/** The j destination country label. */
	private JLabel jDestinationCountryLabel;

	/** The j chosen no of armies. */
	private JLabel jChosenNoOfArmies;

	/** The players. */
	private PlayerModel player;

	private GameModel gameModel;

	/**
	 * Instantiates a new fortification window.
	 *
	 * @param mapModel the map model
	 * @param player   the player
	 */
	public FortificationWindow(GameModel gameModel, PlayerModel player) {
		this.player = player;
		this.gameModel = gameModel;

		setTitle("Fortification Window");
		setResizable(false);
		setSize(Constants.MAP_EDITOR_WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		jPlayerLabel = new JLabel();
		jPlayerLabel.setBounds(50, 50, 100, 30);
		add(jPlayerLabel);

		jComboBoxSourceCountries = new JComboBox<>();
		jComboBoxSourceCountries.setBounds(170, 50, 100, 30);

		add(jComboBoxSourceCountries);

		jComboBoxDestinationCountries = new JComboBox<>();
		jComboBoxDestinationCountries.setBounds(300, 50, 100, 30);
		add(jComboBoxDestinationCountries);

		jButtonSend = new JButton("Send");
		jButtonSend.setBounds(600, 50, 100, 30);
		jButtonSend.addActionListener(this);
		add(jButtonSend);

		jButtonFinish = new JButton("Finish");
		jButtonFinish.setBounds(750, 50, 100, 30);
		jButtonFinish.addActionListener(this);
		add(jButtonFinish);

		jComboBoxChosenNoOfArmies = new JComboBox<>();
		jComboBoxChosenNoOfArmies.setBounds(450, 50, 100, 30);
		add(jComboBoxChosenNoOfArmies);

		jChosenNoOfArmies = new JLabel();
		jChosenNoOfArmies.setBounds(450, 20, 200, 30);
		jChosenNoOfArmies.setText("Select no. of Armies");
		add(jChosenNoOfArmies);

		jSourceCountryLabel = new JLabel();
		jSourceCountryLabel.setBounds(170, 20, 200, 30);
		jSourceCountryLabel.setText("Source Country");
		add(jSourceCountryLabel);

		jDestinationCountryLabel = new JLabel();
		jDestinationCountryLabel.setBounds(300, 20, 200, 30);
		jDestinationCountryLabel.setText("Destination Country");
		add(jDestinationCountryLabel);

		jSourceArmyLabel = new JLabel();
		jSourceArmyLabel.setBounds(217, 78, 200, 30);
		add(jSourceArmyLabel);

		jDestinationArmyLabel = new JLabel();
		jDestinationArmyLabel.setBounds(337, 78, 200, 30);
		add(jDestinationArmyLabel);

		reinforcementWindowController = new FortificationWindowController(player, this);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();

			}

		});
		jComboBoxSourceCountries.addActionListener(this);
		jComboBoxDestinationCountries.addActionListener(this);

	}

	/**
	 * Update army.
	 *
	 * @param number the number
	 */
	public void updateArmy(int number) {

		jComboBoxChosenNoOfArmies.removeAllItems();
		for (int i = 1; i <= number; i++) {
			jComboBoxChosenNoOfArmies.addItem(Integer.toString(i));
		}
	}

	/**
	 * Update player label.
	 *
	 * @param playerName the player name
	 */
	public void updatePlayerLabel(String playerName) {
		jPlayerLabel.setText(playerName);
	}

	/**
	 * Update source army label.
	 *
	 * @param sourceArmy the source army
	 */
	public void updateSourceArmyLabel(int sourceArmy) {
		jSourceArmyLabel.setText(Integer.toString(sourceArmy));

	}

	/**
	 * New army label.
	 */
	public void newArmyLabel() {
		jSourceArmyLabel.setText("");
		jDestinationArmyLabel.setText("");
	}

	/**
	 * Update destination army label.
	 *
	 * @param destinationArmy the destination army
	 */
	public void updateDestinationArmyLabel(int destinationArmy) {
		jDestinationArmyLabel.setText(Integer.toString(destinationArmy));

	}

	/**
	 * Update combo box source countries.
	 *
	 * @param countryModels the country models
	 */
	public void updateComboBoxSourceCountries(List<CountryModel> countryModels) {

		jComboBoxSourceCountries.removeAllItems();
		jComboBoxSourceCountries.addItem("Select country:");
		for (CountryModel countryModel : countryModels) {
			jComboBoxSourceCountries.addItem(countryModel.getCountryName());
		}

	}

	/**
	 * Update combo box destination countries.
	 *
	 * @param arrayList the array list
	 */
	public void updateComboBoxDestinationCountries(ArrayList<String> arrayList) {

		jComboBoxDestinationCountries.removeAllItems();
		jComboBoxDestinationCountries.addItem("Select country:");

		for (int i = arrayList.size() - 1; i >= 0; i--) {
			jComboBoxDestinationCountries.addItem(arrayList.get(i));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jButtonFinish) {
//			reinforcementWindowController.updateBackend();
			gameModel.increaseTurn();
			gameModel.moveToNextPlayer();
			dispose();

		} else if (e.getSource() == jButtonSend) {
			System.out.println("Selected Player Name: " + jPlayerLabel.getText().toString());
			System.out
					.println("Selected Source Country Name: " + jComboBoxSourceCountries.getSelectedItem().toString());

			if (jComboBoxSourceCountries.getSelectedItem().toString().equalsIgnoreCase("Select Country:")) {

				JOptionPane.showMessageDialog(this, "Select Source Country to continue.", "Error Message",
						JOptionPane.ERROR_MESSAGE);

			} else if (reinforcementWindowController.checking()) {
				JOptionPane.showMessageDialog(this, "Low number of Armies in Source Country\n Unable to send",
						"Error Message", JOptionPane.ERROR_MESSAGE);
			} else if (jComboBoxDestinationCountries.getSelectedItem().toString().equalsIgnoreCase("Select Country:")) {
				JOptionPane.showMessageDialog(this, "Select Destination Country to continue.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			} else {

				armies = Integer.valueOf(jComboBoxChosenNoOfArmies.getSelectedItem().toString());
				reinforcementWindowController.sending(armies);
				reinforcementWindowController.updateUIInfo();

				reinforcementWindowController.updateBackend();
				gameModel.increaseTurn();
				gameModel.moveToNextPlayer();
				dispose();
			}

		} else if (e.getSource() == jComboBoxSourceCountries) {
			System.out.println("JCombo Source COuntry selected");
			if (jComboBoxSourceCountries.getItemCount() != 0) {
				selectedSourceCountry = jComboBoxSourceCountries.getSelectedItem().toString();

				System.out.println(" Current Item Selected " + jComboBoxSourceCountries.getSelectedItem()
						+ jComboBoxSourceCountries.getSelectedIndex());
				if (jComboBoxSourceCountries.getSelectedIndex() > 0) {
					reinforcementWindowController.finding(selectedSourceCountry);
					reinforcementWindowController.updateUIInfo();
				}
				updateDestinationArmyLabel(0);
			}
		} else if (e.getSource() == jComboBoxDestinationCountries) {
			if (jComboBoxDestinationCountries.getSelectedIndex() == 0) {
				updateDestinationArmyLabel(0);
			}
			if (jComboBoxDestinationCountries.getItemCount() != 0) {
				selectedDestinationCountry = jComboBoxDestinationCountries.getSelectedItem().toString();
			} else {
				selectedDestinationCountry = "";
			}
			reinforcementWindowController.updateDestinationUI(selectedDestinationCountry);

		}

	}

}