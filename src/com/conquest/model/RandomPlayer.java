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
public class RandomPlayer implements Serializable, Strategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Random random = new Random();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conquest.model.Strategy#reinforcementPhase(com.conquest.model.GameModel,
	 * com.conquest.model.PlayerModel)
	 */
	@Override
	public void reinforcementPhase(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub
		while (playerModel.getnoOfArmyInPlayer() > 0) {
			int index = random.nextInt(playerModel.getPlayerCountryList().size());
			if (index == playerModel.getPlayerCountryList().size())
				index = index - 1;
			CountryModel countryModel = playerModel.getPlayerCountryList().get(index);
			countryModel.addNoOfArmiesCountry();
			playerModel.reduceArmyInPlayer();
		}
		attackPhase(gameModel, playerModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conquest.model.Strategy#fortificationPhase(com.conquest.model.GameModel,
	 * com.conquest.model.PlayerModel)
	 */
	@Override
	public void fortificationPhase(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conquest.model.Strategy#attackPhase(com.conquest.model.GameModel,
	 * com.conquest.model.PlayerModel)
	 */
	@Override
	public void attackPhase(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conquest.model.Strategy#assignInitialArmyToCountry(com.conquest.model.
	 * GameModel, com.conquest.model.PlayerModel)
	 */
	@Override
	public void assignInitialArmyToCountry(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub
		int index = random.nextInt(playerModel.getPlayerCountryList().size());
		if (index == playerModel.getPlayerCountryList().size())
			index = index - 1;

		CountryModel countryModel = playerModel.getPlayerCountryList().get(index);

		if (playerModel.getnoOfArmyInPlayer() > 0) {
			countryModel.addNoOfArmiesCountry();
			playerModel.reduceArmyInPlayer();
		}

	}

}
