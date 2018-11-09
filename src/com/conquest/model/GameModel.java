package com.conquest.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.conquest.controller.GameWindowController;
import com.conquest.mapeditor.model.ContinentModel;
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
	private MapHierarchyModel mapHierarchyModel;

	/** The turn. */
	private int turn;
	
	/** The handInCounter. */
	private int handInCounter=0;

	/** The cards. */
	private ArrayList<CardsModel> totalCards;

	/** The random generator. */
	private Random randomGenerator;

	/**
	 * Instantiates a new game model.
	 *
	 * @param mapHierarchyModel    the map hierarchy model
	 * @param gameWindowController the game window controller
	 */
	public GameModel(MapHierarchyModel mapHierarchyModel, PlayerModel[] playerModels) {
		this.players = playerModels;

		this.mapHierarchyModel = mapHierarchyModel;
		randomGenerator = new Random();
		initializingCardsModel(mapHierarchyModel);
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
			CardsModel card = new CardsModel(name, type, this);
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

		int index = randomGenerator.nextInt(totalCards.size());
		ArrayList<CardsModel> cardsList = totalCards;
		CardsModel newCard = cardsList.get(index);
		cardsList.remove(index);
		return newCard;
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
	
	

	/**
	 * @return the handInCounter
	 */
	public int getHandInCounter() {
		return handInCounter;
	}

	/**
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
	}

	public void moveToNextPlayer() {
		if (currPlayer == players[players.length - 1]) {
			currPlayer = players[0];
		} else
			currPlayer = players[turn - 1];
	}
}
