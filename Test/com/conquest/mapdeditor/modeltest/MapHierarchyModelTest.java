package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.MapHierarchyModel;

/**
 * The Class MapHierarchyModelTest.
 */
public class MapHierarchyModelTest {

	/** The map name. */
	private String mapName = "The World";

	/** The no of countries. */
	private int noOfCountries = 10;

	/** The map hierarchy. */
	MapHierarchyModel mapHierarchy = new MapHierarchyModel(mapName, noOfCountries);

	/** The map hierarchy 1. */
	MapHierarchyModel maphierarchy1 = new MapHierarchyModel();

	/**
	 * Sets the up before class.
	 * 
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

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
	 * setting up the values of continent and countries before conducting test.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void beforeTest() throws Exception {
		maphierarchy1.addContinent("ASIA");
		maphierarchy1.addCountry("INDIA", "ASIA");
		maphierarchy1.addCountry("BHUTAN", "ASIA");
		maphierarchy1.addCountry("CHINA", "ASIA");
		maphierarchy1.addContinent("RUSSIA");
		maphierarchy1.addCountry("SAINT", "RUSSIA");
		maphierarchy1.addCountry("KREM", "RUSSIA");
		maphierarchy1.addCountry("JOHN", "RUSSIA");
		maphierarchy1.addContinent("AMERICA");
		maphierarchy1.addCountry("BOSTON", "AMERICA");

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
	 * getTotalCountriestest1 Unit test Basic number of total countries test.
	 */
	@Test
	public void getTotalCountriestest1() {
		assertEquals(noOfCountries, mapHierarchy.getTotalCountries());

	}

	/**
	 * Test to check the total no of countries that has been added.
	 */
	@Test
	public void getTotalCountriestest() {

		assertEquals(7, maphierarchy1.getTotalCountries());
	}

	/**
	 * getConquestMapNametest Unit Test Test to check the map name of the game.
	 */
	@Test
	public void getConquestMapNametest() {
		assertEquals(mapName, mapHierarchy.getConquestMapName());
	}

	/**
	 * searchContinentTest Unit Test Test used to check the name of continents that
	 * are added through add continent method.
	 */

	@Test
	public void searchContinentTest() {

		assertEquals("ASIA", maphierarchy1.searchContinent("ASIA").getContinentName());
		assertEquals("RUSSIA", maphierarchy1.searchContinent("RUSSIA").getContinentName());

	}

	/**
	 * Method to check whether the continent is deleted or not.
	 */
	@Test
	public void deleteContinentTest() {
		String value = maphierarchy1.deleteContinent("RUSSIA");
		if (value.trim().isEmpty()) {
			assertNull(maphierarchy1.searchContinent("RUSSIA"));
		} else {
			maphierarchy1.deleteCountry("SAINT");
			maphierarchy1.deleteCountry("KREM");
			maphierarchy1.deleteCountry("JOHN");
			maphierarchy1.deleteContinent("RUSSIA");
			assertNull(maphierarchy1.searchContinent("RUSSIA"));
		}

	}

	/**
	 * maphierarchy1 country is moved to new continent.
	 */
	@Test
	public void moveTest() {
		maphierarchy1.moveCountry("INDIA", "RUSSIA");
		ContinentModel toContinent = maphierarchy1.searchContinent("RUSSIA");
		System.out.println(toContinent.searchCountry("INDIA"));
		assertEquals("INDIA", toContinent.searchCountry("INDIA").getCountryName());

	}

	/**
	 * country is deleted and checked whether it exists.
	 */
	@Test
	public void deleteCountryTest() {
		maphierarchy1.deleteCountry("INDIA");
		ContinentModel toContinent = maphierarchy1.searchContinent("ASIA");
		assertNull(toContinent.searchCountry("INDIA"));

	}

	/**
	 * country is renamed and checked whether it exists.
	 */
	@Test
	public void renameCountryTest() {
		maphierarchy1.renameCountry("BHUTAN", "MALAYSIA");
		ContinentModel toContinent = maphierarchy1.searchContinent("ASIA");
		assertNull(toContinent.searchCountry("BHUTAN"));
		assertNotNull(toContinent.searchCountry("MALAYSIA"));

	}

	/**
	 * continent is renamed and checked whether it exists.
	 */
	@Test
	public void renameContinentTest() {
		maphierarchy1.renameContinent("AMERICA", "NORTHAMERICA");
		assertNull(maphierarchy1.searchContinent("AMERICA"));
		assertNotNull(maphierarchy1.searchContinent("NORTHAMERICA"));

	}

	/**
	 * check to ensure without emptying a continent cannot be deleted.
	 */
	@Test
	public void delContinentTest() {
		assertEquals("Continent 'ASIA' is not empty, Please delete or move all the countries in it.",
				maphierarchy1.deleteContinent("ASIA"));
	}

}
