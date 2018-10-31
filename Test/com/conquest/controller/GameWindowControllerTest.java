package com.conquest.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.utilities.Utility;
import com.conquest.window.GameWindow;

public class GameWindowControllerTest {
	private Utility utility = new Utility();
	/** The asia map file path. */
	private static String asiaMapFilePath;
	GameWindow gameWindow;

	@Before
	public void setUp() throws Exception {
		asiaMapFilePath = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * no of armies a player have at the time of startup
	 */
	@Test
	public void noOfArmyTest() {
		// fail("Not yet implemented");

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
		// fail("Not yet implemented");

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		gameWindow = new GameWindow(mapHierarchyModel, "3");
		GameWindowController gameWindowController = new GameWindowController(gameWindow, 3, mapHierarchyModel);
		gameWindowController.initializingPlayerModels(3, mapHierarchyModel);
		PlayerModel[] playerModels = gameWindowController.getPlayers();
		System.out.println("Country List Size: " + playerModels[1].getPlayerCountryList().size());
		System.out.println("player no of army: " + playerModels[1].getnoOfArmyInPlayer());
		gameWindowController.calculateAndAddReinforcementArmy(playerModels);

		
		assertEquals(28,playerModels[1].getnoOfArmyInPlayer());
	}
	
}
