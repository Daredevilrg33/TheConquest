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

	MapHierarchyModel c = new MapHierarchyModel(mapName, noOfCountries);

	MapHierarchyModel b = new MapHierarchyModel();

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
		b.addContinent("ASIA");
		b.addCountry("INDIA", "ASIA");
		b.addCountry("BHUTAN", "ASIA");
		b.addCountry("CHINA", "ASIA");
		b.addContinent("RUSSIA");
		b.addCountry("SAINT", "RUSSIA");
		b.addCountry("KREM", "RUSSIA");
		b.addCountry("JOHN", "RUSSIA");
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
		assertEquals(noOfCountries, c.getTotalCountries());

	}

	/**
	 * Test to check the total no of countries that has been added.
	 */
	@Test
	public void getTotalCountriestest() {
		// fail("Not yet implemented");
		// c.setTotalCountries(-1);

//		String continentName=null;
//		for(ContinentModel loopContinent : b.getContinentsList() )
//		{
//			 continentName= loopContinent.getContinentName();
//		}
//		System.out.println(b.getTotalCountries());

		// System.out.println(b.searchContinent("ASIA").getContinentName());
		// assertEquals("ASIA",continentName);
		assertEquals(6, b.getTotalCountries());
	}

	/**
	 * getConquestMapNametest Unit Test Test to check the map name of the game.
	 */
	@Test
	public void getConquestMapNametest() {
		// fail("Not yet implemented");
		assertEquals(mapName, c.getConquestMapName());
	}

	/**
	 * searchContinentTest Unit Test Test used to check the name of continents that
	 * are added through add continent method.
	 */

	@Test
	public void searchContinentTest() {
		// fail("Not yet implemented");

		assertEquals("ASIA", b.searchContinent("ASIA").getContinentName());
		assertEquals("RUSSIA", b.searchContinent("RUSSIA").getContinentName());

	}
}
