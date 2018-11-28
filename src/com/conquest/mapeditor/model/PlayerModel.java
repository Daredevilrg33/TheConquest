package com.conquest.mapeditor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.conquest.model.CardsModel;
import com.conquest.model.GameModel;
import com.conquest.window.AttackPhaseWindow;
import com.conquest.window.FortificationWindow;
import com.conquest.window.GameWindow;

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

	/** The game window. */
	private transient GameWindow gameWindow;

	/** The risk map model. */
	private GameModel gameModel;

	/** The has won territory. */
	private boolean hasWonTerritory;

	/** The cards. */
	private int[] cards;

	static Logger log = Logger.getLogger(PlayerModel.class.getName());

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 *
	 * @param playerName the player name
	 * @param gameModel  the risk map model
	 */
	public PlayerModel(String playerName, GameModel gameModel) {
		this.playerName = playerName;
		this.gameModel = gameModel;
		cards = new int[] { 0, 0, 0 };
		this.playerCountryList = new ArrayList<>();
		this.hasWonTerritory = false;
		updateChanges();
	}

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 * 
	 * @param playerName            the player name
	 * @param countryModelArrayList array list of countries of the player.
	 */

	public PlayerModel(String playerName, ArrayList<CountryModel> countryModelArrayList) {
		this.playerName = playerName;
		this.playerCountryList = new ArrayList<>();
		this.playerCountryList.addAll(countryModelArrayList);
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

	public void handInCards() {
		gameModel.setGameStatus("Cards HandIn");
		log.info("Cards HandIn");
		if (cards[0] >= 3 || cards[1] >= 3 || cards[2] >= 3) {
			if (cards[0] >= 3) {
				cards[0] = 0;
				for (int i = 0; i < 3; i++) {
					CardsModel card = new CardsModel("Infantry", 0);
					gameModel.getTotalCards().add(card);
				}
			}
			if (cards[1] >= 3) {
				cards[1] = 0;
				for (int i = 0; i < 3; i++) {
					CardsModel card = new CardsModel("Cavalry", 1);
					gameModel.getTotalCards().add(card);
				}
			}
			if (cards[2] >= 3) {
				cards[2] = 0;
				for (int i = 0; i < 3; i++) {
					CardsModel card = new CardsModel("Artillery", 2);
					gameModel.getTotalCards().add(card);
				}
			}
		} else {
			if (cards[0] >= 1) {
				cards[0]--;
				CardsModel card = new CardsModel("Infantry", 0);
				gameModel.getTotalCards().add(card);
			}
			if (cards[1] >= 1) {
				cards[1]--;
				CardsModel card = new CardsModel("Cavalry", 1);
				gameModel.getTotalCards().add(card);
			}
			if (cards[2] >= 1) {
				cards[2]--;
				CardsModel card = new CardsModel("Artillery", 2);
				gameModel.getTotalCards().add(card);
			}
		}
		gameModel.setHandInCounter(gameModel.getHandInCounter() + 1);
		gameModel.getCurrPlayer().addArmies(gameModel.getHandInCounter() * 5);
		updateChanges();
	}

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
	 * Game phase.
	 *
	 * @param gameWindow the game window
	 */
	public void gamePhase(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		reinforcementPhase();
	}

	/**
	 * Reinforcement phase.
	 */
	public void reinforcementPhase() {
		gameModel.setGameStatus("Reinforcement Phase starts");
		log.info("Reinforcement Phase starts");
		calculateAndAddReinforcementArmy();
	}

	/**
	 * Attack phase.
	 *
	 * @return the string
	 */
	public String AttackPhase() {

		PlayerModel[] players = gameWindow.getPlayers();

		if (getTotalCards() >= 5) {
			JOptionPane.showMessageDialog(null,
					"You have either 5 or more than 5 cards in possession. Please exchange before proceeding further");
			return "";
		} else {
			AttackPhaseWindow attackPhaseWindow = new AttackPhaseWindow(gameModel, players, this);
			attackPhaseWindow.setVisible(true);

		}
		return "success";
	}

	/**
	 * Fortification phase.
	 */
	public void fortificationPhase() {

		if (this.hasWonTerritory) {
			CardsModel card = gameModel.generateRandomCard();
			if (card != null) {
				gameModel.setGameStatus("Assigning one card");
				log.info("Assigning one card");
				this.increaseCard(card.getType());
			}

		}
		FortificationWindow fortificationWindow = new FortificationWindow(gameModel, this);
		fortificationWindow.setVisible(true);

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
		gameModel.setGameSavePhase(1);
		gameModel.setGameStatus("Calculating and Adding Reinforcement Armies");
		log.info("Calculating and Adding Reinforcement Armies");
		int reinforcementArmyCount = this.getPlayerCountryList().size() / 3;
		if (reinforcementArmyCount < 3)
			reinforcementArmyCount = 3;
		for (int i = 0; i < reinforcementArmyCount; i++) {
			this.addArmyInPlayer();
		}
	}

	/**
	 * Gets the game model.
	 *
	 * @return the gameModel
	 */
	public GameModel getGameModel() {
		return gameModel;
	}

	/**
	 * Sets the game model.
	 *
	 * @param gameModel the gameModel to set
	 */
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	/**
	 * Sets the game window.
	 *
	 * @param gameWindow the gameWindow to set
	 */
	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}

	/**
	 * Update changes.
	 */
	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
