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
import com.conquest.model.HumanPlayer;
import com.conquest.model.PlayerModel;
import com.conquest.model.PlayerType;

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
		CountryModel countryModel = new CountryModel("IRAQ");
		CountryModel countryModel1 = new CountryModel("IRAN");
		CountryModel countryModel2 = new CountryModel("JAPAN");
		CountryModel countryModel3 = new CountryModel("CHINA");
		CountryModel countryModel4 = new CountryModel("BANGLADESH");
		countryModelArrayList = new ArrayList<CountryModel>();
		countryModelArrayList.add(countryModel);
		countryModelArrayList.add(countryModel1);
		countryModelArrayList.add(countryModel2);
		countryModelArrayList.add(countryModel3);
		countryModelArrayList.add(countryModel4);

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

		PlayerModel playerModel = new PlayerModel("ROGER", PlayerType.Human);
		playerModel.setStrategy(new HumanPlayer());
		playerModel.setPlayerCountryList(countryModelArrayList);
		assertEquals("JAPAN", playerModel.searchCountry("JAPAN").getCountryName());
		assertEquals("IRAN", playerModel.searchCountry("IRAN").getCountryName());
	}

	/**
	 * Basic test conducted to check whether all the methods are functioning
	 * properly or not.
	 */
	@Test
	public void overallTest() {

		PlayerModel playerModel = new PlayerModel("FORD", PlayerType.Human);
		playerModel.setStrategy(new HumanPlayer());
		playerModel.setPlayerCountryList(countryModelArrayList);
		CountryModel countryModel4 = new CountryModel("BANGLADESH");
		playerModel.noOfArmyInPlayer(4);
		assertEquals("FORD", playerModel.getPlayerName());
		playerModel.addCountry(countryModel4);
		assertEquals("BANGLADESH", playerModel.searchCountry("BANGLADESH").getCountryName());
		assertEquals(4, playerModel.getnoOfArmyInPlayer());

	}

}
