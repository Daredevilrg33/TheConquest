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

/**
 * The Class PlayerModelTest.
 */
public class PlayerModelTest {

	/** The country model array list. */
	private ArrayList<CountryModel> countryModelArrayList;

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

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
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
		PlayerModel playermodel = new PlayerModel("FORD",countryModelArrayList);
		CountryModel countrymodel4 = new CountryModel("BANGLADESH");
		playermodel.noOfArmyInPlayer(4);
		assertEquals("FORD", playermodel.getPlayerName());
		playermodel.addCountry(countrymodel4);
		assertEquals("BANGLADESH", playermodel.searchCountry("BANGLADESH").getCountryName());
		assertEquals(4, playermodel.getnoOfArmyInPlayer());

	}

}
