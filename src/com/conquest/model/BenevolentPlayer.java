/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;

import com.conquest.mapeditor.model.CountryModel;

/**
 * The Class BenevolentPlayer.
 *
 * @author Rohit Gupta
 */
public class BenevolentPlayer implements Serializable, Strategy {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9L;

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
			CountryModel countryModel = playerModel.getPlayerCountryList().get(0);
			int noOfArmies = countryModel.getNoOfArmiesCountry();
			for (CountryModel country : playerModel.getPlayerCountryList()) {
				if (country.getNoOfArmiesCountry() < noOfArmies) {
					countryModel = country;
					noOfArmies = country.getNoOfArmiesCountry();
				}
			}

			countryModel.addNoOfArmiesCountry();
			playerModel.reduceArmyInPlayer();

		}
		gameModel.setGameStatus("Attack Phase starts");
		gameModel.setGamePhaseStage(2);
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
		CountryModel sourceCountry = getCountryWithMinArmies(playerModel);
		for (String countryName : sourceCountry.getListOfNeighbours()) {
			CountryModel country = playerModel.searchCountry(countryName);
			if (country != null) {
				if (sourceCountry.getNoOfArmiesCountry() < country.getNoOfArmiesCountry()) {
					sourceCountry.addNoOfArmiesCountry();
					country.removeNoOfArmiesCountry();
					break;
				}
			}
		}

		gameModel.increaseTurn();
		gameModel.moveToNextPlayer();
		gameModel.setGameStatus("Reinforcement Phase starts");
		gameModel.setGamePhaseStage(1);
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
		gameModel.setGameStatus("Fortification Phase starts");
		gameModel.setGamePhaseStage(3);
		fortificationPhase(gameModel, playerModel);
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
		CountryModel countryModel = playerModel.getPlayerCountryList().get(0);
		int countOfArmies = countryModel.getNoOfArmiesCountry();
		for (CountryModel country : playerModel.getPlayerCountryList()) {
			if (countOfArmies >= country.getNoOfArmiesCountry()) {
				countryModel = country;
				countOfArmies = country.getNoOfArmiesCountry();
			}

		}
		if (playerModel.getnoOfArmyInPlayer() > 0) {
			countryModel.addNoOfArmiesCountry();
			playerModel.reduceArmyInPlayer();
		}
	}

	/**
	 * Gets the country with min armies.
	 *
	 * @param playerModel the player model
	 * @return the country with min armies
	 */
	private CountryModel getCountryWithMinArmies(PlayerModel playerModel) {
		CountryModel countryModel = playerModel.getPlayerCountryList().get(0);
		int noOfArmies = countryModel.getNoOfArmiesCountry();
		for (CountryModel country : playerModel.getPlayerCountryList()) {
			if (country.getNoOfArmiesCountry() < noOfArmies) {
				countryModel = country;
				noOfArmies = country.getNoOfArmiesCountry();
			}
		}
		return countryModel;
	}

}
