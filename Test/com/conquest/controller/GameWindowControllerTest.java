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

// TODO: Auto-generated Javadoc
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
	private static String CONTINENT_VALUE;

	/** The accbb map file path. */
	private static String ACCBB_MAP;

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
		CONTINENT_VALUE = System.getProperty("user.dir") + "\\resources\\testresource\\CONTINENTVALUETEST.map";
		ACCBB_MAP = System.getProperty("user.dir") + "\\resources\\testresource\\ACCBB.map";

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
		CountryModel countryModel = new CountryModel("RUSSIA12");
		CountryModel countryModel1 = new CountryModel("AMERICA31");
		CountryModel countryModel2 = new CountryModel("JAPAN12");
		CountryModel countryModel3 = new CountryModel("FRANCE43");
		CountryModel countryModel4 = new CountryModel("Sri Lanka");
		CountryModel countryModel5 = new CountryModel("Australia");
		CountryModel countryModel6 = new CountryModel("United Kingdom");
		CountryModel countryModel7 = new CountryModel("Germany");
		CountryModel countryModel8 = new CountryModel("Crotia");
		CountryModel countryModel9 = new CountryModel("Netherlands");
		CountryModel countryModel10 = new CountryModel("New Zealand");

		playerModels[1].addCountry(countryModel);
		playerModels[1].addCountry(countryModel1);
		playerModels[1].addCountry(countryModel2);
		playerModels[1].addCountry(countryModel3);
		playerModels[1].addCountry(countryModel4);
		playerModels[1].addCountry(countryModel5);
		playerModels[1].addCountry(countryModel6);
		playerModels[1].addCountry(countryModel7);
		playerModels[1].addCountry(countryModel8);
		playerModels[1].addCountry(countryModel9);
		playerModels[1].addCountry(countryModel10);
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

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(CONTINENT_VALUE);
		String[] playerTypes = new String[] { "Human", "Human", "Human" };
		NewGameMenuScreen newGameMenuScreen = new NewGameMenuScreen();
		PlayerModel[] playerModels = newGameMenuScreen.initializingPlayerModels(3, mapHierarchyModel, playerTypes);
		ArrayList<CountryModel> countryModelArrayList = new ArrayList<CountryModel>();
		CountryModel countryModel = new CountryModel("BOSTON");
		CountryModel countryModel1 = new CountryModel("CALIFORNIA");
		CountryModel countryModel2 = new CountryModel("BRAZIL");

		ArrayList<CountryModel> countryModelArrayList1 = new ArrayList<CountryModel>();

		CountryModel countryModel3 = new CountryModel("LONDON");
		CountryModel countryModel4 = new CountryModel("PARIS");

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

		playerModels[1].addCountry(countryModel);

		playerModels[1].addCountry(countryModel1);
		playerModels[1].addCountry(countryModel2);
		playerModels[0].addCountry(countryModel3);
		playerModels[0].addCountry(countryModel4);

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
	
	/**
	 * save test check.
	 */
	@Test
	public void saveTest() {
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		NewGameMenuScreen newGameMenuScreen = new NewGameMenuScreen();
		String[] playerTypes = new String[] { "Human", "Human", "Human" };

		PlayerModel[] playerModels = newGameMenuScreen.initializingPlayerModels(3, mapHierarchyModel, playerTypes);
		GameModel gameModel = new GameModel(mapHierarchyModel, playerModels);
		gameWindow = new GameWindow(gameModel);
		GameWindowController gameWindowController= new GameWindowController(gameWindow, gameModel);
		System.out.println(gameWindowController.saveGame(gameModel));
		assertEquals("true",String.valueOf(gameWindowController.saveGame(gameModel)));
		
	}
	
	
}