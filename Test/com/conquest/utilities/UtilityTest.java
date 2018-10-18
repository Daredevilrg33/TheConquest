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
	public void parseAndValidateMaptest() {
		// fail("Not yet implemented");
		ArrayList<CountryModel> parsedCountryList = u.parseAndValidateMap("E:\\Asia.map").getCountryList();
		System.out.println(parsedCountryList.size());
		ArrayList<String> countryNames = new ArrayList<String>();
		ArrayList<String> countryList = new ArrayList<String>();

		for (CountryModel loopCountry : parsedCountryList) {
			countryNames.add(loopCountry.getCountryName());
		}
		System.out.println(countryNames + "hiiiii");
		countryList.add("Russia");
		countryList.add("Japan");
		countryList.add("Georgia");
		countryList.add("Armenia");
		System.out.println(countryList);
		assertTrue(countryList.equals(countryNames));
	}

}
