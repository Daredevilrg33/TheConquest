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

	/** The game model. */
	private GameModel gameModel;

	/**
	 * Instantiates a new attack window controller.
	 *
	 * @param players           the players
	 * @param attackPhaseWindow the attack phase window
	 * @param riskMapModel      the risk map model
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
	 * @param attackingCountry  the attacking country
	 * @param targetCountry     the target country
	 * @param attackArmyCount   the attack army count
	 * @param defenderArmyCount the defender army count
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

	public void allOutAttack(String attackingCountry, String targetCountry) {

		CountryModel attackingCountryModel = attackPhaseWindow.getCurrentPlayer()
				.searchCountry(attackingCountry.trim());

		CountryModel defendingCountryModel = null;
		defendingCountryModel = gameModel.getMapHierarchyModel().searchCountry(targetCountry.trim());

		
		while (attackingCountryModel.getNoOfArmiesCountry() > 1 && defendingCountryModel.getNoOfArmiesCountry() > 0) {
			
			if(attackingCountryModel.getNoOfArmiesCountry() > 3) {
				for (int i = 0; i < 3; i++) {
					int diceRollValue = rollDice();
					attackPhaseWindow.getDiceResultsAttacking().add(diceRollValue);
				}
			}
			if(attackingCountryModel.getNoOfArmiesCountry() == 3) {
				for (int i = 0; i < 2; i++) {
					int diceRollValue = rollDice();
					attackPhaseWindow.getDiceResultsAttacking().add(diceRollValue);
				}
			}if(attackingCountryModel.getNoOfArmiesCountry() == 2) {
			for (int i = 0; i < 1; i++) {
				int diceRollValue = rollDice();
				attackPhaseWindow.getDiceResultsAttacking().add(diceRollValue);
				}
			}
			if(defendingCountryModel.getNoOfArmiesCountry() > 2) {
				for (int i = 0; i < 2; i++) {
					attackPhaseWindow.getDiceResultsDefending().add(rollDice());
				}	
			}
			if(defendingCountryModel.getNoOfArmiesCountry() > 1) {
				for (int i = 0; i < 1; i++) {
					attackPhaseWindow.getDiceResultsDefending().add(rollDice());
				}	
			}
			
			attackEvaluation(attackPhaseWindow.getDiceResultsAttacking(), attackPhaseWindow.getDiceResultsDefending(),
					attackingCountryModel, defendingCountryModel);
		}
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
		int diceValues = 0;
		if (countryModel != null) {
			if (countryModel.getNoOfArmiesCountry() > 3) {
				diceValues = 3;
			} else if (countryModel.getNoOfArmiesCountry() > 2) {
				diceValues = 2;
			} else if (countryModel.getNoOfArmiesCountry() > 1) {
				diceValues = 1;
			}

		}

		attackPhaseWindow.updateComboBoxNoOfDice(diceValues);

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

	/**
	 * Attack evaluation.
	 *
	 * @param attackingDiceValues the attacking dice values
	 * @param defendingDiceValues the defending dice values
	 * @param attackerCountry     the attacker country
	 * @param defenderCountry     the defender country
	 */
	public void attackEvaluation(ArrayList<Integer> attackingDiceValues, ArrayList<Integer> defendingDiceValues,
			CountryModel attackerCountry, CountryModel defenderCountry) {
		Collections.sort(attackingDiceValues, Collections.reverseOrder());
		Collections.sort(defendingDiceValues, Collections.reverseOrder());
		int resultOfDice1 = attackingDiceValues.get(0).compareTo(defendingDiceValues.get(0));
		int resultofDice2 = -2;
		if (attackingDiceValues.size() > 1 && defendingDiceValues.size() > 1) {
			resultofDice2 = attackingDiceValues.get(1).compareTo(defendingDiceValues.get(1));
		}

		if (resultOfDice1 == 1)
			defenderCountry.removeNoOfArmiesCountry();
		else
			attackerCountry.removeNoOfArmiesCountry();
		if (!(resultofDice2 == -2)) {
			if (resultofDice2 == 1)

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
			attackPhaseWindow.showMoveArmyPopup(1, attackerCountry.getNoOfArmiesCountry() - 1, attackerCountry,
					defenderCountry);
		}

	}
}
