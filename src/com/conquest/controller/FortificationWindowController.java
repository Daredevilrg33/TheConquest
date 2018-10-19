package com.conquest.controller;

import java.util.ArrayList;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.FortificationWindow;

public class FortificationWindowController {
	private FortificationWindow reinforcementWindow;
	private int noOfPlayers = 0;
	private int sourceCounter = 0;
	private int destinationCounter = 0;
	private int counter = 0;
	private PlayerModel[] players;
	ArrayList<String> destinationCountryModels = new ArrayList<>();

	public FortificationWindowController(PlayerModel[] players, FortificationWindow reinforcementWindow,
			int noOfPlayers, MapHierarchyModel mapModel) {
		this.reinforcementWindow = reinforcementWindow;
		this.noOfPlayers = noOfPlayers;
		this.players = players;

		updateSourceUI();
//		reinforcementWindow.updateSourceArmyLabel(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry())
//		reinforcementWindow.updateArmy(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
//		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
		reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());
	}

	public void sending(int armies) {
		for (int s = 0; s < armies; s++) {
			players[counter].getPlayerCountryList().get(destinationCounter).addNoOfArmiesCountry();
			players[counter].getPlayerCountryList().get(sourceCounter).removeNoOfArmiesCountry();
		}
		System.out.println("Newnumber of armies in source: "
				+ players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		System.out.println("Newnumber of armies in destination: "
				+ players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
		if (counter < noOfPlayers - 1)
			counter++;
		else
			counter = 0;

		sourceCounter = 0;
		destinationCounter = 0;
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
		reinforcementWindow.updateComboBoxDestinationCountries(destinationCountryModels);
		reinforcementWindow.newArmyLabel();
		reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());
	}

	public void skipping() {
		if (counter < noOfPlayers - 1)
			counter++;
		else
			counter = 0;
		sourceCounter = 0;
		destinationCounter = 0;
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
		reinforcementWindow.updateComboBoxDestinationCountries(destinationCountryModels);
		reinforcementWindow.newArmyLabel();
		reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());

	}

	public void finding(String sourceCountry) {

		PlayerModel playerModel = players[counter];

		ArrayList<String> tempDestList = new ArrayList<>();
		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountry)) {
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

	public boolean checking() {
		if (players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry() == 1)
			return true;
		else
			return false;
	}

	public void updateDestinationUI(String selectedDestination) {
		for (int i = 0; i < players[counter].getPlayerCountryList().size(); i++) {
			if (players[counter].getPlayerCountryList().get(i).getCountryName().equalsIgnoreCase(selectedDestination)) {
				destinationCounter = i;
				break;
			}
		}
		reinforcementWindow.updateDestinationArmyLabel(
				players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
	}

	public void updateSourceUI() {
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
	}

	public void updateUIInfo() {

		reinforcementWindow
				.updateArmy(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		reinforcementWindow.updateSourceArmyLabel(
				players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
//		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
//		reinforcementWindow.updateComboBoxDestinationCountries(players[counter].getPlayerCountryList().get(destinationCounter).getListOfNeighbours());
		reinforcementWindow.updateComboBoxDestinationCountries(destinationCountryModels);
		destinationCountryModels.clear();

	}

}
