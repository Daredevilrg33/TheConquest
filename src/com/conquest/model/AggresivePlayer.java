/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.utilities.Utility;

/**
 * @author Rohit Gupta
 *
 */
public class AggresivePlayer implements Strategy, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conquest.model.PlayerModel#assignInitialArmyToCountry()
	 */
	@Override
	public void assignInitialArmyToCountry(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub
		CountryModel countryModel = playerModel.getPlayerCountryList().get(0);
		if (playerModel.getnoOfArmyInPlayer() > 0) {
			countryModel.addNoOfArmiesCountry();
			playerModel.reduceArmyInPlayer();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conquest.model.PlayerModel#reinforcementPhase(com.conquest.model.
	 * GameModel)
	 */
	@Override
	public void reinforcementPhase(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub
		CountryModel countryModel = getCountryWithMaxArmies(playerModel);

		while (playerModel.getnoOfArmyInPlayer() > 0) {
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
	 * @see com.conquest.model.PlayerModel#attackPhase(com.conquest.model.GameModel)
	 */
	@Override
	public void attackPhase(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub

		CountryModel sourceCountry = getCountryWithMaxArmies(playerModel);
		int armies = sourceCountry.getNoOfArmiesCountry();
		while (armies > 1) {
			for (String countryName : sourceCountry.getListOfNeighbours()) {
				if (playerModel.searchCountry(countryName) == null) {
					CountryModel targetCountry = gameModel.getMapHierarchyModel().searchCountry(countryName);
					if (targetCountry.getNoOfArmiesCountry() > 0) {
						int attackDiceValue = Utility.rollDice();
						int defenderDiceValue = Utility.rollDice();
						if (attackDiceValue > defenderDiceValue) {
							targetCountry.removeNoOfArmiesCountry();
							if (targetCountry.getNoOfArmiesCountry() == 0) {
								for (PlayerModel player : gameModel.getPlayers()) {
									for (CountryModel countryModels : player.getPlayerCountryList()) {
										if (countryModels.getCountryName().trim()
												.equalsIgnoreCase(targetCountry.getCountryName().trim())) {
											player.removeCountry(targetCountry);
											break;
										}
									}
								}
								sourceCountry.removeNoOfArmiesCountry();
								targetCountry.addNoOfArmiesCountry();
								playerModel.addCountry(targetCountry);
								playerModel.setHasWonTerritory(true);
							}
						} else {
							sourceCountry.removeNoOfArmiesCountry();
						}
					}
				}

			}
			break;
		}
		gameModel.setGameStatus("Fortification Phase starts");
		gameModel.setGamePhaseStage(3);
		fortificationPhase(gameModel, playerModel);

	}

	private CountryModel getCountryWithMaxArmies(PlayerModel playerModel) {
		CountryModel countryModel = playerModel.getPlayerCountryList().get(0);
		int noOfArmies = countryModel.getNoOfArmiesCountry();
		for (CountryModel country : playerModel.getPlayerCountryList()) {
			if (country.getNoOfArmiesCountry() > noOfArmies) {
				countryModel = country;
				noOfArmies = country.getNoOfArmiesCountry();
			}
		}
		return countryModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conquest.model.Strategy#fortificationPhase(com.conquest.model.GameModel)
	 */
	@Override
	public void fortificationPhase(GameModel gameModel, PlayerModel playerModel) {
		// TODO Auto-generated method stub
		CountryModel sourceCountry = getCountryWithMaxArmies(playerModel);
		for (String countryName : sourceCountry.getListOfNeighbours()) {
			CountryModel country = playerModel.searchCountry(countryName);
			if (country != null) {
				int noOfArmies = country.getNoOfArmiesCountry();
				while (noOfArmies > 1) {
					country.removeNoOfArmiesCountry();
					sourceCountry.addNoOfArmiesCountry();
				}
			}
		}
		gameModel.increaseTurn();
		gameModel.moveToNextPlayer();
		gameModel.setGameStatus("Reinforcement Phase starts");
		gameModel.setGamePhaseStage(1);
	}

}
