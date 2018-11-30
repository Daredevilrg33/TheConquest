package com.conquest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture.AsynchronousCompletionTask;

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
	public TournamentController(int noOfMaps, int noOfGames, int noOfTurns) {

		this.noOfMaps = noOfMaps;
		this.noOfGames = noOfGames;
		this.noOfTurns = noOfTurns;

		System.out.println("\n\n\n\n noOfMaps " + noOfMaps + " noOfGames" + noOfGames + " noOfTurns" + noOfTurns);
	}

	/**
	 * Start tournament.
	 */
	public void startTournament(GameModel gameModel) {
		// begin the tournament
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
			@Override
			protected Void doInBackground() {
				gameModel.setMaxTurnsAllowed(noOfTurns);
				for (PlayerModel player : gameModel.getPlayers()) {
					for (int i = 0; i < player.getnoOfArmyInPlayer(); i++) {
						player.assignInitialArmyToCountry(gameModel);
					}
				}
				gameModel.getCurrPlayer().reinforcementPhase(gameModel);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
