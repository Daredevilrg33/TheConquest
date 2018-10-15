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
	private String continentName  = "Europe";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getTotalCountriestest1() {
		//fail("Not yet implemented");
		//c.setTotalCountries(-1);
		assertEquals(noOfCountries,c.getTotalCountries());
		
	}
	@Test
	public void getTotalCountriestest() {
		//fail("Not yet implemented");
		//c.setTotalCountries(-1);
		b.addContinent("ASIA");
		b.addCountry("INDIA","ASIA");
		b.addCountry("BHUTAN","ASIA");
		b.addCountry("CHINA","ASIA");
		b.addContinent("RUSSIA");
		b.addCountry("SAINT","RUSSIA");
		b.addCountry("KREM","RUSSIA");
		b.addCountry("JOHN","RUSSIA");
//		String continentName=null;
//		for(ContinentModel loopContinent : b.getContinentsList() )
//		{
//			 continentName= loopContinent.getContinentName();
//		}
//		System.out.println(b.getTotalCountries());
//		System.out.println(continentName);
		//assertEquals("ASIA",continentName);
		assertEquals(6,b.getTotalCountries());
	}
	
	@Test
	public void getConquestMapNametest() {
		//fail("Not yet implemented");
		assertEquals(mapName,c.getConquestMapName());
		
	}
//	@Test
//	public void test3() {
//		//fail("Not yet implemented");
//		b.addContinent("ASIA");
//		System.out.println(b.searchContinent(continentName).getContinentName());
//		
//		assertEquals("RUSSIA",b.searchContinent(continentName).getContinentName());
//		
//	}
}
