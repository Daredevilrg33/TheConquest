/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;
import java.util.Random;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.utilities.Utility;

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
		boolean isFortificationDone = false;
		while (!isFortificationDone) {
			int index = random.nextInt(playerModel.getPlayerCountryList().size() - 1);
			CountryModel countryModel = playerModel.getPlayerCountryList().get(index);
			for (String neighbourName : countryModel.getListOfNeighbours()) {
				CountryModel country = playerModel.searchCountry(neighbourName);
				if (country != null) {
					if (country.getNoOfArmiesCountry() > 1) {
						country.removeNoOfArmiesCountry();
						countryModel.addNoOfArmiesCountry();
						isFortificationDone = true;
					}
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
		int noOfTimeToAttack = random.nextInt(playerModel.getPlayerCountryList().size() / 2);
		while (noOfTimeToAttack > 0) {
			int countryIndex = random.nextInt(playerModel.getPlayerCountryList().size() - 1);
			CountryModel sourceCountry = playerModel.getPlayerCountryList().get(countryIndex);
			if (sourceCountry.getNoOfArmiesCountry() > 1) {
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
			} else
				break;
		}

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
