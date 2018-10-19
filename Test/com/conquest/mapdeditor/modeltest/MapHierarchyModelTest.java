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

	MapHierarchyModel maphierarchy = new MapHierarchyModel(mapName, noOfCountries);

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
		// fail("Not yet implemented");
		// c.setTotalCountries(-1);
		assertEquals(noOfCountries, maphierarchy.getTotalCountries());

	}

	/**
	 * Test to check the total no of countries that has been added.
	 */
	@Test
	public void getTotalCountriestest() {
		
		assertEquals(6, maphierarchy1.getTotalCountries());
	}

	/**
	 * getConquestMapNametest Unit Test Test to check the map name of the game.
	 */
	@Test
	public void getConquestMapNametest() {
		// fail("Not yet implemented");
		assertEquals(mapName, maphierarchy.getConquestMapName());
	}

	/**
	 * searchContinentTest Unit Test Test used to check the name of continents that
	 * are added through add continent method.
	 */

	@Test
	public void searchContinentTest() {
		// fail("Not yet implemented");

		assertEquals("ASIA", maphierarchy1.searchContinent("ASIA").getContinentName());
		assertEquals("RUSSIA", maphierarchy1.searchContinent("RUSSIA").getContinentName());

	}

	/**
	 * Method to check whether the continent is deleted or not.
	 */
	@Test
	public void deleteContinentTest() {
		// fail("Not yet implemented");
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

}
