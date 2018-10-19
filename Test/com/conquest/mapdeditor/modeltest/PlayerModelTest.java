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
		CountryModel countrymodel = new CountryModel("IRAQ");
		CountryModel countrymodel1 = new CountryModel("IRAN");
		CountryModel countrymodel2 = new CountryModel("JAPAN");
		CountryModel countrymodel3 = new CountryModel("CHINA");
		CountryModel countrymodel4 = new CountryModel("BANGLADESH");
		countryModelArrayList = new ArrayList<CountryModel>();
		countryModelArrayList.add(countrymodel);
		countryModelArrayList.add(countrymodel1);
		countryModelArrayList.add(countrymodel2);
		countryModelArrayList.add(countrymodel3);
		countryModelArrayList.add(countrymodel4);

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
		PlayerModel playermodel = new PlayerModel("ROGER", countryModelArrayList);
		assertEquals("JAPAN", playermodel.searchCountry("JAPAN").getCountryName());
		assertEquals("IRAN", playermodel.searchCountry("IRAN").getCountryName());

	}

	/**
	 * Basic test conducted to check whether all the methods are functioning
	 * properly or not.
	 */
	@Test
	public void overallTest() {
		PlayerModel playermodel = new PlayerModel("FORD");
		CountryModel countrymodel4 = new CountryModel("BANGLADESH");
		playermodel.noOfArmyinPlayer(4);
		assertEquals("FORD", playermodel.getPlayerName());
		playermodel.AddCountry(countrymodel4);
		assertEquals("BANGLADESH", playermodel.searchCountry("BANGLADESH").getCountryName());
		assertEquals(4, playermodel.getnoOfArmyinPlayer());

	}

}
