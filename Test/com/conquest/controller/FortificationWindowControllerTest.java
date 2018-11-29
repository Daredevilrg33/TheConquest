package com.conquest.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.model.HumanPlayer;
import com.conquest.model.PlayerModel;
import com.conquest.model.PlayerType;
import com.conquest.window.FortificationWindow;

/**
 * The Class FortificationWindowControllerTest.
 */
public class FortificationWindowControllerTest {

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
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
	 * Fortification test checking values of two countries after transferring armies from one country to another.
	 * INDIA-> Bhutan
	 */
	@Test
	public void fortificationTest() {

		ContinentModel continentModel = new ContinentModel("ASIA");
		ArrayList<String> countryNeighbourModels = new ArrayList<>();
		countryNeighbourModels.add("BHUTAN");
		countryNeighbourModels.add("PAKISTAN");
		countryNeighbourModels.add("NEPAL");
		countryNeighbourModels.add("SRILANKA");
		ArrayList<String> countryNeighbourModels1 = new ArrayList<>();
		countryNeighbourModels1.add("INDIA");
		countryNeighbourModels1.add("PAKISTAN");
		countryNeighbourModels1.add("NEPAL");
		countryNeighbourModels1.add("SRILANKA");

		CountryModel countryModel1 = new CountryModel("INDIA", continentModel, countryNeighbourModels);
		countryModel1.setNoOfArmiesCountry(10);
		CountryModel countryModel2 = new CountryModel("BHUTAN", continentModel, countryNeighbourModels1);
		countryModel2.setNoOfArmiesCountry(20);

		ArrayList<CountryModel> countryModels = new ArrayList<>();
		countryModels.add(countryModel1);
		countryModels.add(countryModel2);
		PlayerModel playerModel = new PlayerModel("Player1", PlayerType.Human);
		playerModel.setStrategy(new HumanPlayer());
		playerModel.setPlayerCountryList(countryModels);

		FortificationWindowController fortificationController = new FortificationWindowController(playerModel, null);
		System.out.println(countryModel1.getNoOfArmiesCountry());
		System.out.println(countryModel2.getNoOfArmiesCountry());
		fortificationController.finding("INDIA");
		fortificationController.updateDestinationUI("BHUTAN");
		fortificationController.sending(6);

		System.out.println(countryModel1.getNoOfArmiesCountry());
		System.out.println(countryModel2.getNoOfArmiesCountry());
		assertEquals(4,countryModel1.getNoOfArmiesCountry());
		assertEquals(26,countryModel2.getNoOfArmiesCountry());

	}

}
