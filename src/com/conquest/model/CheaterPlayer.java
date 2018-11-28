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
public class CheaterPlayer extends PlayerModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param playerName
	 * @param playerType
	 */
	public CheaterPlayer(String playerName, PlayerType playerType) {
		super(playerName, playerType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void assignInitialArmyToCountry(GameModel gameModel) {
		// TODO Auto-generated method stub

		if (getnoOfArmyInPlayer() > 0) {
			for (CountryModel countryModel : getPlayerCountryList()) {
				countryModel.addNoOfArmiesCountry();

			}
			reduceArmyInPlayer();
		}

		super.assignInitialArmyToCountry(gameModel);
	}
}
