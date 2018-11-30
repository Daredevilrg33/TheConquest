package com.conquest.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;

/**
 * The Class UtilityTest.
 */
public class UtilityTest {

	/** The utility. */
	private Utility utility = new Utility();

	/** The asia map file path. */
	private static String ASIA_MAP_FILE_PATH;

	/** The asia invalid map file path. */
	private static String ASIA_INVALID_MAP_FILE_PATH;
	/** The asia invalid map file path. */
	private static String NO_PATH;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ASIA_MAP_FILE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
		ASIA_INVALID_MAP_FILE_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\Asiainvalid.map";
		NO_PATH = System.getProperty("user.dir") + "\\resources\\testresource\\noneighbours.map";

	}

	/**
	 * Tear down after class.
	 *
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {

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
	 * First it parses the map and then extracts the list of countries from it and
	 * after that it compares with the list of counties that we have passed. Note:
	 * At least 5 countries should be there in order to be a valid map.
	 */
	@Test
	public void parseAndValidateMapTest() {
		ArrayList<CountryModel> parsedCountryList = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH).getCountryList();

		System.out.println(parsedCountryList.size());

		ArrayList<String> countryNames = new ArrayList<String>();
		ArrayList<String> countryList = new ArrayList<String>();

		for (CountryModel loopCountry : parsedCountryList) {
			countryNames.add(loopCountry.getCountryName());
		}
		countryList.add("Boston");
		countryList.add("California");
		countryList.add("New York");
		countryList.add("Nepal");
		countryList.add("India");
		countryList.add("Bhutan");
		System.out.println(countryList);
		assertTrue(countryList.equals(countryNames));
	}

	/**
	 * Function to check whether the function is reading the file and returning the
	 * data without returning null.
	 */
	@Test
	public void convertMapDataToStringTest() {
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		assertNotNull(utility.convertMapDataToString(mapHierarchyModel));

	}

	/**
	 * Method to check whether map file is getting created after the Map
	 * HierarchModel's object and file name is passed.
	 */
	@Test
	public void saveMapFileTest() {

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(ASIA_MAP_FILE_PATH);
		String name = "testfile";
		assertTrue(utility.saveMapFile(mapHierarchyModel, name));

	}

	/**
	 * Function to check that invalid map is not treated as a valid map.
	 */
	@Test
	public void ValidateMapTest() {
		ArrayList<CountryModel> parsedCountryList = utility.parseAndValidateMap(ASIA_INVALID_MAP_FILE_PATH)
				.getCountryList();
		MapHierarchyModel maphierarchy = new MapHierarchyModel();
		assertFalse(maphierarchy.isValErrorFlag());

	}
	
	/**
	 * Function to check that invalid map is not treated as a valid map.
	 */
	@Test
	public void validateIfCountryHasNeighbourTest() {
		ArrayList<CountryModel> parsedCountryList = utility.parseAndValidateMap(NO_PATH)
				.getCountryList();
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(NO_PATH);		
		utility.validateIfCountryHasNeighbour("b22", parsedCountryList, mapHierarchyModel);
		System.out.println(mapHierarchyModel.getErrorMsg());
		assertEquals("Map is invalid as there is no connectivity from or to country b22",mapHierarchyModel.getErrorMsg());
	}
	
}