package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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

	private ContinentModel cm = new ContinentModel("ASIA");

	private ArrayList<CountryModel> cl = new ArrayList<CountryModel>();

	private MapHierarchyModel mh = new MapHierarchyModel();
	private ArrayList<String> countryNeighbourModels;

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
		CountryModel c = new CountryModel("INDIA", cm, countryNeighbourModels);
		cm.addCountry(c);
		// System.out.println(cm.searchCountry("INDIA"));
		assertEquals("INDIA", cm.searchCountry("INDIA").getCountryName());

	}

	/**
	 * Test to check whether after deletion country is there or not.
	 */
	@Test
	public void deleteCountryTest() {
		CountryModel c = new CountryModel("RUSSIA");
		CountryModel c1 = new CountryModel("AMERICA");
		CountryModel c2 = new CountryModel("JAPAN");
		CountryModel c3 = new CountryModel("FRANCE");
		cm.addCountry(c);
		cm.addCountry(c1);
		cm.addCountry(c2);
		cm.addCountry(c3);

		// System.out.println(cm.searchCountry("INDIA"));
		assertEquals("RUSSIA", cm.searchCountry("RUSSIA").getCountryName());
		assertEquals("AMERICA", cm.searchCountry("AMERICA").getCountryName());
		cm.deleteCountry(c1);
		System.out.println(cm.searchCountry("AMERICA").getCountryName());
		assertNull(cm.searchCountry("AMERICA").getCountryName());

	}

	private Object not(boolean equals) {
		// TODO Auto-generated method stub
		return null;
	}

}
