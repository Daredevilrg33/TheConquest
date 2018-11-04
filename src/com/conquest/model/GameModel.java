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
	private PlayerModel[] players;
	private PlayerModel currPlayer;
	private int gameState = 0; // 0=on 1=won
	private MapHierarchyModel riskGameModel;
	private int turn;

	public GameModel(MapHierarchyModel mapHierarchyModel, GameWindowController gameWindowController) {
		this.players = gameWindowController.getPlayers();
		this.riskGameModel = mapHierarchyModel;
	}

	public PlayerModel[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerModel[] players) {
		this.players = players;
	}

	public PlayerModel getCurrPlayer() {
		return currPlayer;
	}

	public void setCurrPlayer(PlayerModel currPlayer) {
		this.currPlayer = currPlayer;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public MapHierarchyModel getRiskGameModel() {
		return riskGameModel;
	}

	public void setRiskGameModel(MapHierarchyModel riskGameModel) {
		this.riskGameModel = riskGameModel;
	}

	/**
	 * The getter of turn, used to return turn count
	 * 
	 * @return turn the count of turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Method to increase the count of turn
	 */
	public void increaseTurn() {
		turn++;
		setChanged();
		notifyObservers(2);
	}

}
