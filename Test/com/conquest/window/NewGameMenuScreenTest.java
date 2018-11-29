package com.conquest.window;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.utilities.Utility;

/**
 * The Class NewGameMenuScreenTest.
 */
public class NewGameMenuScreenTest {

	/** The utility. */
	private Utility utility = new Utility();

	/** The Asia map file path. */
	private static String ASIA_MAP_FILE_PATH;

	/** The threemap file path. */
	private static String THREE_MAP_FILE_PATH;

	/** The country connected map file path. */
	private static String COUNTRY_CONNECTED_PATH;

	/** The country not connected map file path. */
	private static String COUNTRY_NOT_CONNECTED_PATH;
	
	/** The country not connected map file path. */
	private static String Twin_Volcano_PATH;
	
	/** The country not connected map file path. */
	private static String Cliff_PATH;

	/** The game window. */
	GameWindow gameWindow;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		ASIA_MAP_FILE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
		THREE_MAP_FILE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\three.map";
		COUNTRY_CONNECTED_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\countryconnected.map";
		COUNTRY_NOT_CONNECTED_PATH = System.getProperty("user.dir")+ "\\resources\\testresource\\countrynotconnected.map";
		Twin_Volcano_PATH = System.getProperty("user.dir")+ "\\resources\\testresource\\Twin Volcano.map";
		Cliff_PATH = System.getProperty("user.dir")+ "\\resources\\testresource\\3D Cliff.map";
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
	 * startup phase test.
	 */
	@Test
	public void checkValidationTest() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		System.out.println(newGame.checkValidation(mapHierarchyModel, 5));
		assertEquals("Passed", String.valueOf(newGame.checkValidation(mapHierarchyModel, 5)));
	}

	/**
	 * startup phase test. test having 5 players and 4 countries.
	 */
	@Test
	public void checkValidation1Test() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(THREE_MAP_FILE_PATH);
		System.out.println(newGame.checkValidation(mapHierarchyModel, 5));
		System.out.println(mapHierarchyModel.errorMsg);
		assertEquals("Number of countries cannot be less than number of players", mapHierarchyModel.errorMsg);
	}

	/**
	 * test to check whether all the countries are connected or not.
	 */
	@Test
	public void countriesconnectedTest() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(COUNTRY_CONNECTED_PATH);
		newGame.isMapConnected(mapHierarchyModel);
		System.out.println(mapHierarchyModel.getErrorMsg());
		System.out.println(mapHierarchyModel.isValErrorFlag());
		assertEquals("false", String.valueOf(mapHierarchyModel.isValErrorFlag()));

	}

	/**
	 * test to that all countries are not connected.
	 */
	@Test
	public void countriesconnected2Test() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(COUNTRY_NOT_CONNECTED_PATH);
		newGame.isMapConnected(mapHierarchyModel);
		System.out.println(mapHierarchyModel.getErrorMsg());
		System.out.println(mapHierarchyModel.isValErrorFlag());
		assertEquals("Map is not connected !!", mapHierarchyModel.getErrorMsg());
		assertEquals("true", String.valueOf(mapHierarchyModel.isValErrorFlag()));
	}

	/**
	 * test to check whether all the continents are connected or not.
	 */
	@Test
	public void continentsconnectedTest() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		boolean isContinentConnected = newGame.isContinentConnected(mapHierarchyModel);

		assertEquals("true", String.valueOf(isContinentConnected));
	}

	/**
	 * test to to show that all the continents are not connected.
	 */
	@Test
	public void continentsnotconnectedTest() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(COUNTRY_NOT_CONNECTED_PATH);
		boolean isContinentConnected = newGame.isContinentConnected(mapHierarchyModel);

		assertEquals("false", String.valueOf(isContinentConnected));
	}
	
	/**
	 * test to check twin volcano map.
	 */
	@Test
	public void twinVolcanoTest() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(Twin_Volcano_PATH);
		newGame.isMapConnected(mapHierarchyModel);
		
		assertEquals("Map is invalid", mapHierarchyModel.getErrorMsg());


	}
	
	/**
	 * test to check #D CLIFF map.
	 */
	@Test
	public void cliffMapTest() {
		NewGameMenuScreen newGame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(Cliff_PATH);
		newGame.isMapConnected(mapHierarchyModel);
		System.out.println(mapHierarchyModel.getErrorMsg());
		System.out.println(mapHierarchyModel.isValErrorFlag());
//		assertEquals("false", String.valueOf(mapHierarchyModel.isValErrorFlag()));
//		assertEquals("Map is invalid", mapHierarchyModel.getErrorMsg());


	}
	
}
