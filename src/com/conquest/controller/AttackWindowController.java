package com.conquest.controller;

import java.util.ArrayList;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.model.GameModel;

import java.util.Collections;
import com.conquest.window.AttackPhaseWindow;
import java.security.SecureRandom;

/**
 * @author Siddhant
 *
 */

public class AttackWindowController {
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
	ArrayList<String> sourceCountryModels = new ArrayList<>();

	public AttackWindowController(PlayerModel[] players, AttackPhaseWindow attackPhaseWindow, int noOfPlayers,
			GameModel riskMapModel) {
		this.attackPhaseWindow = attackPhaseWindow;
		this.noOfPlayers = noOfPlayers;
		this.players = players;

		updateSourceUIInfo();
		attackPhaseWindow.updatePlayerLabel(players[counter].getPlayerName());
	}

	public void nextPlayer() {
		if (counter < noOfPlayers) {
			counter++;
		} else
			counter = 0;
	}

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

	public int rollDice() {
		int pickedNumber;
		SecureRandom number = new SecureRandom();
		pickedNumber = number.nextInt(6);
		return pickedNumber + 1;
	}

	public void attack(String attackingCountry, String targetCountry) {

		int i = 0;
		ArrayList<Integer> diceResultsAttacking = new ArrayList<>();
		ArrayList<Integer> diceResultsDefending = new ArrayList<>();
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
				diceResultsAttacking.add(rollDice());
			}
		} else {
			for (i = 0; i < attackingCountryModel.getNoOfArmiesCountry(); i++) {
				diceResultsAttacking.add(rollDice());
			}
		}

		if (targetCountryModel.getNoOfArmiesCountry() > 2) {
			for (i = 0; i < 2; i++) {
				diceResultsDefending.add(rollDice());
			}
		} else {
			for (i = 0; i <= targetCountryModel.getNoOfArmiesCountry(); i++) {
				diceResultsDefending.add(rollDice());
			}
		}

		int obj = Collections.max(diceResultsAttacking);
		int obj1 = Collections.max(diceResultsDefending);
		System.out.println("largest dice in attacking " + obj);
		System.out.println("largest dice in defending " + obj1);
	}

	public void updateSourceUIInfo() {
		PlayerModel playerModel = players[counter];
		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			if (countryModel.getNoOfArmiesCountry() > 1) {
				sourceCountryModels.add(countryModel.getCountryName());
			}
		}
		attackPhaseWindow.updateComboBoxSourceCountries(sourceCountryModels);
	}

	public ArrayList<String> targetCountryList() {
		return targetCountryModels;
	}

	public void updateTargetUIInfo() {
		attackPhaseWindow.updateComboBoxTargetCountries(targetCountryModels);
		targetCountryModels.clear();
	}

}
