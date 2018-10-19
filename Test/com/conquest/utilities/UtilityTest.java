package com.conquest.utilities;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;

public class UtilityTest {
	Utility u = new Utility();

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

	/**
	 * First it parses the map and then extracts the list of countries from it and
	 * after that it compares with the list of counties that we have passed.
	 */
	@Test
	public void parseAndValidateMapTest() {
		// fail("Not yet implemented");
		ArrayList<CountryModel> parsedCountryList = u.parseAndValidateMap("E:\\Asia.map").getCountryList();
		System.out.println(parsedCountryList.size());
		ArrayList<String> countryNames = new ArrayList<String>();
		ArrayList<String> countryList = new ArrayList<String>();

		for (CountryModel loopCountry : parsedCountryList) {
			countryNames.add(loopCountry.getCountryName());
		}
		countryList.add("Russia");
		countryList.add("Japan");
		countryList.add("Georgia");
		countryList.add("Armenia");
		System.out.println(countryList);
		assertTrue(countryList.equals(countryNames));
	}
	/**
	 * Function to check whether the function is reading the file and returning the data without returning null.
	 */
	@Test
	public void convertMapDataToStringTest() {
		// fail("Not yet implemented");
		MapHierarchyModel mapHierarchyModel = u.parseAndValidateMap("E:\\Asia.map");
		System.out.println(u.convertMapDataToString(mapHierarchyModel));
		assertNotNull(u.convertMapDataToString(mapHierarchyModel));
		
	}
	
	/** 
	 * Method to check wheter map file is getting created after the Map HierarchModel's object and file name is passed
	 */
	@Test
	public void saveMapFileTest() {
		MapHierarchyModel mapHierarchyModel = u.parseAndValidateMap("E:\\Asia.map");
		String name="testfile";
		assertTrue(u.saveMapFile(mapHierarchyModel, name));
		
	}
	@Test
	public void ValidateMapTest() {
		// fail("Not yet implemented");
		ArrayList<CountryModel> parsedCountryList = u.parseAndValidateMap("E:\\Asiainvalid.map").getCountryList();
		MapHierarchyModel mh= new MapHierarchyModel();
		System.out.println(mh.isValErrorFlag());
		assertFalse(mh.isValErrorFlag());

}
}