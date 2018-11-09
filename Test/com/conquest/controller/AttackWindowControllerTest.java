package com.conquest.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.model.GameModel;
import com.conquest.utilities.Utility;
import com.conquest.window.AttackPhaseWindow;
import com.conquest.window.GameWindow;

public class AttackWindowControllerTest {

	private Utility utility = new Utility();
	private static String asiaMapFilePath;
	/** The country neighbour models. */
	private ArrayList<String> countryNeighbourModels;


	@Before
	public void setUp() throws Exception {
		asiaMapFilePath = System.getProperty("user.dir") + "\\resources\\testresource\\Asia.map";

	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Attack test to check that if a player attacks from country A to country B, then after defeating country B, the country B gets added to its country list.
	 */
	
	
	
	
	@Test
	public void test() {
		MapHierarchyModel mapHierarchyModel = new MapHierarchyModel("Test",4);
		ArrayList<ContinentModel> continentsList=new ArrayList<ContinentModel>();
		ContinentModel continentModel=new ContinentModel("ASIA");
		CountryModel countryModel = new CountryModel("BHUTAN");
		CountryModel countryModel1 = new CountryModel("INDIA");
		CountryModel countryModel2 = new CountryModel("NEPAL");
		CountryModel countryModel3 = new CountryModel("SRILANKA");
		continentModel.addCountry(countryModel);
		continentModel.addCountry(countryModel1);
		continentModel.addCountry(countryModel2);
		continentModel.addCountry(countryModel3);
		continentsList.add(continentModel);
		ArrayList<CountryModel> countryList = new ArrayList<>();
		countryList.add(countryModel);
		countryList.add(countryModel1);
		countryList.add(countryModel2);
		countryList.add(countryModel3);
			mapHierarchyModel.setContinentsList(continentsList);
		mapHierarchyModel.setCountryList(countryList);
		ArrayList<CountryModel> countryModelArrayList= new ArrayList<CountryModel>();
		countryModelArrayList.add(countryModel1);
		countryModelArrayList.add(countryModel2);
		ArrayList<CountryModel> countryModelArrayList1= new ArrayList<CountryModel>();
		countryModelArrayList1.add(countryModel);
		countryModelArrayList1.add(countryModel3);
		PlayerModel[] playerModels = new PlayerModel[2];
		playerModels[0] = new PlayerModel("Player1", countryModelArrayList);
		playerModels[1] = new PlayerModel("Player2", countryModelArrayList1);
		GameModel gameModel = new GameModel(mapHierarchyModel, playerModels);
		AttackPhaseWindow attackPhaseWindow = new AttackPhaseWindow(gameModel, playerModels, playerModels[0]);
				
		
		
		AttackWindowController attackwindowcontroller=new AttackWindowController(playerModels,attackPhaseWindow,gameModel);
		
		ArrayList<Integer> attackingDiceValues=new ArrayList<Integer>();
		attackingDiceValues.add(6);
		attackingDiceValues.add(5);
		attackingDiceValues.add(4);
		ArrayList<Integer> defendingDiceValues=new ArrayList<Integer>();
		defendingDiceValues.add(4);
		defendingDiceValues.add(3);
		countryNeighbourModels = new ArrayList<>();
		countryNeighbourModels.add("BHUTAN");
		countryNeighbourModels.add("NEPAL");
		countryNeighbourModels.add("SRILANKA");
		System.out.println("Player 1 country size:"+playerModels[0].getPlayerCountryList().size());
		CountryModel country = new CountryModel("INDIA", continentModel, countryNeighbourModels);
		country.setNoOfArmiesCountry(4);
		countryModel.setNoOfArmiesCountry(2);
		attackwindowcontroller.attackEvaluation(attackingDiceValues, defendingDiceValues, country, countryModel,false);
		assertEquals(3,playerModels[0].getPlayerCountryList().size());
		System.out.println("Player 1 country size after winning:"+playerModels[0].getPlayerCountryList().size());
		
		
	}

}
