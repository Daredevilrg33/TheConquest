/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.conquest.mapeditor.model.CountryModel;

/**
 * The Class CheaterPlayer.
 *
 * @author Rohit Gupta
 */
public class CheaterPlayer implements Serializable, Strategy {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 1L;

	/** The Constant log. */
	private static final Logger LOG = Logger.getLogger(CheaterPlayer.class);

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
		System.out.println("Cheater reinforcement start player name " + playerModel.getPlayerName());
		LOG.info("Cheater reinforcement start player name " + playerModel.getPlayerName());

		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			int armies = countryModel.getNoOfArmiesCountry();
			armies = armies * 2;
			countryModel.setNoOfArmiesCountry(armies);
		}
		System.out.println("Cheater reinforcement end player name " + playerModel.getPlayerName());
		LOG.info("Cheater reinforcement end player name " + playerModel.getPlayerName());
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
		System.out.println("Cheater fortification start player name " + playerModel.getPlayerName());
		LOG.info("Cheater fortification start player name " + playerModel.getPlayerName());

		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			for (String neighbourCountryName : countryModel.getListOfNeighbours()) {
				CountryModel country = playerModel.searchCountry(neighbourCountryName);
				// if country is null i.e. it doesnot belong to this player
				if (country == null) {
					int noOfArmies = countryModel.getNoOfArmiesCountry();
					noOfArmies = noOfArmies * 2;
					countryModel.setNoOfArmiesCountry(noOfArmies);
				}
			}
		}
		System.out.println("Cheater fortification end player name " + playerModel.getPlayerName());
		LOG.info("Cheater fortification end player name " + playerModel.getPlayerName());
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
		System.out.println("Cheater attack start player name " + playerModel.getPlayerName());
		LOG.info("Cheater attack start player name " + playerModel.getPlayerName());

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
			for (String countryName : countryModel.getListOfNeighbours()) {
				CountryModel country = gameModel.getMapHierarchyModel().searchCountry(countryName);
				countryList.add(country);
			}
		}

		// CHeck this code
		for (CountryModel country : countryList) {
			for (PlayerModel player : gameModel.getPlayers()) {
				for (CountryModel countryModels : player.getPlayerCountryList()) {
					if (countryModels.getCountryName().trim().equalsIgnoreCase(country.getCountryName().trim())) {
						player.removeCountry(country);
						playerModel.addCountry(country);
						playerModel.setHasWonTerritory(true);
						break;
					}
				}
			}
		}
		if (playerModel.isGameWon(gameModel.getMapHierarchyModel().getCountryList().size())) {

			gameModel.setIsWon(true);
			return;
		}
		System.out.println("Cheater attack end player name " + playerModel.getPlayerName());
		LOG.info("Cheater attack end player name " + playerModel.getPlayerName());

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
		if (playerModel.getnoOfArmyInPlayer() > 0) {
			for (CountryModel countryModel : playerModel.getPlayerCountryList()) {
				countryModel.addNoOfArmiesCountry();

			}
			playerModel.reduceArmyInPlayer();
		}

	}
}
