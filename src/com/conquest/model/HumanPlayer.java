/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.conquest.window.FortificationWindow;

/**
 * The Class HumanPlayer.
 *
 * @author Rohit Gupta
 */
public class HumanPlayer implements Serializable, Strategy {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 1L;

	/** The Constant log. */
	private static final Logger LOG = Logger.getLogger(HumanPlayer.class);

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
		System.out.println("Human reinforcement player name " + playerModel.getPlayerName());
		LOG.info("Human reinforcement player name " + playerModel.getPlayerName());

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
		FortificationWindow fortificationWindow = new FortificationWindow(gameModel, playerModel);
		fortificationWindow.setVisible(true);
		System.out.println("Human fortification player name " + playerModel.getPlayerName());
		LOG.info("Human fortification player name " + playerModel.getPlayerName());

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
		System.out.println("Human attack player name " + playerModel.getPlayerName());
		LOG.info("Human attack player name " + playerModel.getPlayerName());

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

	}

}
