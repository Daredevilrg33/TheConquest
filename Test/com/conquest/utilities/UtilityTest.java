package com.conquest.utilities;

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
	private static String asiaMapFilePath;

	/** The asia invalid map file path. */
	private static String asiaInvalidMapFilePath;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		asiaMapFilePath = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";
		asiaInvalidMapFilePath = System.getProperty("user.dir") + "\\resources\\testresource\\Asiainvalid.map";
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
		ArrayList<CountryModel> parsedCountryList = utility.parseAndValidateMap(asiaMapFilePath).getCountryList();

		System.out.println(parsedCountryList.size());

		ArrayList<String> countryNames = new ArrayList<String>();
		ArrayList<String> countryList = new ArrayList<String>();

		for (CountryModel loopCountry : parsedCountryList) {
			countryNames.add(loopCountry.getCountryName());
		}
		countryList.add("Russia");
		countryList.add("Japan");
		countryList.add("North Korea");
		countryList.add("Georgia");
		countryList.add("Armenia");

		System.out.println(countryList);
		assertTrue(countryList.equals(countryNames));
	}

	/**
	 * Function to check whether the function is reading the file and returning the
	 * data without returning null.
	 */
	@Test
	public void convertMapDataToStringTest() {
		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		assertNotNull(utility.convertMapDataToString(mapHierarchyModel));

	}

	/**
	 * Method to check whether map file is getting created after the Map
	 * HierarchModel's object and file name is passed.
	 */
	@Test
	public void saveMapFileTest() {

		MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(asiaMapFilePath);
		String name = "testfile";
		assertTrue(utility.saveMapFile(mapHierarchyModel, name));

	}

	/**
	 * Function to check that invalid map is not treated as a valid map.
	 */
	@Test
	public void ValidateMapTest() {
		ArrayList<CountryModel> parsedCountryList = utility.parseAndValidateMap(asiaInvalidMapFilePath)
				.getCountryList();
		MapHierarchyModel maphierarchy = new MapHierarchyModel();
		assertFalse(maphierarchy.isValErrorFlag());

	}
}