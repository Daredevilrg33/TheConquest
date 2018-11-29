package com.conquest.controller;

import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.conquest.model.GameModel;
import com.conquest.model.PlayerModel;
import com.conquest.window.GameWindow;

/**
 * TournamentController Class This class starts the tournament thread.
 *
 * @author Nancy
 * @version 1.0.0
 */
public class TournamentController {

	/** The game window. */
	private GameWindow gameWindow;

	/** The noOfMaps. */
	private int noOfMaps = 0;

	/** The noOfGames. */
	private int noOfGames = 0;

	/** The noOfTurns. */
	private int noOfTurns = 0;

	/** The game model. */
	private GameModel gameModel;

	/** The player models. */
	private PlayerModel[] playerModels;

	/** The Constant log. */
	private static final Logger LOG = Logger.getLogger(TournamentController.class);

	/**
	 * TournamentController Constructor Constructor created to assign value of
	 * objects.
	 *
	 * @param playerModels the player models
	 * @param noOfMaps     the no of maps
	 * @param noOfGames    the no of games
	 * @param noOfTurns    the no of turns
	 */
	public TournamentController(PlayerModel[] playerModels, int noOfMaps, int noOfGames, int noOfTurns) {
		this.playerModels = playerModels;
		this.noOfMaps = noOfMaps;
		this.noOfGames = noOfGames;
		this.noOfTurns = noOfTurns;

		System.out.println("\n\n\n\n noOfMaps " + noOfMaps + " noOfGames" + noOfGames + " noOfTurns" + noOfTurns);
	}

	/**
	 * Start tournament.
	 */
	public void startTournament() {
		// begin the tournament
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
			@Override
			protected Void doInBackground() {
				for (int i = 0; i < noOfMaps; i++) {
					for (int j = 0; j < noOfGames; j++) {

						//
					}
				}
				return null;
			}

			@Override
			protected void process(List<Integer> chunks) {
			}

			@Override
			protected void done() {
			}
		};
		worker.execute();
	}
}
