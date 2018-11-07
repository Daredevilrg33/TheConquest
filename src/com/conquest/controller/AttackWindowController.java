package com.conquest.controller;

import java.util.ArrayList;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.model.GameModel;

import java.util.Collections;
import com.conquest.window.AttackPhaseWindow;
import java.security.SecureRandom;

/**
 * The Class AttackWindowController.
 *
 * @author Siddhant
 */

public class AttackWindowController {
	
	/** The attack phase window. */
	private AttackPhaseWindow attackPhaseWindow;

	/** The no of players. */
	private int noOfPlayers = 0;

	/** The source counter. */
	private int sourceCounter = 0;

	/** The destination counter. */
	private int targetCounter = -1;

	/** The counter. */
	private int counter = 0;

	/** The players. */
	private PlayerModel[] players;

	/** The destination country models. */
	ArrayList<String> targetCountryModels = new ArrayList<>();
	
	/** The source country models. */
	ArrayList<String> sourceCountryModels = new ArrayList<>();
	
	

	/**
	 * Instantiates a new attack window controller.
	 *
	 * @param players the players
	 * @param attackPhaseWindow the attack phase window
	 * @param noOfPlayers the no of players
	 * @param riskMapModel the risk map model
	 */
	public AttackWindowController(PlayerModel[] players, AttackPhaseWindow attackPhaseWindow,
			GameModel riskMapModel, PlayerModel currPlayer) {
		
		this.attackPhaseWindow = attackPhaseWindow;
		this.noOfPlayers = players.length;
		this.players = players;

		updateSourceUIInfo();
		attackPhaseWindow.updatePlayerLabel(currPlayer.getPlayerName());
	}

	/**
	 * Next player.
	 * Switches to next player
	 */
	public void nextPlayer() {
		if (counter < noOfPlayers) {
			counter++;
		} else
			counter = 0;
	}

	/**
	 * Finding Countries.
	 *
	 * @param sourceCountry the source country
	 */
	public void finding(String sourceCountry) {
		PlayerModel playerModel = players[counter];
		ArrayList<String> tempCountryModels1 = new ArrayList<>();
		ArrayList<String> tempCountryModels2 = new ArrayList<>();

		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountry.trim())) {
				sourceCounter = playerModel.getPlayerCountryList().indexOf(countryModel);
				tempCountryModels2.addAll(countryModel.getListOfNeighbours());
				tempCountryModels1.addAll(countryModel.getListOfNeighbours());
				targetCountryModels.addAll(countryModel.getListOfNeighbours());
				break;
			}
		}

		for (String neighbor : tempCountryModels1) {
			for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
				if (countryModel.getCountryName().trim().equalsIgnoreCase(neighbor)) {
					tempCountryModels2.remove(neighbor);
					targetCountryModels.remove(neighbor);
				}
			}
		}

		for (PlayerModel player : players) {
			for (String s : tempCountryModels2) {
				CountryModel c = player.searchCountry(s);
				if (c != null) {
					if (c.getNoOfArmiesCountry() < 1) {
						targetCountryModels.remove(s);
					}
				}
			}
		}

	}

	/**
	 * Roll dice.
	 *
	 * @return pickedNumber + 1 the number on dice rolled
	 */
	public int rollDice() {
		int pickedNumber;
		SecureRandom number = new SecureRandom();
		pickedNumber = number.nextInt(6);
		return pickedNumber + 1;
	}

	/**
	 * Attack.
	 *
	 * @param attackingCountry the attacking country
	 * @param targetCountry the target country
	 */
	public void attack(String attackingCountry, String targetCountry) {

		int i = 0;
		
		CountryModel attackingCountryModel = players[counter].searchCountry(attackingCountry.trim());
		CountryModel targetCountryModel = null;
		for (PlayerModel defendingPlayer : players) {
			targetCountryModel = defendingPlayer.searchCountry(targetCountry.trim());
			if (targetCountryModel != null) {
				break;
			}
		}

		if (attackingCountryModel.getNoOfArmiesCountry() > 3) {
			for (i = 0; i < 3; i++) {
				attackPhaseWindow.diceResultsAttacking.add(rollDice());
			}
		} else {
			for (i = 0; i < attackingCountryModel.getNoOfArmiesCountry(); i++) {
				attackPhaseWindow.diceResultsAttacking.add(rollDice());
			}
		}

		if (targetCountryModel.getNoOfArmiesCountry() > 2) {
			for (i = 0; i < 2; i++) {
				attackPhaseWindow.diceResultsDefending.add(rollDice());
			}
		} else {
			for (i = 0; i <= targetCountryModel.getNoOfArmiesCountry(); i++) {
				attackPhaseWindow.diceResultsDefending.add(rollDice());
			}
		}

		int objAttack = Collections.max(attackPhaseWindow.diceResultsAttacking);
		int objDefend = Collections.max(attackPhaseWindow.diceResultsDefending);
		System.out.println("largest dice in attacking " + objAttack);
		System.out.println("largest dice in defending " + objAttack);
		
		updateDiceView();
		
	}
	
	/**
	 * Update dice UI info.
	 */
	public void updateDiceView() {
		
			attackPhaseWindow.attackDice1.setText(attackPhaseWindow.diceResultsAttacking.get(0)+"");
			attackPhaseWindow.attackDice2.setText(attackPhaseWindow.diceResultsAttacking.get(1)+"");
			attackPhaseWindow.attackDice3.setText(attackPhaseWindow.diceResultsAttacking.get(2)+"");
			attackPhaseWindow.defendDice1.setText(attackPhaseWindow.diceResultsDefending.get(0)+"");
			attackPhaseWindow.defendDice2.setText(attackPhaseWindow.diceResultsDefending.get(1)+"");
		
	}

	/**
	 * Update source UI info.
	 */
	public void updateSourceUIInfo() {
		PlayerModel playerModel = players[counter];
		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			if (countryModel.getNoOfArmiesCountry() > 1) {
				sourceCountryModels.add(countryModel.getCountryName());
			}
		}
				attackPhaseWindow.updateComboBoxSourceCountries(sourceCountryModels);
	}
	 public void updateNoOfDiceUIInfo() {
		 int diceFlag = 0;
			PlayerModel playerModel = players[counter];
		 for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
				if (countryModel.getNoOfArmiesCountry() > 3) {
					diceFlag = 3;
			}	
				else if(countryModel.getNoOfArmiesCountry() > 2) {
					diceFlag = 2;
			}	
				else if (countryModel.getNoOfArmiesCountry() > 1) {
					diceFlag = 1;
			}	}
		 attackPhaseWindow.updateComboBoxNoOfDice(diceFlag);

	 }

	/**
	 * Target country list.
	 *
	 * @return the array list
	 */
	public ArrayList<String> targetCountryList() {
		return targetCountryModels;
	}

	/**
	 * Update target UI info.
	 */
	public void updateTargetUIInfo() {
		attackPhaseWindow.updateComboBoxTargetCountries(targetCountryModels);
		targetCountryModels.clear();
	}

}
