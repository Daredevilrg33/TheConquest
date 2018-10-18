package com.conquest.controller;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.GameWindow;
import com.conquest.controller.GameWindowController;
import com.conquest.window.ReinforcementWindow;
import java.util.ArrayList;

public class ReinfrocementWindowController {
	private ReinforcementWindow reinforcementWindow;
	private int noOfPlayers = 0;
	private MapHierarchyModel mapModel;
	private int armies;
	private int sourceCounter = 0;
	private int destinationCounter = 0;
	private int counter = 0;
	private PlayerModel[] players;
//	private int prevCounter = 0;
	private ArrayList<String> a = new ArrayList<>();

	public ReinfrocementWindowController(PlayerModel[] players, ReinforcementWindow reinforcementWindow,
			int noOfPlayers, MapHierarchyModel mapModel) {
		this.reinforcementWindow = reinforcementWindow;
		this.noOfPlayers = noOfPlayers;
		this.mapModel = mapModel;

		this.players =  players;

		updateSourceUI();
//		reinforcementWindow.updateSourceArmyLabel(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry())
//		reinforcementWindow.updateArmy(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
//		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
		reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());
		
	}

//	private void initializingPlayerModels(int Players, MapHierarchyModel mapModel) {
//	
//		players = new PlayerModel[Players];
//		updateUIInfo();
//	
//	}
	
	public void sending(int armies) {
		for(int s=0; s<armies;s++) {
		players[counter].getPlayerCountryList().get(destinationCounter).addNoOfArmiesCountry();
		players[counter].getPlayerCountryList().get(sourceCounter).removeNoOfArmiesCountry();
		}
		System.out.println("Newnumber of armies in source: "+players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		System.out.println("Newnumber of armies in destination: "+players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
		if(counter<noOfPlayers-1)
			counter++;
		else
			counter = 0;
		
		sourceCounter=0;
		destinationCounter=0;
		reinforcementWindow.newArmyLabel();
//		a.clear();
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
		reinforcementWindow.updatePlayerLabel(players[counter].getPlayerName());
//		updateSourceUI();

	}
	
//	public void updatePlayer() {
//		if(counter<=noOfPlayers)
//			counter++;
//		else
//			counter =0;
//	}
	
	public void finding(String sourceCountry) {

		sourceCounter = 0 ;
		destinationCounter = 0;
//		while(true) {
//			int i = 0;
//			int j = 0;
//			if(players[counter].getPlayerCountryList().get(sourceCounter).getCountryName().trim()
//					.equalsIgnoreCase(sourceCountry.trim())) {
//				System.out.println("country n first while loop "+players[counter].getPlayerCountryList().get(sourceCounter).getCountryName().trim());
//				while(true) {
//					if(players[counter].getPlayerCountryList().get(sourceCounter).getListOfNeighbours().get(i).trim()
//						.equalsIgnoreCase(players[counter].getPlayerCountryList().get(destinationCounter).getCountryName().trim())) {
//
//					System.out.println("New number of armies in destination"+players[counter].getPlayerCountryList().get(destinationCounter).getCountryName()+" is "+players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
//					j++;
//					a.add(players[counter].getPlayerCountryList().get(destinationCounter).getCountryName());
//					System.out.println("New number of armies in source"+players[counter].getPlayerCountryList().get(sourceCounter).getCountryName()+" is "+players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
//					break;
//
//				}
//					destinationCounter++;
//					if (destinationCounter >= players[counter].getPlayerCountryList().size()) {
//						destinationCounter = 0;
//						i++;
//						}				
//					}
//				//redo with for loop
//				if(j>0)
//					break;
//
//				i=0;
//			}
//			sourceCounter++;
//			}
		
		for(sourceCounter = 0; sourceCounter < players[counter].getPlayerCountryList().size(); sourceCounter++) {
			int i = 0;
			if(players[counter].getPlayerCountryList().get(sourceCounter).getCountryName().trim()
					.equalsIgnoreCase(sourceCountry.trim())) {
				System.out.println("country in first loop "+players[counter].getPlayerCountryList().get(sourceCounter).getCountryName().trim());	
				for(destinationCounter=0; destinationCounter<players[counter].getPlayerCountryList().size() - 1;destinationCounter++) {
					for(i = 0;i<players[counter].getPlayerCountryList().get(sourceCounter).getListOfNeighbours().size();i++) {
						if(players[counter].getPlayerCountryList().get(sourceCounter).getListOfNeighbours().get(i).trim()
								.equalsIgnoreCase(players[counter].getPlayerCountryList().get(destinationCounter).getCountryName())){
							
//							System.out.println("source Country is: "+players[counter].getPlayerCountryList().get(sourceCounter).getCountryName().trim());
							System.out.println("destination Country is: "+players[counter].getPlayerCountryList().get(destinationCounter).getCountryName());
							
							a.add(players[counter].getPlayerCountryList().get(destinationCounter).getCountryName());

						}
					}

				}
				break;
			}
		}
	
	}
		
	public void updateDestinationUI(String selectedDestination) {
		for(int i=0; i<players[counter].getPlayerCountryList().size();i++) {
			if(players[counter].getPlayerCountryList().get(i).getCountryName().equalsIgnoreCase(selectedDestination)) {
				destinationCounter = i;
				break;
			}
		}
		
		reinforcementWindow.updateDestinationArmyLabel(players[counter].getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());		
		
	}
	
	public void updateSourceUI() {
		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());

	}
	
	public void updateUIInfo() {
//		prevCounter = counter;

		reinforcementWindow.updateArmy(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		reinforcementWindow.updateSourceArmyLabel(players[counter].getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
//		reinforcementWindow.updateComboBoxSourceCountries(players[counter].getPlayerCountryList());
//		reinforcementWindow.updateComboBoxDestinationCountries(players[counter].getPlayerCountryList().get(destinationCounter).getListOfNeighbours());
		reinforcementWindow.updateComboBoxDestinationCountries(a);
		a.clear();
	}

}
