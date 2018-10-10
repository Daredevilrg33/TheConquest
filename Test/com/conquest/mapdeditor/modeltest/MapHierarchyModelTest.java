package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.MapHierarchyModel;

public class MapHierarchyModelTest {

	private String mapName = "The World";
	private int noOfCountries = 10;
	MapHierarchyModel c=new MapHierarchyModel(mapName,noOfCountries);
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
	public void test() {
		//fail("Not yet implemented");
		//c.setTotalCountries(-1);
		assertEquals(noOfCountries,c.getTotalCountries());
		
	}
	@Test
	public void test2() {
		//fail("Not yet implemented");
		assertEquals(mapName,c.getConquestMapName());
		
	}
	@Test
	public void test3() {
		//fail("Not yet implemented");
		c.addContinent(continentName);
		assertEquals(continentName,c.searchContinent(continentName).getContinentName());
		
	}
}
