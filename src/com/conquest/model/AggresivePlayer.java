/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;

import org.apache.log4j.Logger;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.utilities.Utility;

/**
 * The Class AggresivePlayer.
 *
 * @author Rohit Gupta
 */
public class AggresivePlayer implements Strategy, Serializable {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 1L;

	/** The Constant log. */
	private static final Logger LOG = Logger.getLogger(AggresivePlayer.class);

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
		System.out.println("Aggresive reinforcement start player name " + playerModel.getPlayerName());
		LOG.info("Aggresive reinforcement start player name " + playerModel.getPlayerName());
		while (playerModel.getnoOfArmyInPlayer() > 0) {
			countryModel.addNoOfArmiesCountry();
			playerModel.reduceArmyInPlayer();
		}
		gameModel.setGameStatus("Attack Phase starts");
		gameModel.setGamePhaseStage(2);
		attackPhase(gameModel, playerModel);
		System.out.println("Aggresive reinforcement end player name " + playerModel.getPlayerName());
		LOG.info("Aggresive reinforcement end player name " + playerModel.getPlayerName());

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
		System.out.println("Aggresive attack start player name " + playerModel.getPlayerName());
		LOG.info("Aggresive attack start player name " + playerModel.getPlayerName());

		while (sourceCountry.getNoOfArmiesCountry() > 1) {
			System.out.println("Attack Phase initial source army: " + sourceCountry.getCountryName()
					+ sourceCountry.getNoOfArmiesCountry());
			for (String countryName : sourceCountry.getListOfNeighbours()) {
				if (playerModel.searchCountry(countryName) == null) {
					CountryModel targetCountry = gameModel.getMapHierarchyModel().searchCountry(countryName);
					while (targetCountry.getNoOfArmiesCountry() > 0) {
						System.out.println("Target Country" + targetCountry.getCountryName()
								+ targetCountry.getNoOfArmiesCountry());
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
								System.out.println("If " + sourceCountry.getNoOfArmiesCountry() + " :::::"
										+ targetCountry.getNoOfArmiesCountry());
								sourceCountry.removeNoOfArmiesCountry();
								targetCountry.addNoOfArmiesCountry();
								playerModel.addCountry(targetCountry);
								playerModel.setHasWonTerritory(true);
								if (playerModel.isGameWon(gameModel.getMapHierarchyModel().totalCountries)) {

									gameModel.setIsWon(true);
									return;
								}
							}
						} else {
							System.out.println("else " + sourceCountry.getNoOfArmiesCountry() + " :::::"
									+ targetCountry.getNoOfArmiesCountry());
							sourceCountry.removeNoOfArmiesCountry();

						}

						if (sourceCountry.getNoOfArmiesCountry() < 2) {
							gameModel.setGameStatus("Fortification Phase starts");
							gameModel.setGamePhaseStage(3);
							fortificationPhase(gameModel, playerModel);
							return;
						}
					}
				}

			}
			break;
		}
		System.out.println("Aggresive attack end player name " + playerModel.getPlayerName());
		LOG.info("Aggresive attack end player name " + playerModel.getPlayerName());
	}

	/**
	 * Gets the country with max armies.
	 *
	 * @param playerModel the player model
	 * @return countryModel the country with max armies
	 */
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
		System.out.println("Aggresive fortification start player name " + playerModel.getPlayerName());
		LOG.info("Aggresive fortification start player name " + playerModel.getPlayerName());

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
		System.out.println("Aggresive fortification end player name " + playerModel.getPlayerName());
		LOG.info("Aggresive fortification end player name " + playerModel.getPlayerName());

	}

}
