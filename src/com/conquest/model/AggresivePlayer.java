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
public class AggresivePlayer extends PlayerModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param playerName
	 * @param playerType
	 */
	public AggresivePlayer(String playerName, PlayerType playerType) {
		super(playerName, playerType);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conquest.model.PlayerModel#assignInitialArmyToCountry()
	 */
	@Override
	public void assignInitialArmyToCountry(GameModel gameModel) {
		// TODO Auto-generated method stub
//		super.assignInitialArmyToCountry();
		CountryModel countryModel = this.getPlayerCountryList().get(0);
		if (getnoOfArmyInPlayer() > 0) {
			countryModel.addNoOfArmiesCountry();
			reduceArmyInPlayer();
		}
		super.assignInitialArmyToCountry(gameModel);
	}

}
