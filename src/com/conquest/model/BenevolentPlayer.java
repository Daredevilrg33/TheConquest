/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;

import com.conquest.mapeditor.model.CountryModel;

/**
 * @author Rohit Gupta
 *
 */
public class BenevolentPlayer extends PlayerModel implements Serializable {

	private static final long serialVersionUID = 9L;

	/**
	 * @param playerName
	 * @param playerType
	 */
	public BenevolentPlayer(String playerName, PlayerType playerType) {
		super(playerName, playerType);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conquest.model.PlayerModel#assignInitialArmyToCountry(com.conquest.model.
	 * GameModel)
	 */
	@Override
	public void assignInitialArmyToCountry(GameModel gameModel) {
		// TODO Auto-generated method stub
		CountryModel countryModel = getPlayerCountryList().get(0);
		int countOfArmies = countryModel.getNoOfArmiesCountry();
		for (CountryModel country : getPlayerCountryList()) {
			if (countOfArmies >= country.getNoOfArmiesCountry()) {
				countryModel = country;
				countOfArmies = country.getNoOfArmiesCountry();
			}

		}
		if (getnoOfArmyInPlayer() > 0) {
			countryModel.addNoOfArmiesCountry();
			reduceArmyInPlayer();
		}

		super.assignInitialArmyToCountry(gameModel);
	}

}
