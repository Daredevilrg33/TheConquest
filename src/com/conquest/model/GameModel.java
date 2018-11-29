package com.conquest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import org.apache.log4j.Logger;

import com.conquest.controller.GameWindowController;
import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.window.GameWindow;

/**
 * The Class GameModel.
 *
 * @author Nancy Goyal
 * @version 1.0.0
 */
public class GameModel extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The players. */
	private PlayerModel[] players;

	/** The current player. */
	private PlayerModel currPlayer;

	/** The game state. */
	private int isWon = 0; // 0=on 1=won

	/** The risk game model. */
	private MapHierarchyModel mapHierarchyModel;

	/** The gameStatus. */
	private String gameStatus = "";

	/** The gameSavePhase. */
	private int gamePhaseStage = 0;// 0=startup 1=Reinforcement 2=Attack 3=Fortification

	/** The turn. */
	private int turn;

	/** The handInCounter. */
	private int handInCounter = 0;

	/** The cards. */
	private ArrayList<CardsModel> totalCards;

	/** The random generator. */
	private transient Random randomGenerator;

	/**
	 * Instantiates a new game model.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param playerModels      the player models
	 */
	public GameModel(MapHierarchyModel mapHierarchyModel, PlayerModel[] playerModels) {
		this.players = playerModels;
		this.mapHierarchyModel = mapHierarchyModel;
		randomGenerator = new Random();
		initializingCardsModel(mapHierarchyModel);
		this.currPlayer = playerModels[0];
		updateChanges();
	}

	/**
	 * Initializing cards model.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 */
	public void initializingCardsModel(MapHierarchyModel mapHierarchyModel) {
		String[] names = { "Infantry", "Cavalry", "Artillery" };
		int[] types = { 0, 1, 2 };
		int j = 0;
		totalCards = new ArrayList<>();
		String name = names[j];
		int type = types[j];
		for (int i = 0; i < mapHierarchyModel.totalCountries; i++) {
			CardsModel card = new CardsModel(name, type);
			totalCards.add(card);
			name = names[j++];
			if (j == 2) {
				j = 0;
			}
		}
	}

	/**
	 * Generate random card.
	 *
	 * @return the cards model
	 */

	public CardsModel generateRandomCard() {
		if (totalCards.size() > 0) {
			int index = randomGenerator.nextInt(totalCards.size());
			ArrayList<CardsModel> cardsList = totalCards;
			CardsModel newCard = cardsList.get(index);
			cardsList.remove(index);
			return newCard;
		} else
			return null;

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
		updateChanges();
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
		updateChanges();
	}

	/**
	 * @return the isWon
	 */
	public int getIsWon() {
		return isWon;
	}

	/**
	 * @param isWon the isWon to set
	 */
	public void setIsWon(int isWon) {
		this.isWon = isWon;
		updateChanges();
	}

	/**
	 * Gets the risk game model.
	 *
	 * @return the risk game model
	 */
	public MapHierarchyModel getMapHierarchyModel() {
		return mapHierarchyModel;
	}

	/**
	 * Sets the risk game model.
	 *
	 * @param mapHierarchyModel the new map hierarchy model
	 */

	public void setMapHierarchyModel(MapHierarchyModel mapHierarchyModel) {
		this.mapHierarchyModel = mapHierarchyModel;
		updateChanges();
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
		if (turn == players.length)
			turn = 0;
		turn++;
	}

	/**
	 * Gets the hand in counter.
	 *
	 * @return the handInCounter
	 */
	public int getHandInCounter() {
		return handInCounter;
	}

	/**
	 * Sets the hand in counter.
	 *
	 * @param handInCounter the handInCounter to set
	 */
	public void setHandInCounter(int handInCounter) {
		this.handInCounter = handInCounter;
	}

	/**
	 * Gets the total cards.
	 *
	 * @return the total cards
	 */
	public ArrayList<CardsModel> getTotalCards() {
		return totalCards;
	}

	/**
	 * Sets the total cards.
	 *
	 * @param totalCards the new total cards
	 */
	public void setTotalCards(ArrayList<CardsModel> totalCards) {
		this.totalCards = totalCards;
		updateChanges();
	}

	/**
	 * @return the gameStatus
	 */
	public String getGameStatus() {
		return gameStatus;
	}

	/**
	 * @param gameStatus the gameStatus to set
	 */
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * @return the gamePhaseStage
	 */
	public int getGamePhaseStage() {
		return gamePhaseStage;
	}

	/**
	 * @param gamePhaseStage the gamePhaseStage to set
	 */
	public void setGamePhaseStage(int gamePhaseStage) {
		this.gamePhaseStage = gamePhaseStage;
//		updateChanges();
	}

	/**
	 * Move to next player.
	 */
	public void moveToNextPlayer() {
		if (currPlayer == players[players.length - 1]) {
			currPlayer = players[0];
		} else
			currPlayer = players[turn - 1];
		if (!(currPlayer.getPlayerCountryList().size() > 0)) {
			increaseTurn();
			moveToNextPlayer();
		}
		updateChanges();
	}

	/**
	 * Update changes.
	 */
	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}

	public void fortificationPhase() {
		currPlayer.fortificationPhase(this);
	}

	public String attackPhase() {
		return currPlayer.attackPhase(this);

	}

	public void reinforcementPhase() {
		this.setGameStatus("Reinforcement Phase starts");
		this.setGamePhaseStage(1);

	}
}
