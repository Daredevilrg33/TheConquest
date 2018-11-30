/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;
import java.util.Random;

import org.apache.log4j.Logger;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.utilities.Utility;

/**
 * The Class RandomPlayer.
 *
 * @author Rohit Gupta
 */
public class RandomPlayer implements Serializable, Strategy {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 1L;

	/** The Constant log. */
	private static final Logger LOG = Logger.getLogger(RandomPlayer.class);

	/** The random. */
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
		System.out.println("Random reinforcement start player name " + playerModel.getPlayerName());
		LOG.info("Random reinforcement start player name " + playerModel.getPlayerName());

		while (playerModel.getnoOfArmyInPlayer() > 0) {
			int index = random.nextInt(playerModel.getPlayerCountryList().size());
			if (index == playerModel.getPlayerCountryList().size())
				index = index - 1;
			CountryModel countryModel = playerModel.getPlayerCountryList().get(index);
			countryModel.addNoOfArmiesCountry();
			playerModel.reduceArmyInPlayer();
		}
		System.out.println("Random reinforcement end player name " + playerModel.getPlayerName());
		LOG.info("Random reinforcement end player name " + playerModel.getPlayerName());
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
		System.out.println("Random fortification start player name " + playerModel.getPlayerName());
		LOG.info("Random fortification start player name " + playerModel.getPlayerName());

		boolean isFortificationDone = false;
		if (playerModel.getPlayerCountryList().size() > 0) {
			while (!isFortificationDone) {
				if (playerModel.getPlayerCountryList().size() - 1 == 0)
					break;
				int index = random.nextInt(playerModel.getPlayerCountryList().size() - 1);
				CountryModel countryModel = playerModel.getPlayerCountryList().get(index);
				if (countryModel.getNoOfArmiesCountry() > 1) {
					for (String neighbourName : countryModel.getListOfNeighbours()) {
						CountryModel country = playerModel.searchCountry(neighbourName);
						if (country != null) {
							int armiesToBeSent = random.nextInt(countryModel.getNoOfArmiesCountry() - 1);
							for (int i = 0; i < armiesToBeSent; i++) {
								countryModel.removeNoOfArmiesCountry();
								country.addNoOfArmiesCountry();
							}
							isFortificationDone = true;
							break;
						}

					}
					isFortificationDone = true;
				} else {
					break;
				}

			}
		}
		System.out.println("Random fortification end player name " + playerModel.getPlayerName());
		LOG.info("Random fortification end player name " + playerModel.getPlayerName());

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

		System.out.println("Random attack start player name " + playerModel.getPlayerName());
		LOG.info("Random attack start player name " + playerModel.getPlayerName());
		int noOfTimeToAttack = random.nextInt(playerModel.getPlayerCountryList().size());
		System.out.println("noOfTimeToAttack" + noOfTimeToAttack);
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

									if (targetCountry.getOwner().getPlayerCountryList().size() == 0) {
										playerModel.cardsTransfer(targetCountry.getOwner());
									}

									playerModel.addCountry(targetCountry);
									targetCountry.setOwner(playerModel);
									playerModel.setHasWonTerritory(true);
									if (playerModel.isGameWon(gameModel.getMapHierarchyModel().totalCountries)) {
										gameModel.setIsWon(true);
										return;
									}
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
		System.out.println("Random attack end player name " + playerModel.getPlayerName());
		LOG.info("Random attack end player name " + playerModel.getPlayerName());
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
