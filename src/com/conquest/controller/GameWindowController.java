package com.conquest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.model.GameModel;
import com.conquest.window.GameWindow;
import com.conquest.window.FortificationWindow;

/**
 * Game Window Controller Class This class initializes Number of players, Player
 * model, Game window object and Map HeirarchyModel.
 *
 * @author Rohit Gupta
 * @version 1.0.0
 */
public class GameWindowController {

	/** The game window. */
	private GameWindow gameWindow;

	/** The no of players. */
	private int noOfPlayers = 0;

	/** The map hierarchy model. */
	private MapHierarchyModel mapHierarchyModel;

	/** The counter. */
	private int counter = 0;

	/** The players. */
	private PlayerModel[] players;

	/** The previous counter. */
	private int prevCounter = 0;

	/** The continents. */
	private ContinentModel[] continents;

	/** The game model. */
	private GameModel gameModel;

	/**
	 * Game Window Controller Constructor Constructor created to assign value of
	 * objects.
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

		this.gameModel = new GameModel(mapHierarchyModel, players);
		providingGameModelToPlayer();
	}

	/**
	 * initializingPlayerModels method Void method to initialize player models as
	 * per number of players.
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
			players[j] = new PlayerModel("Player" + String.valueOf(value), gameModel);

			switch (noOfPlayers) {
			case (3):
				players[j].noOfArmyInPlayer(25);
				break;

			case (4):
				players[j].noOfArmyInPlayer(20);
				break;

			case (5):
				players[j].noOfArmyInPlayer(15);
				break;

			}
			players[j].addObserver(gameWindow);

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
						players[count1].addCountry(countryModelTest);
						countryModelTest.addNoOfArmiesCountry();
						players[count1].reduceArmyInPlayer();

					}
					System.out.println(countryModelList.get(pickedNumber).getCountryName());
					countryModelList.remove(pickedNumber);
				}
			}
		}

	}

	/**
	 * Checks if is control value to be added.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param playerModels      the player models
	 */
	public void isControlValueTobeAdded(MapHierarchyModel mapHierarchyModel, PlayerModel[] playerModels) {
		int z = 0;
		for (int i = 0; i < playerModels.length; i++) {
			PlayerModel playerModel = playerModels[i];
			for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
				z = 0;
				for (CountryModel countryModel : continentModel.getCountriesList()) {
					CountryModel value = playerModel.searchCountry(countryModel.getCountryName());
					if (value == null) {
						break;
					} else {
						z++;
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
	 * Checking method To check the number of armies.
	 *
	 * @param selectedCountryName type String {@link String}
	 */
	public void placingInitialArmies(String selectedCountryName, PlayerModel currentPlayer) {

		if (currentPlayer.getnoOfArmyInPlayer() > 0) {
			currentPlayer.searchCountry(selectedCountryName).addNoOfArmiesCountry();
			currentPlayer.reduceArmyInPlayer();
		}

	}

	/**
	 * Checking method To check the number of armies.
	 *
	 * @param selectedCountryName type String {@link String}
	 */
	public void placeReinforcedArmy(String selectedCountryName, PlayerModel currentPlayer) {

		if (currentPlayer.getnoOfArmyInPlayer() > 0) {
			currentPlayer.searchCountry(selectedCountryName).addNoOfArmiesCountry();
			currentPlayer.reduceArmyInPlayer();
		}
		if (currentPlayer.getnoOfArmyInPlayer() == 0) {
			currentPlayer.setGameWindow(gameWindow);
			currentPlayer.AttackPhase();
		}

	}

	public void providingGameModelToPlayer() {
		for (PlayerModel player : players) {
			player.setGameModel(gameModel);
		}
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public PlayerModel[] getPlayers() {
		return players;
	}

	/**
	 * @return the gameModel
	 */
	public GameModel getGameModel() {
		return gameModel;
	}
}
