package com.conquest.controller;

import java.util.ArrayList;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.FortificationWindow;

/**
 * The Class FortificationWindowController.
 * 
 * @author Tushar
 */
public class FortificationWindowController {

	/** The reinforcement window. */
	private FortificationWindow reinforcementWindow;

	/** The no of players. */
	private int noOfPlayers = 0;

	/** The source counter. */
	private int sourceCounter = 0;

	/** The destination counter. */
	private int destinationCounter = -1;

	/** The counter. */
	private int counter = 0;

	/** The players. */
	private PlayerModel[] players;

	/** The destination country models. */
	ArrayList<String> destinationCountryModels = new ArrayList<>();

	/**
	 * Instantiates a new fortification window controller.
	 *
	 * @param players             the players
	 * @param reinforcementWindow the reinforcement window
	 * @param noOfPlayers         the no of players
	 * @param mapModel            the map model
	 */
	public FortificationWindowController(PlayerModel[] players, FortificationWindow reinforcementWindow,
			int noOfPlayers, MapHierarchyModel mapModel) {
		this.reinforcementWindow = reinforcementWindow;
		this.noOfPlayers = noOfPlayers;
		this.players = players;

		updateSourceUI();
		this.reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());
	}

	/**
	 * Sending Countries.
	 *
	 * @param armies the armies
	 */
	public void sending(int armies) {
		for (int s = 0; s < armies; s++) {
			players[counter].getPlayerCountryList().get(destinationCounter).addNoOfArmiesCountry();
			players[counter].getPlayerCountryList().get(sourceCounter).removeNoOfArmiesCountry();
		}
		System.out.println("Newnumber of armies in source: "
				+ players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		System.out.println("Newnumber of armies in destination: "
				+ players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
		updateUIInfo();
	}
	
	/**
	 * Next player.
	 */
	public void nextPlayer() {
		if (counter < noOfPlayers - 1)
			counter++;
		else
			afterFortification();

	}

	/**
	 * After fortification.
	 */
	public void afterFortification() {
		reinforcementWindow.dispose();
	}

	/**
	 * Update Backend.
	 */
	public void updateBackend() {

		sourceCounter = 0;
		destinationCounter = -1;
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
		reinforcementWindow.updateComboBoxDestinationCountries(destinationCountryModels);
		reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());
		updateSourceUI();
		reinforcementWindow.newArmyLabel();

	}

	/**
	 * Finding Countries in Fortification.
	 *
	 * @param sourceCountry the source country
	 */
	public void finding(String sourceCountry) {

		PlayerModel playerModel = players[counter];

		ArrayList<String> tempDestList = new ArrayList<>();
		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountry)) {
				sourceCounter = playerModel.getPlayerCountryList().indexOf(countryModel);
				tempDestList.addAll(countryModel.getListOfNeighbours());
				break;
			}
		}

		for (String destinationCountryName : tempDestList) {
			CountryModel countryModel = playerModel.searchCountry(destinationCountryName.trim());
			if (countryModel != null) {
				destinationCountryModels.add(destinationCountryName.trim());
			}
		}

	}

	/**
	 * Checking.
	 *
	 * @return true, if successful
	 */
	public boolean checking() {
		if (players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry() == 1)
			return true;
		else
			return false;
	}

	/**
	 * Update destination UI.
	 *
	 * @param selectedDestination the selected destination
	 */
	public void updateDestinationUI(String selectedDestination) {
		for (int i = 0; i < players[counter].getPlayerCountryList().size(); i++) {
			if (players[counter].getPlayerCountryList().get(i).getCountryName().equalsIgnoreCase(selectedDestination)) {
				destinationCounter = i;
				break;
			}
		}
		if (destinationCounter >= 0)
			reinforcementWindow.updateDestinationArmyLabel(
					players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
		else
			reinforcementWindow.updateDestinationArmyLabel(0);
	}

	/**
	 * Update source UI.
	 */
	public void updateSourceUI() {
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
	}

	/**
	 * Update UI info.
	 */
	public void updateUIInfo() {

		reinforcementWindow
				.updateArmy(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		reinforcementWindow.updateSourceArmyLabel(
				players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		reinforcementWindow.updateComboBoxDestinationCountries(destinationCountryModels);
		destinationCountryModels.clear();

	}

}
