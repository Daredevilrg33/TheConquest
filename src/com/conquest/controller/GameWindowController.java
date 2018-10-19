package com.conquest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.GameWindow;
import com.conquest.window.FortificationWindow;

/**
 * Game Window Controller Class This class initializes Number of players, Player
 * model, Game window object and Map HeirarchyModel
 * 
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
	private ContinentModel[] continents;

	/**
	 * Game Window Controller Constructor Constructor created to assign value of
	 * objects
	 * 
	 * @param gameWindow  Object of class GameWindow {@link GameWindow}
	 * @param noOfPlayers Number of players in game Range from (3-5)
	 * @param mapModel    Object of class MapHierarchyModel
	 *                    {@link MapHierarchyModel}
	 */
	public GameWindowController(GameWindow gameWindow, int noOfPlayers, MapHierarchyModel mapModel) {
		this.gameWindow = gameWindow;
		this.noOfPlayers = noOfPlayers;
		this.mapModel = mapModel;
		initializingPlayerModels(this.noOfPlayers, this.mapModel);
	}

	/**
	 * initializingPlayerModels method Void method to initialize player models as
	 * per number of players
	 * 
	 * @param noOfPlayers Input the number of players in game type integer
	 * @param mapModel    MapHierarchyModel{@link MapHierarchyModel} object to pass
	 *                    map model
	 */
	private void initializingPlayerModels(int noOfPlayers, MapHierarchyModel mapModel) {

		players = new PlayerModel[noOfPlayers];
		continents = new ContinentModel[mapModel.getContinentsList().size()];

		for (int j = 0; j < noOfPlayers; j++) {
			int value = j + 1;
			players[j] = new PlayerModel("Player" + String.valueOf(value));
			switch (noOfPlayers) {
			case (3): {
				players[j].noOfArmyinPlayer(25);
				break;
			}
			case (4): {
				players[j].noOfArmyinPlayer(20);
				break;
			}
			case (5): {
				players[j].noOfArmyinPlayer(15);
				break;
			}
			}
		}

		/*
		 * randomly placing army of each player in different country by round robin
		 */
		int pickedNumber = 0;
		Random rand = new Random();
		List<CountryModel> countryModelList = new ArrayList<>();
		List<ContinentModel> continentModelList = new ArrayList<>();
		continentModelList.addAll(mapModel.getContinentsList());
		countryModelList.addAll(mapModel.getCountryList());
		while (!(countryModelList.isEmpty())) {
			for (int count1 = 0; count1 < noOfPlayers; count1++) {
				if (!(countryModelList.isEmpty())) {
					pickedNumber = rand.nextInt(countryModelList.size());
					CountryModel countryModelTest = countryModelList.get(pickedNumber);
					if (countryModelTest != null) {
						players[count1].AddCountry(countryModelTest);
//						players[count1].reduceArmyinPlayer();
					}

					System.out.println(countryModelList.get(pickedNumber).getCountryName());
					countryModelList.remove(pickedNumber);
					// h++;
					// System.out.println("size "+mapModel.getCountryModels().size());
					// System.out.println("Picked Number "+pickedNumber);
					// System.out.println(h);
				}
			}
		}
		int z=0;
//		players[0].RemoveCountry();
////		players[0].RemoveCountry();
//		players[1].RemoveCountry();
//		players[2].RemoveCountry();
//		players[0].AddCountry(mapModel.getContinentsList().get(0).getCountriesList().get(0));
//		players[0].AddCountry(mapModel.getContinentsList().get(0).getCountriesList().get(1));
//		players[1].AddCountry(mapModel.getContinentsList().get(1).getCountriesList().get(0));
//		players[2].AddCountry(mapModel.getContinentsList().get(1).getCountriesList().get(1));
		for(int j=0; j< noOfPlayers; j++) {
			for(int s = 0;s<(mapModel.getContinentsList().size());s++) {
				for(int count = 0; count < players[j].getPlayerCountryList().size();count++) {
					if(mapModel.getContinentsList().get(s).searchingCountry(players[j].getPlayerCountryList().get(count).getCountryName())) {
						{
							z++;
							System.out.println("Player  has "+players[j].getPlayerCountryList().size()+ " countries in "+mapModel.getContinentsList().get(s).getContinentName()+ " continent");
							break;
						}
					}
					if(z == mapModel.getContinentsList().size()) {
						System.out.println(mapModel.getContinentsList().get(s).getControlValue());
						for(int w =0; w<mapModel.getContinentsList().get(s).getControlValue();w++)
							players[j].addArmyinPlayer();
							z=0;
					}
				}
			}
		}
		
		
		updateUIInfo();
	}

	/**
	 * Checking method To check the number of armies
	 * 
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
				if (players[prevCounter].getnoOfArmyinPlayer() > 0) {
					players[prevCounter].getPlayerCountryList().get(i).addNoOfArmiesCountry();
					players[prevCounter].reduceArmyinPlayer();
				}
				if (players[players.length - 1].getnoOfArmyinPlayer() == 0) {
//					System.out.println("I'm in block");
					FortificationWindow reinforcementWindow = new FortificationWindow(mapModel, players);
					reinforcementWindow.setVisible(true);
//					gameWindow.dispose();
				}
				System.out.println(
						"No. of armies" + players[prevCounter].getPlayerCountryList().get(i).getNoOfArmiesCountry());
				break;
			}
			i++;
		}
	}

	/**
	 * updateUIInfo method Void Method to update the window screen after any change
	 * has been made
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
