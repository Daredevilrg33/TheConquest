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

	/** The threemap filepath. */
	private static String THREE_MAP_FILE_PATH;
	
	/** The countryconnectedmap filepath. */
	private static String COUNTRY_CONNECTED_PATH;
	
	/** The countrynotconnectedmap filepath. */
	private static String COUNTRY_NOT_CONNECTED_PATH;
	
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
		THREE_MAP_FILE_PATH= System.getProperty("user.dir") + "\\resources\\testresource\\three.map";
		COUNTRY_CONNECTED_PATH= System.getProperty("user.dir") + "\\resources\\testresource\\countryconnected.map";
		COUNTRY_NOT_CONNECTED_PATH= System.getProperty("user.dir") + "\\resources\\testresource\\countrynotconnected.map";
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
		NewGameMenuScreen newgame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		System.out.println(newgame.checkValidation(mapHierarchyModel, 5));
		assertEquals("Passed", String.valueOf(newgame.checkValidation(mapHierarchyModel, 5)));
	}

	/**
	 * startup phase test. test having 5 players and 4 countries.
	 */
	@Test
	public void checkValidation1Test() {
		NewGameMenuScreen newgame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(THREE_MAP_FILE_PATH);
		System.out.println(newgame.checkValidation(mapHierarchyModel, 5));
		System.out.println(mapHierarchyModel.errorMsg);
		assertEquals("Number of countries cannot be less than number of players", mapHierarchyModel.errorMsg);
	}
	/**
	 * test to check whether all the countries are connected or not.
	 */
	@Test
	public void countriesconnectedTest() {
		NewGameMenuScreen newgame=new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(COUNTRY_CONNECTED_PATH);
		newgame.isMapConnected(mapHierarchyModel);
		System.out.println(mapHierarchyModel.getErrorMsg());
		System.out.println(mapHierarchyModel.isValErrorFlag());
//		assertEquals("Map is valid",mapHierarchyModel.getErrorMsg());
		assertEquals("false",String.valueOf(mapHierarchyModel.isValErrorFlag()));
		
	}
	
	/**
	 * test to check whether all the countries are connected or not.
	 */
	@Test
	public void countriesconnected2Test() {
		NewGameMenuScreen newgame=new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(COUNTRY_NOT_CONNECTED_PATH);
		newgame.isMapConnected(mapHierarchyModel);
		System.out.println(mapHierarchyModel.getErrorMsg());
		System.out.println(mapHierarchyModel.isValErrorFlag());
		assertEquals("Map is not connected !!",mapHierarchyModel.getErrorMsg());
		assertEquals("true",String.valueOf(mapHierarchyModel.isValErrorFlag()));
	}

	/**
	 * test to check whether all the continents are connected or not.
	 */
	@Test
	public void continentsconnectedTest() {
		NewGameMenuScreen newgame=new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		boolean isContinentConnected = newgame.isContinentConnected(mapHierarchyModel);
		
		assertEquals("true",String.valueOf(isContinentConnected));

		
	}
	
	/**
	 * test to check whether all the continents are connected or not.
	 */
	@Test
	public void continentsnotconnectedTest() {
		NewGameMenuScreen newgame=new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(COUNTRY_NOT_CONNECTED_PATH);
		boolean isContinentConnected = newgame.isContinentConnected(mapHierarchyModel);
		
		assertEquals("false",String.valueOf(isContinentConnected));

		
	}
	
	
	
}
