/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;
import java.util.Random;

import com.conquest.mapeditor.model.CountryModel;

/**
 * @author Rohit Gupta
 *
 */
public class RandomPlayer extends PlayerModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param playerName
	 * @param playerType
	 */
	public RandomPlayer(String playerName, PlayerType playerType) {
		super(playerName, playerType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void assignInitialArmyToCountry(GameModel gameModel) {
		// TODO Auto-generated method stub
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(getPlayerCountryList().size());
		CountryModel countryModel = getPlayerCountryList().get(index);

		if (getnoOfArmyInPlayer() > 0) {
			countryModel.addNoOfArmiesCountry();
			reduceArmyInPlayer();
		}

		super.assignInitialArmyToCountry(gameModel);
	}

}
