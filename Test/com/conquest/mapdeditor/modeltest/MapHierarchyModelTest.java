package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.MapHierarchyModel;

public class MapHierarchyModelTest {

	private String mapName = "The World";
	private int noOfCountries = 10;
	MapHierarchyModel c=new MapHierarchyModel(mapName,noOfCountries);
	MapHierarchyModel b=new MapHierarchyModel();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
/**
 * setting up the values of continent and countries before conducting test
 * @throws Exception
 */
	@Before
	public void beforeTest() throws Exception {
		b.addContinent("ASIA");
		b.addCountry("INDIA","ASIA");
		b.addCountry("BHUTAN","ASIA");
		b.addCountry("CHINA","ASIA");
		b.addContinent("RUSSIA");
		b.addCountry("SAINT","RUSSIA");
		b.addCountry("KREM","RUSSIA");
		b.addCountry("JOHN","RUSSIA");
	}

	@After
	public void tearDown() throws Exception {
	}
	/**
	 * Basic number of total countries test.
	 */

	@Test
	public void getTotalCountriestest1() {
		//fail("Not yet implemented");
		//c.setTotalCountries(-1);
		assertEquals(noOfCountries,c.getTotalCountries());
		
	}
	/**
	 * Test to check the total no of countries that has been added.
	 */
	@Test
	public void getTotalCountriestest() {
		//fail("Not yet implemented");
		//c.setTotalCountries(-1);
		
//		String continentName=null;
//		for(ContinentModel loopContinent : b.getContinentsList() )
//		{
//			 continentName= loopContinent.getContinentName();
//		}
//		System.out.println(b.getTotalCountries());
		
		//System.out.println(b.searchContinent("ASIA").getContinentName());
		//assertEquals("ASIA",continentName);
		assertEquals(6,b.getTotalCountries());
	}
	/**
	 * Test to check the map name of the game.
	 */
	@Test
	public void getConquestMapNametest() {
		//fail("Not yet implemented");
		assertEquals(mapName,c.getConquestMapName());
		
	}
	/**
	 *  Test used to check the name of continents that are added through add continent method
	 */
	
	@Test
	public void searchContinentTest() {
		//fail("Not yet implemented");
		
		
		assertEquals("ASIA",b.searchContinent("ASIA").getContinentName());
		assertEquals("RUSSIA",b.searchContinent("RUSSIA").getContinentName());
		
	}
}
