package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;

/**
 * The Class ContinentModelTest.
 */
public class ContinentModelTest {

	/** The country model. */
	private ContinentModel countryModel = new ContinentModel("ASIA");

	/** The country neighbor models. */
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
	 * Before test case.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void beforeTestCase() throws Exception {

		countryNeighbourModels = new ArrayList<>();
		countryNeighbourModels.add("Bhutan");
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
	 * searchCountryTest Unit test Test to search the countries whether it retrieved
	 * from search country method.
	 */
	@Test
	public void searchCountryTest() {
		CountryModel countrymodel = new CountryModel("INDIA", countryModel, countryNeighbourModels);
		countryModel.addCountry(countrymodel);
		assertEquals("INDIA", countryModel.searchCountry("INDIA").getCountryName());

	}

	/**
	 * Test to check whether after deletion country is there or not.
	 */
	@Test
	public void deleteCountryTest() {
		CountryModel countrymodel = new CountryModel("RUSSIA");
		CountryModel countrymodel1 = new CountryModel("AMERICA");
		CountryModel countrymodel2 = new CountryModel("JAPAN");
		CountryModel countrymodel3 = new CountryModel("FRANCE");
		countryModel.addCountry(countrymodel);
		countryModel.addCountry(countrymodel1);
		countryModel.addCountry(countrymodel2);
		countryModel.addCountry(countrymodel3);

		assertEquals("RUSSIA", countryModel.searchCountry("RUSSIA").getCountryName());
		assertEquals("AMERICA", countryModel.searchCountry("AMERICA").getCountryName());

		countryModel.deleteCountry(countrymodel1);
		assertNull(countryModel.searchCountry("AMERICA"));

	}

	/**
	 * Not.
	 *
	 * @param equals the equals
	 * @return the object
	 */
	private Object not(boolean equals) {
		return null;
	}

}
