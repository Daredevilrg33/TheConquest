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
	private CountryModel countryModel, countryModel1, countryModel2, countryModel3;
	private ArrayList<CountryModel> countryNeighbourModels ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void beforeTest() throws Exception {
		countryModel = new CountryModel("BHUTAN");
		countryModel1 = new CountryModel("PAKISTAN");
		countryModel2 = new CountryModel("NEPAL");
		countryModel3 = new CountryModel("SRILANKA");
		countryNeighbourModels = new ArrayList<>();
		countryNeighbourModels.add(countryModel);
		countryNeighbourModels.add(countryModel1);
		countryNeighbourModels.add(countryModel2);
		countryNeighbourModels.add(countryModel3);
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
		assertEquals("BHUTAN",c.searchNeighboursCountry("Bhutan").getCountryName());
		assertEquals("PAKISTAN",c.searchNeighboursCountry("PAKISTAN").getCountryName());
		assertEquals("NEPAL",c.searchNeighboursCountry("NEPAL").getCountryName());
		assertEquals("SRILANKA",c.searchNeighboursCountry("SRILANKA").getCountryName());

		
		
		//fail("Not yet implemented");
	}

}
