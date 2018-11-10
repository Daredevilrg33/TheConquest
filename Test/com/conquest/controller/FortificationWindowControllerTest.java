package com.conquest.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.PlayerModel;
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
	 * Fortification test.
	 */
	@Test
	public void fortificationTest() {

		ContinentModel countryModel = new ContinentModel("ASIA");
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
		CountryModel country = new CountryModel("INDIA", countryModel, countryNeighbourModels);
		country.setNoOfArmiesCountry(10);
		CountryModel country1 = new CountryModel("BHUTAN", countryModel, countryNeighbourModels1);
		country1.setNoOfArmiesCountry(20);

		ArrayList<CountryModel> countryModels = new ArrayList<>();
		countryModels.add(country);
		countryModels.add(country1);

		PlayerModel player = new PlayerModel("Player1", countryModels);

		FortificationWindowController fortificationController = new FortificationWindowController(player, null);
		System.out.println(country.getNoOfArmiesCountry());
		System.out.println(country1.getNoOfArmiesCountry());
		fortificationController.finding("INDIA");
		fortificationController.updateDestinationUI("BHUTAN");
		fortificationController.sending(6);

		System.out.println(country.getNoOfArmiesCountry());
		System.out.println(country1.getNoOfArmiesCountry());

	}

}
