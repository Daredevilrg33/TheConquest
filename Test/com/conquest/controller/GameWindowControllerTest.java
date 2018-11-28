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

	/** The game window 1. */
	GameWindow gameWindow1;

	/** The game window 2. */
	GameWindow gameWindow2;

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
/*   ROHIT
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		gameWindow = new GameWindow(mapHierarchyModel, "3","New Game",null);
		gameWindow1 = new GameWindow(mapHierarchyModel, "4","New Game",null);
		gameWindow2 = new GameWindow(mapHierarchyModel, "5","New Game",null);

		GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
		gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
		PlayerModel[] playerModels = gameWindowController.getPlayers();

		GameWindowController gameWindowController1 = new GameWindowController(gameWindow1, 4, mapHierarchyModel);
		gameWindowController1.initializingPlayerModels(4, mapHierarchyModel);
		PlayerModel[] playerModels1 = gameWindowController1.getPlayers();

		GameWindowController gameWindowController2 = new GameWindowController(gameWindow2, 5, mapHierarchyModel);
		gameWindowController2.initializingPlayerModels(5, mapHierarchyModel);
		PlayerModel[] playerModels2 = gameWindowController2.getPlayers();
		assertEquals(23, playerModels[1].getnoOfArmyInPlayer());
		assertEquals(18, playerModels1[1].getnoOfArmyInPlayer());
		assertEquals(14, playerModels2[1].getnoOfArmyInPlayer());
*/
	}

	/**
	 * No of armies a player has after first reinforcement phase ie the minimum reinforcement 
	 * value gets added .
	 
	 */
	@Test
	public void noOfArmyAfterTest() {

	/*	ROHIT
	 * MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		gameWindow = new GameWindow(mapHierarchyModel, "3","New Game",null);
		GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
		gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
		PlayerModel[] playerModels = gameWindowController.getPlayers();

		playerModels[1].calculateAndAddReinforcementArmy();

		assertEquals(26, playerModels[1].getnoOfArmyInPlayer());*/
	}

	/**
	 * No of armies a player has after reinforcement phase depending on the number
	 * of countries he has.
	 * 
	 */
	@Test
	public void noOfArmyCountryTest() {
			// ROHIT
		/*MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		gameWindow = new GameWindow(mapHierarchyModel, "3","New Game",null);
		GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
		gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
		PlayerModel[] playerModels = gameWindowController.getPlayers();
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
		assertEquals(27, playerModels[1].getnoOfArmyInPlayer());*/
	}
	
	
	/**
	 * No of armies a player has after reinforcement phase if the player controls the whole continent.
	 * 
	 */
	@Test
	public void noOfArmyCountry2Test() {

//		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(Continent_Value);
//		gameWindow = new GameWindow(mapHierarchyModel, "3","New Game",null);
//		GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
//		gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
//		PlayerModel[] playerModels = gameWindowController.getPlayers();
//		ArrayList<CountryModel> countryModelArrayList = new ArrayList<CountryModel>();
//		CountryModel countrymodel = new CountryModel("BOSTON");
//		CountryModel countrymodel1 = new CountryModel("CALIFORNIA");
//		CountryModel countrymodel2 = new CountryModel("BRAZIL");
//		
//		ArrayList<CountryModel> countryModelArrayList1 = new ArrayList<CountryModel>();
//
//		CountryModel countrymodel3 = new CountryModel("LONDON");
//		CountryModel countrymodel4 = new CountryModel("PARIS");
//		
//		ArrayList<CountryModel> countryModelArrayList2 = new ArrayList<CountryModel>();
//
//		CountryModel countrymodel6 = new CountryModel("SCOTLAND");
//		
//		playerModels[1].removeAllCountry();
//		playerModels[0].removeAllCountry();
//		playerModels[2].removeAllCountry();
//		playerModels[1].noOfArmyInPlayer(25);
//		playerModels[2].noOfArmyInPlayer(25);
//		playerModels[0].noOfArmyInPlayer(25);
//
//
//		playerModels[1].addCountry(countrymodel);
//
//		playerModels[1].addCountry(countrymodel1);
//		playerModels[1].addCountry(countrymodel2);
//		playerModels[0].addCountry(countrymodel3);
//		playerModels[0].addCountry(countrymodel4);
//		
//		playerModels[2].addCountry(countrymodel6);
//		
//		System.out.println("Country NAME: " + playerModels[1].getPlayerName());
//		System.out.println("Country List Size: " + playerModels[1].getPlayerCountryList().size());
//		System.out.println("player no of army: " + playerModels[2].getnoOfArmyInPlayer());
//		playerModels[1].calculateAndAddReinforcementArmy();
//		playerModels[0].calculateAndAddReinforcementArmy();
//		//assertEquals(26, playerModels[0].getnoOfArmyInPlayer());
//		//assertEquals(27, playerModels[1].getnoOfArmyInPlayer());
	}
	
	
	
	
}