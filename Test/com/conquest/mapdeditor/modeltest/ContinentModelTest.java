package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.assertEquals;

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
	private ArrayList<String> countryNeighbourModels ;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Before test case.
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
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * searchCountryTest Unit test
	 * Test to search the countries whether it retrieved from search country method.
	 */
	@Test
	public void searchCountryTest() {
		CountryModel c=new CountryModel("INDIA",cm,countryNeighbourModels);
		cm.addCountry(c);
		//System.out.println(cm.searchCountry("INDIA"));
		assertEquals("INDIA",cm.searchCountry("INDIA").getCountryName());

	}

}
