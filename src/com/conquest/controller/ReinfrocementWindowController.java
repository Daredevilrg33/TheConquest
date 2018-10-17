package com.conquest.controller;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.GameWindow;
import com.conquest.controller.GameWindowController;
import com.conquest.window.ReinforcementWindow;

public class ReinfrocementWindowController {
	private ReinforcementWindow reinforcementWindow;
	private int noOfPlayers = 0;
	private MapHierarchyModel mapModel;
	private int sourceCounter = 0;
	private int destinationCounter = 0;
	private int counter = 0;
	private PlayerModel[] players;
	private int prevCounter = 0;

	
	
	public  ReinfrocementWindowController(PlayerModel[] players,ReinforcementWindow reinforcementWindow, int noOfPlayers, MapHierarchyModel mapModel) {
		this.reinforcementWindow = reinforcementWindow;
		this.noOfPlayers = noOfPlayers;
		this.mapModel = mapModel;
		this.players =  players;
		
		updateUIInfo();

	}
	
//	private void initializingPlayerModels(int Players, MapHierarchyModel mapModel) {
//	
//		players = new PlayerModel[Players];
//		updateUIInfo();
//	
//	}
	
	public void sending(String sourceCountry,String destinationCountry,int armies) {

		while(true) {
			if(players[prevCounter].getPlayerCountryList().get(sourceCounter).getCountryName().trim()
					.equalsIgnoreCase(sourceCountry.trim())) {
				if(players[prevCounter].getPlayerCountryList().get(destinationCounter).getCountryName().trim()
						.equalsIgnoreCase(destinationCountry.trim())) {
					for(int s=0; s<armies;s++) {
						players[prevCounter].getPlayerCountryList().get(destinationCounter).addNoOfArmiesCountry();
						players[prevCounter].getPlayerCountryList().get(sourceCounter).removeNoOfArmiesCountry();
						}
					System.out.println("New number of armies in destination"+players[prevCounter].getPlayerCountryList().get(destinationCounter).getCountryName()+" is "+players[prevCounter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
					System.out.println("New number of armies in source"+players[prevCounter].getPlayerCountryList().get(sourceCounter).getCountryName()+" is "+players[prevCounter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
					break;
				}
				destinationCounter++;
				if (destinationCounter > players[prevCounter].getPlayerCountryList().size()) {
					destinationCounter = 0;
					sourceCounter = 0;
					prevCounter++;
				}
			}
			sourceCounter++;
//			if (sourceCounter >= players[prevCounter].getPlayerCountryList().size()) {
//				destinationCounter = 0;
//				sourceCounter = 0;
//				prevCounter++;
//				}
			}
		}
		
	
	public void updateUIInfo() {
		prevCounter = counter;

		reinforcementWindow.updateArmy(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());
		reinforcementWindow.updateArmyLabel(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry(),players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
		reinforcementWindow.updateComboBoxDestinationCountries(players[counter].getPlayerCountryList().get(destinationCounter).getListOfNeighbours());
		counter++;
		if (counter == noOfPlayers)
			counter = 0;
		
	}
	

}
