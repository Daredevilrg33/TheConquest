/**
 * 
 */
package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.conquest.controller.AttackWindowController;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.model.GameModel;
import com.conquest.utilities.Constants;

/**
 * The Class AttackPhaseWindow.
 *
 * @author Rohit Gupta
 */
public class AttackPhaseWindow extends JFrame implements ActionListener {

	/** The attack window controller. */
	private AttackWindowController attackWindowController;

	/** The j source army label. */
	private JLabel jSourceArmyLabel;

	/** The j target army label. */
	private JLabel jTargetArmyLabel;

	/** The j combo box source countries. */
	private JComboBox<String> jComboBoxSourceCountries;

	/** The j combo box target countries. */
	private JComboBox<String> jComboBoxTargetCountries;

	/** The j combo box no of dice. */
	private JComboBox<String> jComboBoxNoOfDice;

	/** The j button attack. */
	private JButton jButtonAttack;

	/** The j player label. */
	private JLabel jPlayerLabel;

	/** The j button all out attack. */
	private JButton jButtonAllOutAttack;

	/** The risk map model. */
	private GameModel gameModel;

	/** The player. */
	private PlayerModel[] players;

	/** The dice image. */
	private JLabel diceImage;

	/** The attack dice 1. */
	private JLabel attackDice1;

	/** The attack dice 2. */
	private JLabel attackDice2;

	/** The attack dice 3. */
	private JLabel attackDice3;

	/** The defend dice 1. */
	private JLabel defendDice1;

	/** The defend dice 2. */
	private JLabel defendDice2;

	/** The dice results attacking. */
	private ArrayList<Integer> diceResultsAttacking = new ArrayList<>();

	/** The dice results defending. */
	private ArrayList<Integer> diceResultsDefending = new ArrayList<>();

	/** The current player. */
	private PlayerModel currentPlayer;

	/** The j button finish attack. */
	private JButton jButtonFinishAttack;

	/**
	 * Instantiates a new attack phase window.
	 *
	 * @param gameModel     the risk map model
	 * @param playerModels  the player models
	 * @param currentPlayer the current player
	 */
	public AttackPhaseWindow(GameModel gameModel, PlayerModel[] playerModels, PlayerModel currentPlayer) {
		gameModel.setGameStatus("Attack Phase starts");
		gameModel.setGameSavePhase(2);
		this.gameModel = gameModel;
		this.players = playerModels;
		this.currentPlayer = currentPlayer;
		setTitle("Attack Phase");
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
		jComboBoxTargetCountries = new JComboBox<>();
		jComboBoxTargetCountries.setBounds(300, 50, 100, 30);
		add(jComboBoxTargetCountries);

		jComboBoxNoOfDice = new JComboBox<>();
		jComboBoxNoOfDice.setBounds(430, 50, 100, 30);
		add(jComboBoxNoOfDice);

		jButtonAttack = new JButton("Attack");
		jButtonAttack.setBounds(540, 50, 80, 30);
		jButtonAttack.addActionListener(this);
		add(jButtonAttack);

		jButtonAllOutAttack = new JButton("All Out Attack");
		jButtonAllOutAttack.setBounds(670, 50, 100, 30);
		jButtonAllOutAttack.addActionListener(this);
		add(jButtonAllOutAttack);

		JLabel jSourceCountryLabel = new JLabel();
		jSourceCountryLabel.setBounds(170, 20, 200, 30);
		jSourceCountryLabel.setText("Source Country");
		add(jSourceCountryLabel);

		JLabel jTargetCountryLabel = new JLabel();
		jTargetCountryLabel.setBounds(300, 20, 200, 30);
		jTargetCountryLabel.setText("Target Country");
		add(jTargetCountryLabel);

		JLabel jNoOfDiceLabel = new JLabel();
		jNoOfDiceLabel.setBounds(430, 20, 200, 30);
		jNoOfDiceLabel.setText("Select No of Dice");
		add(jNoOfDiceLabel);

		jSourceArmyLabel = new JLabel();
		jSourceArmyLabel.setBounds(217, 78, 200, 30);
		add(jSourceArmyLabel);

		jTargetArmyLabel = new JLabel();
		jTargetArmyLabel.setBounds(337, 78, 200, 30);
		add(jTargetArmyLabel);

		ImageIcon diceIcon = new ImageIcon("resources/dice-icon.png");
		diceImage = new JLabel("");
		diceImage.setIcon(diceIcon);
		diceImage.setBounds(890, 15, 100, 50);
		add(diceImage);

		attackDice1 = new JLabel("");
		attackDice1.setBounds(840, 50, 100, 30);
		add(attackDice1);

		attackDice2 = new JLabel("");
		attackDice2.setBounds(840, 70, 100, 30);
		add(attackDice2);

		attackDice3 = new JLabel("");
		attackDice3.setBounds(840, 90, 100, 30);
		add(attackDice3);

		defendDice1 = new JLabel("");
		defendDice1.setBounds(940, 50, 100, 30);
		add(defendDice1);

		defendDice2 = new JLabel("");
		defendDice2.setBounds(940, 70, 100, 30);
		add(defendDice2);
		jButtonFinishAttack = new JButton("Finish Attack");
		jButtonFinishAttack.setBounds(Constants.MAP_EDITOR_WIDTH / 2 - 100, Constants.HEIGHT / 2, 200, 30);
		jButtonFinishAttack.addActionListener(this);
		add(jButtonFinishAttack);
		attackWindowController = new AttackWindowController(players, this, gameModel);
		jComboBoxSourceCountries.addActionListener(this);
		jComboBoxTargetCountries.addActionListener(this);
		jComboBoxNoOfDice.addActionListener(this);
		if (!ifAttackValid()) {
			dispose();
			getCurrentPlayer().fortificationPhase();
		}

	}

	/**
	 * Update player label.
	 *
	 * @param label the label
	 */
	public void updatePlayerLabel(String label) {
		jPlayerLabel.setText(label);
	}

	/**
	 * Update combo box source countries.
	 *
	 * @param countryModels the country models
	 */
	public void updateComboBoxSourceCountries(ArrayList<String> countryModels) {
		jComboBoxSourceCountries.removeAllItems();
		jComboBoxSourceCountries.addItem("Select country:");
		if (countryModels != null) {
			for (int i = countryModels.size() - 1; i >= 0; i--) {
				jComboBoxSourceCountries.addItem(countryModels.get(i));
			}

		}
		jComboBoxSourceCountries.setSelectedIndex(0);
	}

	/**
	 * Update combo box target countries.
	 *
	 * @param countryModels the country models
	 */
	public void updateComboBoxTargetCountries(ArrayList<String> countryModels) {
		jComboBoxTargetCountries.removeAllItems();
		jComboBoxTargetCountries.addItem("Select country:");
		if (countryModels != null) {
			for (int i = countryModels.size() - 1; i >= 0; i--) {
				jComboBoxTargetCountries.addItem(countryModels.get(i));
			}
		}

	}

	/**
	 * Update combo box no of dice.
	 *
	 * @param noOfDice the no of dice
	 */
	public void updateComboBoxNoOfDice(int noOfDice) {
		jComboBoxNoOfDice.removeAllItems();
		jComboBoxNoOfDice.addItem("Select Die:");
		if (noOfDice == 3) {
			jComboBoxNoOfDice.addItem("1");
			jComboBoxNoOfDice.addItem("2");
			jComboBoxNoOfDice.addItem("3");
		}
		if (noOfDice == 2) {
			jComboBoxNoOfDice.addItem("1");
			jComboBoxNoOfDice.addItem("2");
		}
		if (noOfDice == 1) {
			jComboBoxNoOfDice.addItem("1");
		}
	}

	/**
	 * Update target army label.
	 *
	 * @param armyCount the army count
	 */
	public void updateTargetArmyLabel(int armyCount) {
		jTargetArmyLabel.setText(String.valueOf(armyCount));
	}

	/**
	 * Update source army label.
	 *
	 * @param armyCount the army count
	 */
	public void updateSourceArmyLabel(int armyCount) {
		jSourceArmyLabel.setText(String.valueOf(armyCount));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jComboBoxSourceCountries) {
			String sourceCountryName = (String) jComboBoxSourceCountries.getSelectedItem();
			if (sourceCountryName != null && !sourceCountryName.trim().equalsIgnoreCase("Select country:")) {
				CountryModel sourceCountry = gameModel.getMapHierarchyModel().searchCountry(sourceCountryName);
				updateSourceArmyLabel(sourceCountry.getNoOfArmiesCountry());
				attackWindowController.finding(sourceCountryName);
				System.out.println("targetCountryList().size()" + attackWindowController.targetCountryList().size());
				if (attackWindowController.targetCountryList().size() < 1) {
					JOptionPane.showMessageDialog(this, "No Target country eligible from this country", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				} else {

					attackWindowController.updateNoOfDiceUIInfo(sourceCountry);
				}
				attackWindowController.updateTargetUIInfo();

			} else {
				updateComboBoxTargetCountries(null);
				updateSourceArmyLabel(0);
			}
			updateTargetArmyLabel(0);
		} else if (e.getSource() == jComboBoxTargetCountries) {
			System.out.println(jComboBoxTargetCountries.getSelectedIndex());
			if (jComboBoxTargetCountries.getSelectedIndex() > 0) {
				String targetCountryName = (String) jComboBoxTargetCountries.getSelectedItem();
				CountryModel targetCountry = gameModel.getMapHierarchyModel().searchCountry(targetCountryName);
				updateTargetArmyLabel(targetCountry.getNoOfArmiesCountry());
			} else
				updateTargetArmyLabel(0);
		} else if (e.getSource() == jComboBoxNoOfDice) {

		} else if (e.getSource() == jButtonAttack) {
			diceResultsAttacking.clear();
			diceResultsDefending.clear();

			String sourceCountryName = (String) jComboBoxSourceCountries.getSelectedItem();
			String defenderCountryName = (String) jComboBoxTargetCountries.getSelectedItem();
			if (sourceCountryName.trim().equalsIgnoreCase("Select country:")) {
				JOptionPane.showMessageDialog(this, "Select source country !!", "Error Message",
						JOptionPane.ERROR_MESSAGE);

				return;
			}
			if (defenderCountryName.trim().equalsIgnoreCase("Select country:")) {
				JOptionPane.showMessageDialog(this, "Select target country !!", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String diceComboValue = (String) jComboBoxNoOfDice.getSelectedItem();
			int noOfDiceSelected = 0;
			int defenderArmyCount = 0;
			defenderArmyCount = Integer.valueOf(jTargetArmyLabel.getText().toString());
			if (defenderArmyCount == 0) {
				JOptionPane.showMessageDialog(this, "Defender has zero army !!", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (diceComboValue.trim().equalsIgnoreCase("Select Die:")) {
				JOptionPane.showMessageDialog(this, "Please select no of Dice !!", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				noOfDiceSelected = Integer.valueOf(diceComboValue);
				if (defenderArmyCount > 2)
					defenderArmyCount = 2;
				if (defenderArmyCount > noOfDiceSelected)
					defenderArmyCount = 1;
				setDiceVisiblityAccordingToDiceSelected(noOfDiceSelected, defenderArmyCount);
			}

			attackWindowController.attack(sourceCountryName, defenderCountryName, noOfDiceSelected, defenderArmyCount);
			CountryModel sourceCountry = gameModel.getMapHierarchyModel().searchCountry(sourceCountryName);
			attackWindowController.updateNoOfDiceUIInfo(sourceCountry);
			if (!ifAttackValid()) {
				dispose();
				getCurrentPlayer().fortificationPhase();
			} else if (getCurrentPlayer().isGameWon(gameModel.getMapHierarchyModel().totalCountries)) {
				gameModel.setGameState(1);
				JOptionPane.showMessageDialog(this, getCurrentPlayer().getPlayerName() + " has Won the Game !!",
						"Congratulations !!!", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == jButtonAllOutAttack) {

			String sourceCountryName = (String) jComboBoxSourceCountries.getSelectedItem();
			String defenderCountryName = (String) jComboBoxTargetCountries.getSelectedItem();
			if (sourceCountryName.trim().equalsIgnoreCase("Select country:")) {
				JOptionPane.showMessageDialog(this, "Select source country !!", "Error Message",
						JOptionPane.ERROR_MESSAGE);

				return;
			}
			if (defenderCountryName.trim().equalsIgnoreCase("Select country:")) {
				JOptionPane.showMessageDialog(this, "Select target country !!", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int defenderArmyCount = 0;
			defenderArmyCount = Integer.valueOf(jTargetArmyLabel.getText().toString());
			if (defenderArmyCount == 0) {
				JOptionPane.showMessageDialog(this, "Defender has zero army !!", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			attackWindowController.allOutAttack(sourceCountryName, defenderCountryName);
			CountryModel sourceCountry = gameModel.getMapHierarchyModel().searchCountry(sourceCountryName);
			attackWindowController.updateNoOfDiceUIInfo(sourceCountry);
			if (!ifAttackValid()) {
				dispose();
				getCurrentPlayer().fortificationPhase();
			} else if (getCurrentPlayer().isGameWon(gameModel.getMapHierarchyModel().totalCountries)) {
				gameModel.setGameState(1);
				JOptionPane.showMessageDialog(this, getCurrentPlayer().getPlayerName() + " has Won the Game !!",
						"Congratulations !!!", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == jButtonFinishAttack) {

			dispose();
			getCurrentPlayer().fortificationPhase();
		}
	}

	/**
	 * Gets the current player.
	 *
	 * @return the currentPlayer
	 */
	public PlayerModel getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Change dice visiblity.
	 *
	 * @param jLabelDice the j label dice
	 * @param isVisible  the is visible
	 */
	public void changeDiceVisiblity(JLabel jLabelDice, boolean isVisible) {
		jLabelDice.setVisible(isVisible);

	}

	/**
	 * Update dice value.
	 *
	 * @param jLabelDice the j label dice
	 * @param diceValue  the dice value
	 */
	public void updateDiceValue(JLabel jLabelDice, int diceValue) {
		jLabelDice.setText(String.valueOf(diceValue));

	}

	/**
	 * Sets the dice visiblity according to dice selected.
	 *
	 * @param noOfDiceSelected  the no of dice selected
	 * @param defenderArmyCount the defender army count
	 */
	public void setDiceVisiblityAccordingToDiceSelected(int noOfDiceSelected, int defenderArmyCount) {
		switch (noOfDiceSelected) {
		case 1:
			changeDiceVisiblity(attackDice1, true);
			changeDiceVisiblity(attackDice2, false);
			changeDiceVisiblity(attackDice3, false);
			break;
		case 2:
			changeDiceVisiblity(attackDice1, true);
			changeDiceVisiblity(attackDice2, true);
			changeDiceVisiblity(attackDice3, false);
			break;
		case 3:
			changeDiceVisiblity(attackDice1, true);
			changeDiceVisiblity(attackDice2, true);
			changeDiceVisiblity(attackDice3, true);
			break;
		default:
			changeDiceVisiblity(attackDice1, false);
			changeDiceVisiblity(attackDice2, false);
			changeDiceVisiblity(attackDice3, false);
			break;
		}
		if (defenderArmyCount > 2)
			defenderArmyCount = 2;
		switch (defenderArmyCount) {
		case 1:
			changeDiceVisiblity(defendDice1, true);
			changeDiceVisiblity(defendDice2, false);

			break;
		case 2:
			changeDiceVisiblity(defendDice1, true);
			changeDiceVisiblity(defendDice2, true);

			break;
		default:
			changeDiceVisiblity(defendDice1, false);
			changeDiceVisiblity(defendDice2, false);

			break;
		}
	}

	/**
	 * Sets the dice values.
	 *
	 * @param noOfDiceSelected  the no of dice selected
	 * @param defenderArmyCount the defender army count
	 */
	public void setDiceValues(int noOfDiceSelected, int defenderArmyCount) {
		Collections.sort(diceResultsAttacking, Collections.reverseOrder());
		Collections.sort(diceResultsDefending, Collections.reverseOrder());
		int maxAttackValue = diceResultsAttacking.get(0);

		int maxDefenderValue = diceResultsDefending.get(0);
		int secondMaxAttackValue = 0;
		int thirdAttackValue = 0;
		int secondDefendValue = 0;
		if (diceResultsAttacking.size() > 1) {
			secondMaxAttackValue = diceResultsAttacking.get(1);
		}
		if (diceResultsDefending.size() > 1)
			secondDefendValue = diceResultsDefending.get(1);

		if (diceResultsAttacking.size() > 2)
			thirdAttackValue = diceResultsAttacking.get(2);
		switch (noOfDiceSelected) {
		case 1:
			updateDiceValue(attackDice1, maxAttackValue);
			break;
		case 2:

			updateDiceValue(attackDice1, maxAttackValue);
			updateDiceValue(attackDice2, secondMaxAttackValue);
			break;
		case 3:

			updateDiceValue(attackDice1, maxAttackValue);
			updateDiceValue(attackDice2, secondMaxAttackValue);
			updateDiceValue(attackDice3, thirdAttackValue);
			break;
		default:
			break;
		}

		switch (defenderArmyCount) {
		case 1:
			updateDiceValue(defendDice1, maxDefenderValue);
			break;
		case 2:
			updateDiceValue(defendDice1, maxDefenderValue);
			updateDiceValue(defendDice2, secondDefendValue);
			break;
		default:
			break;
		}
	}

	/**
	 * Gets the dice results attacking.
	 *
	 * @return the diceResultsAttacking
	 */
	public ArrayList<Integer> getDiceResultsAttacking() {
		return diceResultsAttacking;
	}

	/**
	 * Gets the dice results defending.
	 *
	 * @return the diceResultsDefending
	 */
	public ArrayList<Integer> getDiceResultsDefending() {
		return diceResultsDefending;
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public PlayerModel[] getPlayers() {
		return players;
	}

	/**
	 * Show move army popup.
	 *
	 * @param minValue           the min value
	 * @param maxValue           the max value
	 * @param sourceCountry      the source country
	 * @param destinationCountry the destination country
	 */
	public void showMoveArmyPopup(int minValue, int maxValue, CountryModel sourceCountry,
			CountryModel destinationCountry) {
		int size = maxValue - minValue + 1;
		Integer[] options = new Integer[size];
		for (int i = minValue; i <= maxValue; i++) {
			int index = i - minValue;
			options[index] = i;
		}
		int n = (Integer) JOptionPane.showInputDialog(null, "Select no of armies to be moved.", "Move Armies",
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		for (int j = 0; j < n; j++) {
			sourceCountry.removeNoOfArmiesCountry();
			destinationCountry.addNoOfArmiesCountry();
		}
	}

	/**
	 * If attack valid.
	 *
	 * @return true, if successful
	 */
	public boolean ifAttackValid() {
		boolean isAttackPossible = false;
		for (CountryModel countryModel : getCurrentPlayer().getPlayerCountryList()) {
			if (countryModel.getNoOfArmiesCountry() > 1) {
				isAttackPossible = true;
				System.out.println("hbguhu");

				break;
			}
		}
		return isAttackPossible;
	}

}
