/**
 * 
 */
package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.conquest.controller.AttackWindowController;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
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

	private JComboBox<String> jComboBoxNoOfDice;

	/** The j button attack. */
	private JButton jButtonAttack;

	/** The j player label. */
	private JLabel jPlayerLabel;

	/** The j button all out attack. */
	private JButton jButtonAllOutAttack;

	/** The risk map model. */
	private GameModel riskMapModel;

	/** The player. */
	private PlayerModel[] player;

	private JLabel diceImage;
	public JLabel attackDice1;
	public JLabel attackDice2;
	public JLabel attackDice3;
	public JLabel defendDice1;
	public JLabel defendDice2;

	public ArrayList<Integer> diceResultsAttacking = new ArrayList<>();

	public ArrayList<Integer> diceResultsDefending = new ArrayList<>();

	/**
	 * Instantiates a new attack phase window.
	 *
	 * @param riskMapModel the risk map model
	 * @param playerModel  the player model
	 */
	public AttackPhaseWindow(GameModel riskMapModel, PlayerModel[] playerModel, PlayerModel currentPlayer) {
		this.riskMapModel = riskMapModel;
		this.player = playerModel;
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
		jNoOfDiceLabel.setText("Select Dice");
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

		attackWindowController = new AttackWindowController(player, this, riskMapModel, currentPlayer);
		jComboBoxSourceCountries.addActionListener(this);
		jComboBoxTargetCountries.addActionListener(this);
		jComboBoxNoOfDice.addActionListener(this);

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

		for (int i = countryModels.size() - 1; i >= 0; i--) {
			jComboBoxSourceCountries.addItem(countryModels.get(i));
		}

	}

	/**
	 * Update combo box target countries.
	 *
	 * @param countryModels the country models
	 */
	public void updateComboBoxTargetCountries(ArrayList<String> countryModels) {
		jComboBoxTargetCountries.removeAllItems();
		jComboBoxTargetCountries.addItem("Select country:");

		for (int i = countryModels.size() - 1; i >= 0; i--) {
			jComboBoxTargetCountries.addItem(countryModels.get(i));
		}
	}

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
			if (!sourceCountryName.trim().equalsIgnoreCase("Select country:")) {
				CountryModel sourceCountry = riskMapModel.getRiskGameModel().searchCountry(sourceCountryName);
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

			}
			updateTargetArmyLabel(0);
		} else if (e.getSource() == jComboBoxTargetCountries) {
			System.out.println(jComboBoxTargetCountries.getSelectedIndex());
			if (jComboBoxTargetCountries.getSelectedIndex() > 0) {
				String targetCountryName = (String) jComboBoxTargetCountries.getSelectedItem();
				CountryModel targetCountry = riskMapModel.getRiskGameModel().searchCountry(targetCountryName);
				updateTargetArmyLabel(targetCountry.getNoOfArmiesCountry());
			}
		} else if (e.getSource() == jComboBoxNoOfDice) {
			System.out.println(jComboBoxNoOfDice.getSelectedIndex());
//			if (jComboBoxNoOfDice.getSelectedIndex() != 0) {
//			
//			}
		} else if (e.getSource() == jButtonAttack) {
			attackWindowController.attack((String) jComboBoxSourceCountries.getSelectedItem(),
					(String) jComboBoxTargetCountries.getSelectedItem());

		} else if (e.getSource() == jButtonAllOutAttack) {

		}
	}
}
