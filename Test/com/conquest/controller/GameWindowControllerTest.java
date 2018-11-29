package com.conquest.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.model.GameModel;
import com.conquest.model.PlayerModel;
import com.conquest.utilities.Utility;
import com.conquest.window.GameWindow;
import com.conquest.window.NewGameMenuScreen;

/**
 * The Class GameWindowControllerTest.
 */
public class GameWindowControllerTest {

	/** The utility object. */
	private Utility utility = new Utility();

	/** The country model object. */
	private ContinentModel countryModel = new ContinentModel("AUSTRALIA");

	/** The Asia map file path. */
	private static String ASIA_MAP_FILE_PATH;

	/** The Asia map file path. */
	private static String Continent_Value;

	private static String ACCBB;

	/** The game window object. */
	GameWindow gameWindow;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		ASIA_MAP_FILE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
		Continent_Value = System.getProperty("user.dir") + "\\resources\\testresource\\CONTINENTVALUETEST.map";
		ACCBB = System.getProperty("user.dir") + "\\resources\\testresource\\ACCBB.map";

	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * no of armies a player have at the time of startup.
	 */
	@Test
	public void noOfArmyTest() {
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		String[] playerTypes = new String[] { "Human", "Human", "Human", "Human", "Human" };
		NewGameMenuScreen newGameMenuScreen = new NewGameMenuScreen();
		System.out.println("Player Typesjnkjnskjnsk: " + playerTypes.length);
		PlayerModel[] playerModels = newGameMenuScreen.initializingPlayerModels(3, mapHierarchyModel, playerTypes);
		System.out.println("Player Types " + playerTypes.length);
		PlayerModel[] playerModels1 = newGameMenuScreen.initializingPlayerModels(4, mapHierarchyModel, playerTypes);
		PlayerModel[] playerModels2 = newGameMenuScreen.initializingPlayerModels(5, mapHierarchyModel, playerTypes);
		assertEquals(23, playerModels[1].getnoOfArmyInPlayer());
		assertEquals(18, playerModels1[1].getnoOfArmyInPlayer());
		assertEquals(14, playerModels2[1].getnoOfArmyInPlayer());
	}

	/**
	 * No of armies a player has after first reinforcement phase ie the minimum
	 * reinforcement value gets added .
	 * 
	 */
	@Test
	public void noOfArmyAfterTest() {

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		String[] playerTypes = new String[] { "Human", "Human", "Human" };
		NewGameMenuScreen newGameMenuScreen = new NewGameMenuScreen();
		PlayerModel[] playerModels = newGameMenuScreen.initializingPlayerModels(3, mapHierarchyModel, playerTypes);

		playerModels[1].calculateAndAddReinforcementArmy();

		assertEquals(26, playerModels[1].getnoOfArmyInPlayer());
	}

	/**
	 * No of armies a player has after reinforcement phase depending on the number
	 * of countries he has.
	 * 
	 */
	@Test
	public void noOfArmyCountryTest() {

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		String[] playerTypes = new String[] { "Human", "Human", "Human" };
		NewGameMenuScreen newGameMenuScreen = new NewGameMenuScreen();
		PlayerModel[] playerModels = newGameMenuScreen.initializingPlayerModels(3, mapHierarchyModel, playerTypes);
		ArrayList<CountryModel> countryModelArrayList = new ArrayList<CountryModel>();
		CountryModel countrymodel = new CountryModel("RUSSIA12");
		CountryModel countrymodel1 = new CountryModel("AMERICA31");
		CountryModel countrymodel2 = new CountryModel("JAPAN12");
		CountryModel countrymodel3 = new CountryModel("FRANCE43");
		CountryModel countrymodel4 = new CountryModel("Sri Lanka");
		CountryModel countrymodel5 = new CountryModel("Australia");
		CountryModel countrymodel6 = new CountryModel("United Kingdom");
		CountryModel countrymodel7 = new CountryModel("Germany");
		CountryModel countrymodel8 = new CountryModel("Crotia");
		CountryModel countrymodel9 = new CountryModel("Netherlands");
		CountryModel countrymodel10 = new CountryModel("New Zealand");

		playerModels[1].addCountry(countrymodel);
		playerModels[1].addCountry(countrymodel1);
		playerModels[1].addCountry(countrymodel2);
		playerModels[1].addCountry(countrymodel3);
		playerModels[1].addCountry(countrymodel4);
		playerModels[1].addCountry(countrymodel5);
		playerModels[1].addCountry(countrymodel6);
		playerModels[1].addCountry(countrymodel7);
		playerModels[1].addCountry(countrymodel8);
		playerModels[1].addCountry(countrymodel9);
		playerModels[1].addCountry(countrymodel10);
//		System.out.println("Country NAME: " + playerModels[1].getPlayerName());
//		System.out.println("Country List Size: " + playerModels[0].getPlayerCountryList().size());
//		System.out.println("player no of army: " + playerModels[0].getnoOfArmyInPlayer());
		playerModels[1].calculateAndAddReinforcementArmy();
		playerModels[0].calculateAndAddReinforcementArmy();
		assertEquals(26, playerModels[0].getnoOfArmyInPlayer());
		assertEquals(27, playerModels[1].getnoOfArmyInPlayer());
	}

	/**
	 * No of armies a player has after reinforcement phase if the player controls
	 * the whole continent.
	 * 
	 */
	@Test
	public void noOfArmyCountry2Test() {

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(Continent_Value);
		String[] playerTypes = new String[] { "Human", "Human", "Human" };
		NewGameMenuScreen newGameMenuScreen = new NewGameMenuScreen();
		PlayerModel[] playerModels = newGameMenuScreen.initializingPlayerModels(3, mapHierarchyModel, playerTypes);
		ArrayList<CountryModel> countryModelArrayList = new ArrayList<CountryModel>();
		CountryModel countrymodel = new CountryModel("BOSTON");
		CountryModel countrymodel1 = new CountryModel("CALIFORNIA");
		CountryModel countrymodel2 = new CountryModel("BRAZIL");

		ArrayList<CountryModel> countryModelArrayList1 = new ArrayList<CountryModel>();

		CountryModel countrymodel3 = new CountryModel("LONDON");
		CountryModel countrymodel4 = new CountryModel("PARIS");

		ArrayList<CountryModel> countryModelArrayList2 = new ArrayList<CountryModel>();

		CountryModel countrymodel6 = new CountryModel("SCOTLAND");

		for (CountryModel country : playerModels[1].getPlayerCountryList()) {
			playerModels[1].addArmyInPlayer();
		}
		for (CountryModel country : playerModels[0].getPlayerCountryList()) {
			playerModels[0].addArmyInPlayer();
		}
		for (CountryModel country : playerModels[2].getPlayerCountryList()) {
			playerModels[2].addArmyInPlayer();
		}
		playerModels[1].removeAllCountry();
		playerModels[0].removeAllCountry();
		playerModels[2].removeAllCountry();
//		playerModels[1].noOfArmyInPlayer(25);
//		playerModels[2].noOfArmyInPlayer(25);
//		playerModels[0].noOfArmyInPlayer(25);

		playerModels[1].addCountry(countrymodel);

		playerModels[1].addCountry(countrymodel1);
		playerModels[1].addCountry(countrymodel2);
		playerModels[0].addCountry(countrymodel3);
		playerModels[0].addCountry(countrymodel4);

		playerModels[2].addCountry(countrymodel6);
		for (CountryModel country : playerModels[1].getPlayerCountryList()) {
			playerModels[1].reduceArmyInPlayer();
		}
		for (CountryModel country : playerModels[0].getPlayerCountryList()) {
			playerModels[0].reduceArmyInPlayer();
		}
		for (CountryModel country : playerModels[2].getPlayerCountryList()) {
			playerModels[2].reduceArmyInPlayer();
		}

		System.out.println("Country NAME: " + playerModels[1].getPlayerName());
		System.out.println("Country List Size: " + playerModels[1].getPlayerCountryList().size());
		System.out.println("player no of army: " + playerModels[2].getnoOfArmyInPlayer());
		GameModel gameModel = new GameModel(mapHierarchyModel, playerModels);
		gameWindow = new GameWindow(gameModel);
		GameWindowController gameWindowController = new GameWindowController(gameWindow, gameModel);
		playerModels[1].calculateAndAddReinforcementArmy();
		playerModels[0].calculateAndAddReinforcementArmy();
		gameWindowController.isControlValueTobeAdded(mapHierarchyModel, playerModels[0]);
		gameWindowController.isControlValueTobeAdded(mapHierarchyModel, playerModels[1]);
		assertEquals(26, playerModels[0].getnoOfArmyInPlayer());
		assertEquals(28, playerModels[1].getnoOfArmyInPlayer());
	}

}