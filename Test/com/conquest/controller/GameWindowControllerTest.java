package com.conquest.controller;

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

	@Test
	public void test() {
		// fail("Not yet implemented");

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		gameWindow = new GameWindow(mapHierarchyModel, "3");
		GameWindowController gamewindow = new GameWindowController(gameWindow, 3, mapHierarchyModel);
		gamewindow.initializingPlayerModels(3, mapHierarchyModel);
		PlayerModel[] playerModels = gamewindow.getPlayers();
		System.out.println("Country List Size: " + playerModels[1].getPlayerCountryList().size() );
	}

}
