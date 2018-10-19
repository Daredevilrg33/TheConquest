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
	private MapHierarchyModel mapHierarchyModel;
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
		this.mapHierarchyModel = mapModel;
		initializingPlayerModels(this.noOfPlayers, this.mapHierarchyModel);
	}

	/**
	 * initializingPlayerModels method Void method to initialize player models as
	 * per number of players
	 * 
	 * @param noOfPlayers       Input the number of players in game type integer
	 * @param mapHierarchyModel MapHierarchyModel{@link MapHierarchyModel} object to
	 *                          pass map model
	 */
	public void initializingPlayerModels(int noOfPlayers, MapHierarchyModel mapHierarchyModel) {

		players = new PlayerModel[noOfPlayers];
		continents = new ContinentModel[mapHierarchyModel.getContinentsList().size()];

		for (int j = 0; j < noOfPlayers; j++) {
			int value = j + 1;
			players[j] = new PlayerModel("Player" + String.valueOf(value));
			switch (noOfPlayers) {
			case (3): {
				players[j].noOfArmyInPlayer(25);
				break;
			}
			case (4): {
				players[j].noOfArmyInPlayer(20);
				break;
			}
			case (5): {
				players[j].noOfArmyInPlayer(15);
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
		continentModelList.addAll(mapHierarchyModel.getContinentsList());
		countryModelList.addAll(mapHierarchyModel.getCountryList());
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
					
				}
			}
		}

		isControlValueTobeAdded(this.mapHierarchyModel,players);
		updateUIInfo();
	}

	public void isControlValueTobeAdded(MapHierarchyModel mapHierarchyModel, PlayerModel[] playerModels) {
		int z = 0;
		for (int i = 0; i < playerModels.length; i++) {
			PlayerModel playerModel = playerModels[i];
			for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
				z = 0;
				for (CountryModel countryModel : continentModel.getCountriesList()) {
					
					CountryModel value = playerModel.searchCountry(countryModel.getCountryName());
					if (value == null) {
						System.out.println("Country is not availaible.");
						break;
					} else {
						z++;
						System.out.println("Country is  availaible.");
						if (z == continentModel.getCountriesList().size()) {
							playerModel.addControlValueToNoOfArmy(continentModel.getControlValue());
							z = 0;
						}
					}
				}
			}
		}
	}

	/**
	 * Checking method To check the number of armies
	 * 
	 * @param selectedC type String {@link String}
	 */
	public void checking(String selectedC) {
		i = 0;


		while (true) {
			if (players[prevCounter].getPlayerCountryList().get(i).getCountryName().trim()
					.equalsIgnoreCase(selectedC.trim())) {
				if (players[prevCounter].getnoOfArmyInPlayer() > 0) {
					players[prevCounter].getPlayerCountryList().get(i).addNoOfArmiesCountry();
					players[prevCounter].reduceArmyInPlayer();
				}
				if (players[players.length - 1].getnoOfArmyInPlayer() == 0) {
					FortificationWindow reinforcementWindow = new FortificationWindow(mapHierarchyModel, players);
					reinforcementWindow.setVisible(true);
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
		gameWindow.updatePlayerLabel(players[counter].getPlayerName());
		gameWindow.updatePlayerArmies(players[counter].getnoOfArmyInPlayer());
		gameWindow.updateComboBoxCountries(players[counter].getPlayerCountryList());
		gameWindow.invalidate();
		gameWindow.revalidate();

		counter++;
		if (counter == noOfPlayers)
			counter = 0;

	}

}
