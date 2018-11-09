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

	/** The players. */
	private PlayerModel[] players;

	/** The destination country models. */
	private ArrayList<String> targetCountryModels = new ArrayList<>();

	/** The source country models. */
	private ArrayList<String> sourceCountryModels = new ArrayList<>();

	private GameModel gameModel;

	/**
	 * Instantiates a new attack window controller.
	 *
	 * @param players           the players
	 * @param attackPhaseWindow the attack phase window
	 * @param riskMapModel      the risk map model
	 * @param currentPlayer     the current player
	 */
	public AttackWindowController(PlayerModel[] players, AttackPhaseWindow attackPhaseWindow, GameModel riskMapModel) {

		this.attackPhaseWindow = attackPhaseWindow;
		this.noOfPlayers = players.length;
		this.players = players;
		this.gameModel = riskMapModel;
		updateSourceUIInfo();
		attackPhaseWindow.updatePlayerLabel(this.attackPhaseWindow.getCurrentPlayer().getPlayerName());
	}

	/**
	 * Finding Countries.
	 *
	 * @param sourceCountry the source country
	 */
	public void finding(String sourceCountry) {

		System.out.println("Player Name" + attackPhaseWindow.getCurrentPlayer().getPlayerName());
		ArrayList<String> tempCountryModels1 = new ArrayList<>();
		ArrayList<String> tempCountryModels2 = new ArrayList<>();

		for (CountryModel countryModel : attackPhaseWindow.getCurrentPlayer().getPlayerCountryList()) {
			if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountry.trim())) {
				sourceCounter = attackPhaseWindow.getCurrentPlayer().getPlayerCountryList().indexOf(countryModel);
				tempCountryModels2.addAll(countryModel.getListOfNeighbours());
				tempCountryModels1.addAll(countryModel.getListOfNeighbours());
				targetCountryModels.addAll(countryModel.getListOfNeighbours());

				break;
			}
		}

		System.out.println("Before Removing: " + targetCountryModels.size());
		for (String neighbor : tempCountryModels1) {
			for (CountryModel countryModel : attackPhaseWindow.getCurrentPlayer().getPlayerCountryList()) {
				if (countryModel.getCountryName().trim().equalsIgnoreCase(neighbor)) {
					tempCountryModels2.remove(neighbor);
					targetCountryModels.remove(neighbor);
				}
			}
		}
		System.out.println("After Removing: NEighbours " + targetCountryModels.size());

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
		System.out.println("After Removing: NEighbours " + targetCountryModels.size());

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
		System.out.println("Roll Dice Value: " + pickedNumber);
		return pickedNumber + 1;
	}

	/**
	 * Attack.
	 *
	 * @param attackingCountry the attacking country
	 * @param targetCountry    the target country
	 */
	public void attack(String attackingCountry, String targetCountry, int attackArmyCount, int defenderArmyCount) {

		CountryModel attackingCountryModel = attackPhaseWindow.getCurrentPlayer()
				.searchCountry(attackingCountry.trim());

		CountryModel defendingCountryModel = null;
		defendingCountryModel = gameModel.getMapHierarchyModel().searchCountry(targetCountry.trim());

		for (int i = 0; i < attackArmyCount; i++) {
			int diceRollValue = rollDice();
			attackPhaseWindow.getDiceResultsAttacking().add(diceRollValue);
		}

		for (int i = 0; i < defenderArmyCount; i++) {
			attackPhaseWindow.getDiceResultsDefending().add(rollDice());
		}
		attackEvaluation(attackPhaseWindow.getDiceResultsAttacking(), attackPhaseWindow.getDiceResultsDefending(),
				attackingCountryModel, defendingCountryModel);
		attackPhaseWindow.setDiceValues(attackArmyCount, defenderArmyCount);
	}

	/**
	 * Update source UI info.
	 */
	public void updateSourceUIInfo() {

		for (CountryModel countryModel : attackPhaseWindow.getCurrentPlayer().getPlayerCountryList()) {
			if (countryModel.getNoOfArmiesCountry() > 1) {
				sourceCountryModels.add(countryModel.getCountryName());
			}
		}
		attackPhaseWindow.updateComboBoxSourceCountries(sourceCountryModels);
	}

	/**
	 * Update no of dice UI info.
	 *
	 * @param countryModel the country model
	 */
	public void updateNoOfDiceUIInfo(CountryModel countryModel) {
		int diceFlag = 0;
		if (countryModel != null) {
			if (countryModel.getNoOfArmiesCountry() > 3) {
				diceFlag = 3;
			} else if (countryModel.getNoOfArmiesCountry() > 2) {
				diceFlag = 2;
			} else if (countryModel.getNoOfArmiesCountry() > 1) {
				diceFlag = 1;
			}

		}

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

	public void attackEvaluation(ArrayList<Integer> attackingDiceValues, ArrayList<Integer> defendingDiceValues,
			CountryModel attackerCountry, CountryModel defenderCountry) {
		Collections.sort(attackingDiceValues, Collections.reverseOrder());
		Collections.sort(defendingDiceValues, Collections.reverseOrder());
		int result1 = attackingDiceValues.get(0).compareTo(defendingDiceValues.get(0));
		int result2 = -2;
		if (attackingDiceValues.size() > 1 && defendingDiceValues.size() > 1) {
			result2 = attackingDiceValues.get(1).compareTo(defendingDiceValues.get(1));
		}

		if (result1 == 1)
			defenderCountry.removeNoOfArmiesCountry();
		else
			attackerCountry.removeNoOfArmiesCountry();
		if (!(result2 == -2)) {
			if (result2 == 1)

				defenderCountry.removeNoOfArmiesCountry();
			else
				attackerCountry.removeNoOfArmiesCountry();

		}
		attackPhaseWindow.updateSourceArmyLabel(attackerCountry.getNoOfArmiesCountry());
		attackPhaseWindow.updateTargetArmyLabel(defenderCountry.getNoOfArmiesCountry());
		if (defenderCountry.getNoOfArmiesCountry() == 0) {
			for (PlayerModel player : attackPhaseWindow.getPlayers()) {
				for (CountryModel countryModel : player.getPlayerCountryList()) {
					if (countryModel.getCountryName().trim()
							.equalsIgnoreCase(defenderCountry.getCountryName().trim())) {
						player.removeCountry(defenderCountry);
						break;
					}
				}
			}

			attackPhaseWindow.getCurrentPlayer().addCountry(defenderCountry);
			
			ArrayList<String> sourceCountryValues = new ArrayList<>();
			for (CountryModel countryModel : attackPhaseWindow.getCurrentPlayer().getPlayerCountryList()) {
				sourceCountryValues.add(countryModel.getCountryName());
			}
			attackPhaseWindow.updateComboBoxSourceCountries(sourceCountryValues);

		}

	}
}
