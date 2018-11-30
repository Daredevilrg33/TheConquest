package com.conquest.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.model.AggresivePlayer;
import com.conquest.model.CheaterPlayer;
import com.conquest.model.GameModel;
import com.conquest.model.HumanPlayer;
import com.conquest.model.PlayerModel;
import com.conquest.model.PlayerType;
import com.conquest.utilities.Utility;
import com.conquest.window.NewTournamentMenuScreen;

public class TournamentControllerTest {

	public static String THREE_PATH;
	
	private Utility utility = new Utility();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		THREE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\three.map";

	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
/**
 * test for checking tournament mode.
 */
	@Test
	public void startTournamentTest() {
		TournamentController tournamentController= new TournamentController(1, 1, 50);
		NewTournamentMenuScreen newTournamentMenuScreen = new NewTournamentMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(THREE_PATH);
		PlayerModel[] playerModels = newTournamentMenuScreen.initializingPlayerModels(2, mapHierarchyModel, new String[] {"Cheater","Aggresive"});
		
		GameModel gameModel= new GameModel(mapHierarchyModel, playerModels);
		tournamentController.startTournament(gameModel);
		System.out.println("jjjjjjjjj" + newTournamentMenuScreen.getResults().toString());
		
	}

}
