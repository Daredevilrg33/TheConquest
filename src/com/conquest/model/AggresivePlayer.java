/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;
import java.security.SecureRandom;

import com.conquest.mapeditor.model.CountryModel;

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
						int attackDiceValue = rollDice();
						int defenderDiceValue = rollDice();
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
		}

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

	}

	public int rollDice() {
		int pickedNumber;
		SecureRandom number = new SecureRandom();
		pickedNumber = number.nextInt(6);
		System.out.println("Roll Dice Value: " + pickedNumber);
//		log.info("Roll Dice starts \n Number:" + pickedNumber);

		return pickedNumber + 1;
	}

}
