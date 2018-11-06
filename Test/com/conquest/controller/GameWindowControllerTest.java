package com.conquest.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.model.GameModel;
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
	private static String asiaMapFilePath;
	
	/** The game window object. */
	GameWindow gameWindow;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		asiaMapFilePath = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
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

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		gameWindow = new GameWindow(mapHierarchyModel, "3");
		GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
		gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
		PlayerModel[] playerModels = gameWindowController.getPlayers();
		System.out.println("Country List Size: " + playerModels[1].getPlayerCountryList().size());
		System.out.println("player no of army: " + playerModels[1].getnoOfArmyInPlayer());

		assertEquals(25,playerModels[1].getnoOfArmyInPlayer());
	}
/**
 * No of armies a player has after first reinforcement phase depending on the number of countries he has.
 * 
 */
	@Test
	public void noOfArmyAfterTest() {

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		gameWindow = new GameWindow(mapHierarchyModel, "3");
		GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
		gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
		PlayerModel[] playerModels = gameWindowController.getPlayers();
		System.out.println("Country List Size: " + playerModels[1].getPlayerCountryList().size());
		System.out.println("player no of army: " + playerModels[1].getnoOfArmyInPlayer());
		playerModels[1].calculateAndAddReinforcementArmy();

		assertEquals(28,playerModels[1].getnoOfArmyInPlayer());
	}
	
	/**
	 * No of armies a player has after reinforcement phase depending on the number of countries he has.
	 * 
	 */
		@Test
		public void noOfArmyCountryTest() {

			MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
			gameWindow = new GameWindow(mapHierarchyModel, "3");
			GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
			gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
			PlayerModel[] playerModels = gameWindowController.getPlayers();
			 ArrayList<CountryModel> countryModelArrayList = new  ArrayList<CountryModel>();
			CountryModel countrymodel = new CountryModel("RUSSIA");
			CountryModel countrymodel1 = new CountryModel("AMERICA");
			CountryModel countrymodel2 = new CountryModel("JAPAN");
			CountryModel countrymodel3 = new CountryModel("FRANCE");
			
			PlayerModel pm=new PlayerModel("Player2",countryModelArrayList);
			pm.AddCountry(countrymodel);
			pm.AddCountry(countrymodel1);
			pm.AddCountry(countrymodel2);
			pm.AddCountry(countrymodel3);
			
			System.out.println("Country NAME: " + playerModels[1].getPlayerName());
			System.out.println("Country List Size: " + playerModels[1].getPlayerCountryList().size());
			System.out.println("player no of army: " + playerModels[1].getnoOfArmyInPlayer());
			playerModels[1].calculateAndAddReinforcementArmy();

			assertEquals(28,playerModels[1].getnoOfArmyInPlayer());
		}
		
}

