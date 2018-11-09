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
	private static String asiaMapFilePath;

	/** The threemap filepath. */
	private static String threemapFilepath;
	
	/** The countryconnectedmap filepath. */
	private static String countryconnectedpath;
	
	/** The countrynotconnectedmap filepath. */
	private static String countrynotconnectedpath;
	
	/** The game window. */
	GameWindow gameWindow;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		asiaMapFilePath = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
//		threemapFilepath = System.getProperty("user.dir") + "\\resources\\testresource\\three.map";
		threemapFilepath= System.getProperty("user.dir") + "\\resources\\testresource\\three.map";
		countryconnectedpath= System.getProperty("user.dir") + "\\resources\\testresource\\countryconnected.map";
		countrynotconnectedpath= System.getProperty("user.dir") + "\\resources\\testresource\\countrynotconnected.map";
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
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		System.out.println(newgame.checkValidation(mapHierarchyModel, 5));
		assertEquals("Passed", String.valueOf(newgame.checkValidation(mapHierarchyModel, 5)));
	}

	/**
	 * startup phase test. test having 5 players and 4 countries.
	 */
	@Test
	public void checkValidation1Test() {
		NewGameMenuScreen newgame = new NewGameMenuScreen();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(threemapFilepath);
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
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(countryconnectedpath);
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
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(countrynotconnectedpath);
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
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		boolean isContinentConnected = newgame.isContinentConnected(mapHierarchyModel);
		
		assertEquals("true",String.valueOf(isContinentConnected));

		
	}
	
	
}
