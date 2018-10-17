package com.conquest.controller;

import java.util.Random;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.GameWindow;
import com.conquest.window.ReinforcementWindow;

/**
 * Game Window Controller Class
 * This class initializes Number of players, Player model, Game window object and Map HeirarchyModel 
 * @author Rohit Gupta
 * @version 1.0.0
 */
public class GameWindowController {

	private GameWindow gameWindow;
	private int noOfPlayers = 0;
	private MapHierarchyModel mapModel;
	private int counter = 0;
	private PlayerModel[] players;
	private int i = 0;
	private int prevCounter = 0;
	
/**
 * Game Window Controller Constructor
 * Constructor created to assign value of objects
 * @param gameWindow Object of class GameWindow {@link GameWindow}
 * @param noOfPlayers Number of players in game Range from (3-5)
 * @param mapModel Object of class MapHierarchyModel {@link MapHierarchyModel}
 */
	public GameWindowController(GameWindow gameWindow, int noOfPlayers, MapHierarchyModel mapModel) {
		this.gameWindow = gameWindow;
		this.noOfPlayers = noOfPlayers;
		this.mapModel = mapModel;
		initializingPlayerModels(this.noOfPlayers, this.mapModel);
	}
	
/**
 * initializingPlayerModels method
 * Void method to initialize player models as per number of players 
 * @param noOfPlayers Input the number of players in game type integer
 * @param mapModel MapHierarchyModel{@link MapHierarchyModel} object to pass map model 
 */
	private void initializingPlayerModels(int noOfPlayers, MapHierarchyModel mapModel) {

		players = new PlayerModel[noOfPlayers];
		
		for (int j = 0; j < noOfPlayers; j++) {
			int value = j + 1;
			players[j] = new PlayerModel("Player" + String.valueOf(value));
			switch(noOfPlayers) {
				case(3): {players[j].noOfArmyinPlayer(25); break;}
				case(4): {players[j].noOfArmyinPlayer(20); break;}
				case(5): {players[j].noOfArmyinPlayer(15); break;}
			}
		}

		/*
		 * randomly placing army of each player in different country by round robin
		 */
		int pickedNumber = 0;
		Random rand = new Random();


		while (!(mapModel.getCountryList().isEmpty())) {
			for (int count1 = 0; count1 < noOfPlayers; count1++) {
				if (!(mapModel.getCountryList().isEmpty())) {
					pickedNumber = rand.nextInt(mapModel.getCountryList().size());
					CountryModel countryModelTest = mapModel.getCountryList().get(pickedNumber);
					if (countryModelTest != null)
					{	players[count1].AddCountry(countryModelTest);
						players[count1].reduceArmyinPlayer();
					}
					
					System.out.println(mapModel.getCountryList().get(pickedNumber));
					mapModel.getCountryList().remove(pickedNumber);
					// h++;
					// System.out.println("size "+mapModel.getCountryModels().size());
					// System.out.println("Picked Number "+pickedNumber);
					// System.out.println(h);
				}
			}
		}
		updateUIInfo();
	}
	
/**
 * Checking method
 * To check the number of armies
 * @param selectedC type String {@link String}
 */
	public void checking(String selectedC) {
		i = 0;
//		System.out.println("Previous Counter  Value : " + prevCounter);

		while (true) {
			if (players[prevCounter].getPlayerCountryList().get(i).getCountryName().trim()
					.equalsIgnoreCase(selectedC.trim())) {
//				System.out.println("Previous Counter  Value : " + prevCounter);
//				System.out.println("Player " + prevCounter + "Armies" + players[prevCounter].getnoOfArmyinPlayer());
				if( players[prevCounter].getnoOfArmyinPlayer() > 0){	
					players[prevCounter].getPlayerCountryList().get(i).addNoOfArmiesCountry();
					players[prevCounter].reduceArmyinPlayer();
				} 
				if(players[players.length - 1].getnoOfArmyinPlayer() == 0)
				{
//					System.out.println("I'm in block");
					ReinforcementWindow reinforcementWindow = new ReinforcementWindow(mapModel, players);
					reinforcementWindow.setVisible(true);
					gameWindow.dispose();
				}
				System.out.println(
						"No. of armies" + players[prevCounter].getPlayerCountryList().get(i).getNoOfArmiesCountry());
				break;
			}
			i++;
		}
	}
/** 
 * updateUIInfo method
 * Void Method to update the window screen after any change has been made
 */
	public void updateUIInfo() {
		prevCounter = counter;
//		System.out.println(players[counter].getPlayerCountryList().get(i).getCountryName());
		gameWindow.updatePlayerLabel(players[counter].getPlayerName());
		gameWindow.updatePlayerArmies(players[counter].getnoOfArmyinPlayer());
		gameWindow.updateComboBoxCountries(players[counter].getPlayerCountryList());
		gameWindow.invalidate();
		gameWindow.revalidate();

		counter++;
		if (counter == noOfPlayers)
			counter = 0;

	}

}
