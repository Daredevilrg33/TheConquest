package com.conquest.mapdeditor.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.PlayerModel;

public class PlayerModelTest {

	private ArrayList<CountryModel> countryModelArrayList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void beforeTest() throws Exception {
		CountryModel cm = new CountryModel("IRAQ");
		CountryModel cm1 = new CountryModel("IRAN");
		CountryModel cm2 = new CountryModel("JAPAN");
		CountryModel cm3 = new CountryModel("CHINA");
		CountryModel cm4 = new CountryModel("BANGLADESH");
		countryModelArrayList = new ArrayList<CountryModel>();
		countryModelArrayList.add(cm);
		countryModelArrayList.add(cm1);
		countryModelArrayList.add(cm2);
		countryModelArrayList.add(cm3);
		countryModelArrayList.add(cm4);

	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Method used to check whether the player is having the corresponding country
	 * or not.
	 */
	@Test
	public void searchCountrytest() {
		// fail("Not yet implemented");
		PlayerModel pm = new PlayerModel("ROGER", countryModelArrayList);
		assertEquals("JAPAN", pm.searchCountry("JAPAN").getCountryName());
		assertEquals("IRAN", pm.searchCountry("IRAN").getCountryName());

	}

	/**
	 * Basic test conducted to check whether all the methods are functioning
	 * properly or not.
	 */
	@Test
	public void overallTest() {
		// fail("Not yet implemented");
		PlayerModel pm = new PlayerModel("FORD");
		CountryModel cm4 = new CountryModel("BANGLADESH");
		pm.noOfArmyinPlayer(4);
		assertEquals("FORD", pm.getPlayerName());
		pm.AddCountry(cm4);
		assertEquals("BANGLADESH", pm.searchCountry("BANGLADESH").getCountryName());
		assertEquals(4, pm.getnoOfArmyinPlayer());

	}

}
