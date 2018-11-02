package com.conquest.controller;

import java.util.ArrayList;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.AttackPhaseWindow;

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
	
	public AttackWindowController(PlayerModel[] players, AttackPhaseWindow attackPhaseWindow,
		int noOfPlayers, MapHierarchyModel mapModel) {
		this.attackPhaseWindow = attackPhaseWindow;
		this.noOfPlayers = noOfPlayers;
		this.players = players;
		
		updateSourceUIInfo();
		attackPhaseWindow.updatePlayerLabel(players[counter].getPlayerName());
	}
	
	public void nextPlayer() {
		if (counter < noOfPlayers) {
			counter++;
		}
		else
			counter = 0;
	}
	
	public void finding(String sourceCountry) {
		PlayerModel playerModel = players[counter];

			for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
				if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountry.trim())) {
					sourceCounter = playerModel.getPlayerCountryList().indexOf(countryModel);
					targetCountryModels.addAll(countryModel.getListOfNeighbours());
					break;
				}
			}
			
			for(String neighbor : targetCountryModels) {
				for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
					if (countryModel.getCountryName().trim().equalsIgnoreCase(neighbor)) {
						targetCountryModels.remove(neighbor);
						break;
					}
				}
			}
		
	}
	
	public void updateSourceUIInfo() {
		attackPhaseWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
	}
	
	public void updateTargetUIInfo() {
		attackPhaseWindow.updateComboBoxTargetCountries(targetCountryModels);
		targetCountryModels.clear();

	}

}
