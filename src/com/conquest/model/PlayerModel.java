package com.conquest.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.conquest.mapeditor.model.CountryModel;

/**
 * The Class PlayerModel.
 * 
 * @author ROHIT GUPTA
 * @version 1.0.0
 */
public class PlayerModel extends Observable implements Serializable {

	private static final long serialVersionUID = 5L;

	/** The player country list. */
	private List<CountryModel> playerCountryList;

	/** The player name. */
	private String playerName;

	/** The no of army for player. */
	private int noOfArmyInPlayer;

//	/** The game window. */
//	private transient GameWindow gameWindow;

	/** The has won territory. */
	private boolean hasWonTerritory;

	/** The cards. */
	private int[] cards;
	private Strategy strategy;

	private PlayerType playerType;
	private static final Logger log = Logger.getLogger(PlayerModel.class);

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 *
	 * @param playerName the player name
	 * @param gameModel  the risk map model
	 */
	public PlayerModel(String playerName, PlayerType playerType) {
		this.playerName = playerName;
		cards = new int[] { 0, 0, 0 };
		this.playerCountryList = new ArrayList<>();
		this.hasWonTerritory = false;
		this.playerType = playerType;
		updateChanges();
	}

	/**
	 * noOfArmyinPlayer Method No of army for player.
	 * 
	 * @param number the number
	 */

	public void noOfArmyInPlayer(int number) {
		this.noOfArmyInPlayer = number;
		updateChanges();
	}

	/**
	 * Reduce army for player.
	 */
	public void reduceArmyInPlayer() {
		this.noOfArmyInPlayer--;
		updateChanges();
	}

	/**
	 * Adds the army in player.
	 */
	public void addArmyInPlayer() {
		this.noOfArmyInPlayer++;
		updateChanges();
	}

	/**
	 * Adds the control value to no of army.
	 *
	 * @param controlValue the control value
	 */
	public void addControlValueToNoOfArmy(int controlValue) {
		this.noOfArmyInPlayer = this.noOfArmyInPlayer + controlValue;
		updateChanges();
	}

	/**
	 * Gets the no of army for player.
	 * 
	 * @return the no of army for player
	 */
	public int getnoOfArmyInPlayer() {
		return noOfArmyInPlayer;
	}

	/**
	 * getPlayerName Method Gets the player name.
	 * 
	 * @return the player name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the strategy
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * setPlayerName Method Sets the player name.
	 * 
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		updateChanges();
	}

	/**
	 * AddCountry Method Adds the country.
	 * 
	 * @param countryName the country name
	 */
	public void addCountry(CountryModel countryName) {
		playerCountryList.add(countryName);
		updateChanges();
	}

	/**
	 * Removes the country.
	 */
	public void removeAllCountry() {
		playerCountryList.removeAll(playerCountryList);
		updateChanges();
	}

	/**
	 * Removes the country.
	 *
	 * @param countryModel the country model
	 */
	public void removeCountry(CountryModel countryModel) {
		if (playerCountryList.contains(countryModel))
			playerCountryList.remove(countryModel);

	}

	/**
	 * Gets the player country list.
	 * 
	 * @return the player country list
	 */
	public List<CountryModel> getPlayerCountryList() {
		return playerCountryList;
	}

	/**
	 * Method to add one type of card.
	 * 
	 * @param type card type
	 */
	public void increaseCard(int type) {
		this.cards[type]++;
		updateChanges();
	}

	/**
	 * Method to get cards.
	 * 
	 * @return cards
	 */
	public int[] getCards() {
		return cards;
	}

	/**
	 * Method to get cards.
	 * 
	 * @return cards
	 */
	public int getTotalCards() {
		return cards[0] + cards[1] + cards[1];
	}

	/**
	 * The function to judge if player can exchange cards.
	 *
	 * @return true if can handIn the cards
	 */
	public boolean canHandIn() {
		if (cards[0] >= 3 || cards[1] >= 3 || cards[2] >= 3)
			return true;
		else
			return (cards[0] >= 1 && cards[1] >= 1 && cards[2] >= 1);
	}

	/**
	 * The function to return cards the current player possesses.
	 *
	 * @return cardsString
	 */
	public String cardsString() {
		return String.valueOf(cards[0]) + " Infantry, " + String.valueOf(cards[1]) + " Cavalry and "
				+ String.valueOf(cards[2]) + " Artillery";
	}

	/**
	 * The function to exchange the cards.
	 */

//	public void handInCards() {
//		gameModel.setGameStatus("Cards HandIn");
//		log.info("Cards HandIn");
//		if (cards[0] >= 3 || cards[1] >= 3 || cards[2] >= 3) {
//			if (cards[0] >= 3) {
//				cards[0] = 0;
//				for (int i = 0; i < 3; i++) {
//					CardsModel card = new CardsModel("Infantry", 0);
//					gameModel.getTotalCards().add(card);
//				}
//			}
//			if (cards[1] >= 3) {
//				cards[1] = 0;
//				for (int i = 0; i < 3; i++) {
//					CardsModel card = new CardsModel("Cavalry", 1);
//					gameModel.getTotalCards().add(card);
//				}
//			}
//			if (cards[2] >= 3) {
//				cards[2] = 0;
//				for (int i = 0; i < 3; i++) {
//					CardsModel card = new CardsModel("Artillery", 2);
//					gameModel.getTotalCards().add(card);
//				}
//			}
//		} else {
//			if (cards[0] >= 1) {
//				cards[0]--;
//				CardsModel card = new CardsModel("Infantry", 0);
//				gameModel.getTotalCards().add(card);
//			}
//			if (cards[1] >= 1) {
//				cards[1]--;
//				CardsModel card = new CardsModel("Cavalry", 1);
//				gameModel.getTotalCards().add(card);
//			}
//			if (cards[2] >= 1) {
//				cards[2]--;
//				CardsModel card = new CardsModel("Artillery", 2);
//				gameModel.getTotalCards().add(card);
//			}
//		}
//		gameModel.setHandInCounter(gameModel.getHandInCounter() + 1);
//		gameModel.getCurrPlayer().addArmies(gameModel.getHandInCounter() * 5);
//		updateChanges();
//	}

	/**
	 * Search a country by the country Name.
	 * 
	 * @param countryName : Name of the country to be searched.
	 * @return Returns the Country Model.
	 */

	public CountryModel searchCountry(String countryName) {
		for (CountryModel country : playerCountryList) {
			if (country.getCountryName().trim().equalsIgnoreCase(countryName.trim())) {
				return country;
			}
		}
		return null;
	}

	/**
	 * Method to add armies to player on cards Exchange.
	 * 
	 * @param armies armies to add
	 */
	public void addArmies(int armies) {
		this.noOfArmyInPlayer += armies;
		updateChanges();
	}

	/**
	 * This method is to move armies from one country to another.
	 *
	 * @param sourceCountry the source country
	 * @param destCountry   the destination country
	 * @param armies        armies number
	 */
	public void moveArmies(CountryModel sourceCountry, CountryModel destCountry, int armies) {
		destCountry.setNoOfArmiesCountry(sourceCountry.getNoOfArmiesCountry() + armies);
		sourceCountry.setNoOfArmiesCountry(destCountry.getNoOfArmiesCountry() - armies);
	}

	/**
	 * The function to judge if player win.
	 *
	 * @param countryNum number of countries
	 * @return true if player win
	 */
	public boolean isGameWon(int countryNum) {
		if (this.playerCountryList.size() == countryNum)
			return true;
		else
			return false;
	}

	/**
	 * Checks if is checks for won territory.
	 *
	 * @return the hasWonTerritory
	 */
	public boolean isHasWonTerritory() {
		return hasWonTerritory;
	}

	/**
	 * Sets the checks for won territory.
	 *
	 * @param hasWonTerritory the hasWonTerritory to set
	 */
	public void setHasWonTerritory(boolean hasWonTerritory) {
		this.hasWonTerritory = hasWonTerritory;
	}

	/**
	 * calculateAndAddReinforcementArmy method Void Method to calculate and add
	 * Reinforcement armies according to the no of countries per player.
	 * 
	 */
	public void calculateAndAddReinforcementArmy() {
		log.info("Calculating and Adding Reinforcement Armies");
		int reinforcementArmyCount = this.getPlayerCountryList().size() / 3;
		if (reinforcementArmyCount < 3)
			reinforcementArmyCount = 3;
		for (int i = 0; i < reinforcementArmyCount; i++) {
			this.addArmyInPlayer();
		}
	}

	/**
	 * Update changes.
	 */
	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * @return the playerType
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}

	/**
	 * @param playerType the playerType to set
	 */
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public void fortificationPhase(GameModel gameModel) {
		// TODO Auto-generated method stub
		if (this.hasWonTerritory) {
			CardsModel card = gameModel.generateRandomCard();
			if (card != null) {
				gameModel.setGameStatus("Assigning one card");
				log.info("Assigning one card");
				this.increaseCard(card.getType());
			}

		}
		
//		FortificationWindow fortificationWindow = new FortificationWindow(gameModel, this);
//		fortificationWindow.setVisible(true);

	}

	public String attackPhase(GameModel gameModel) {
		// TODO Auto-generated method stub
		PlayerModel[] players = gameModel.getPlayers();

		if (getTotalCards() >= 5) {
			JOptionPane.showMessageDialog(null,
					"You have either 5 or more than 5 cards in possession. Please exchange before proceeding further");
			return "";
		} else {

			strategy.attackPhase(gameModel, this);
			return "success";
		}
	}

	public void assignInitialArmyToCountry(GameModel gameModel) {
		// TODO Auto-generated method stub
		strategy.assignInitialArmyToCountry(gameModel, this);
		gameModel.increaseTurn();
		gameModel.moveToNextPlayer();
	}

	public void reinforcementPhase(GameModel gameModel) {
		// TODO Auto-generated method stub

		log.info("Reinforcement Phase starts of Player" + getPlayerName());
		System.out.println("Reinforcement Phase starts of Player" + getPlayerName() + getPlayerType());

		calculateAndAddReinforcementArmy();
		strategy.reinforcementPhase(gameModel, this);
	}

}
