/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.conquest.mapeditor.model.CountryModel;

/**
 * The Class BenevolentPlayer.
 *
 * @author Rohit Gupta
 */
public class BenevolentPlayer implements Serializable, Strategy {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 9L;

	/** The Constant log. */
	private static final Logger LOG = Logger.getLogger(BenevolentPlayer.class);

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
		System.out.println("Benevolent reinforcement start player name " + playerModel.getPlayerName());
		LOG.info("Benevolent reinforcement start player name " + playerModel.getPlayerName());
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
		System.out.println("Benevolent reinforcement end player name " + playerModel.getPlayerName());
		LOG.info("Benevolent reinforcement end player name " + playerModel.getPlayerName());

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
		System.out.println("Benevolent fortification start player name " + playerModel.getPlayerName());
		LOG.info("Benevolent fortification start player name " + playerModel.getPlayerName());

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
		System.out.println("Benevolent fortification end player name " + playerModel.getPlayerName());
		LOG.info("Benevolent fortification end player name " + playerModel.getPlayerName());

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
		System.out.println("Benevolent attack start player name " + playerModel.getPlayerName());
		LOG.info("Benevolent attack start player name " + playerModel.getPlayerName());

		if (playerModel.isGameWon(gameModel.getMapHierarchyModel().totalCountries)) {

			gameModel.setIsWon(true);
			return;
		}
		gameModel.setGameStatus("Fortification Phase starts");
		gameModel.setGamePhaseStage(3);
		fortificationPhase(gameModel, playerModel);
		System.out.println("Benevolent attack end player name " + playerModel.getPlayerName());
		LOG.info("Benevolent attack end player name " + playerModel.getPlayerName());

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
	 * @return countryModel the country with min armies
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
