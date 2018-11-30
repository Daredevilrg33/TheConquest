package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;

/**
 * The Class CountryModelTest.
 */
public class CountryModelTest {

	/** The country neighbour models. */
	private ArrayList<String> countryNeighbourModels;

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
	 * Before test.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void beforeTest() throws Exception {

		countryNeighbourModels = new ArrayList<>();
		countryNeighbourModels.add("BHUTAN");
		countryNeighbourModels.add("PAKISTAN");
		countryNeighbourModels.add("NEPAL");
		countryNeighbourModels.add("SRILANKA");
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
	 * searchNeighboursCountryTest Unit Test Test used for checking the neighbours
	 * of country.
	 */
	@Test
	public void searchNeighboursCountryTest() {
		ContinentModel continentModel = new ContinentModel("ASIA");

		CountryModel country = new CountryModel("INDIA", continentModel, countryNeighbourModels);
		assertEquals("BHUTAN", country.searchNeighboursCountry("Bhutan"));
		assertEquals("PAKISTAN", country.searchNeighboursCountry("PAKISTAN"));
		assertEquals("NEPAL", country.searchNeighboursCountry("NEPAL"));
		assertEquals("SRILANKA", country.searchNeighboursCountry("SRILANKA"));

	}

}
