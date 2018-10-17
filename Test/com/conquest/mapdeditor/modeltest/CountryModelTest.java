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

public class CountryModelTest {
	
	private ArrayList<String> countryNeighbourModels ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void beforeTest() throws Exception {
	
		countryNeighbourModels = new ArrayList<>();
		countryNeighbourModels.add("BHUTAN");
		countryNeighbourModels.add("PAKISTAN");
		countryNeighbourModels.add("NEPAL");
		countryNeighbourModels.add("SRILANKA");
	}

	@After
	public void tearDown() throws Exception {
	}
/**
 * Test used for checking the neighbours of country
 */
	@Test
	public void searchNeighboursCountryTest() {
	 ContinentModel cm = new ContinentModel("ASIA");

		CountryModel c=new CountryModel("INDIA",cm,countryNeighbourModels);
		assertEquals("BHUTAN",c.searchNeighboursCountry("Bhutan"));
		assertEquals("PAKISTAN",c.searchNeighboursCountry("PAKISTAN"));
		assertEquals("NEPAL",c.searchNeighboursCountry("NEPAL"));
		assertEquals("SRILANKA",c.searchNeighboursCountry("SRILANKA"));

		
		
		//fail("Not yet implemented");
	}

}
