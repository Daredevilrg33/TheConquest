package com.conquest.model;

import java.util.Observable;

import com.conquest.controller.GameWindowController;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.GameWindow;

/**
 * The Class GameModel.
 *
 * @author Nancy Goyal
 * @version 1.0.0
 */
public class GameModel extends Observable {
	
	/** The players. */
	private PlayerModel[] players;
	
	/** The current player. */
	private PlayerModel currPlayer;
	
	/** The game state. */
	private int gameState = 0; // 0=on 1=won
	
	/** The risk game model. */
	private MapHierarchyModel riskGameModel;
	
	/** The turn. */
	private int turn;

	/**
	 * Instantiates a new game model.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param gameWindowController the game window controller
	 */
	public GameModel(MapHierarchyModel mapHierarchyModel, GameWindowController gameWindowController) {
		this.players = gameWindowController.getPlayers();
		this.riskGameModel = mapHierarchyModel;
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public PlayerModel[] getPlayers() {
		return players;
	}

	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(PlayerModel[] players) {
		this.players = players;
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public PlayerModel getCurrPlayer() {
		return currPlayer;
	}

	/**
	 * Sets the current player.
	 *
	 * @param currPlayer the new current player
	 */
	public void setCurrPlayer(PlayerModel currPlayer) {
		this.currPlayer = currPlayer;
	}

	/**
	 * Gets the game state.
	 *
	 * @return the game state
	 */
	public int getGameState() {
		return gameState;
	}

	/**
	 * Sets the game state.
	 *
	 * @param gameState the new game state
	 */
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	/**
	 * Gets the risk game model.
	 *
	 * @return the risk game model
	 */
	public MapHierarchyModel getRiskGameModel() {
		return riskGameModel;
	}

	/**
	 * Sets the risk game model.
	 *
	 * @param riskGameModel the new risk game model
	 */
	public void setRiskGameModel(MapHierarchyModel riskGameModel) {
		this.riskGameModel = riskGameModel;
	}

	/**
	 * The getter of turn, used to return turn count.
	 *
	 * @return turn the count of turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Method to increase the count of turn.
	 */
	public void increaseTurn() {
		turn++;
		setChanged();
		notifyObservers(2);
	}

}
