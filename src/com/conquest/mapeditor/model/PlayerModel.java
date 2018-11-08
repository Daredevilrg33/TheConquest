package com.conquest.mapeditor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.conquest.controller.GameWindowController;
import com.conquest.model.GameModel;
import com.conquest.window.AttackPhaseWindow;
import com.conquest.window.GameWindow;

/**
 * The Class PlayerModel.
 * 
 * @author ROHIT GUPTA
 * @version 1.0.0
 */
public class PlayerModel extends Observable {

	/** The player country list. */
	private List<CountryModel> playerCountryList;

	/** The player name. */
	private String playerName;

	/** The no of army for player. */
	private int noOfArmyInPlayer;

	/** The game window. */
	private GameWindow gameWindow;

	/** The risk map model. */
	private GameModel riskMapModel;

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 *
	 * @param playerName   the player name
	 * @param riskMapModel the risk map model
	 */
	public PlayerModel(String playerName, GameModel riskMapModel) {
		this.playerName = playerName;
		this.riskMapModel = riskMapModel;
		this.playerCountryList = new ArrayList<>();
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
	public void removeCountry() {
		playerCountryList.removeAll(playerCountryList);
		updateChanges();
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
		calculateAndAddReinforcementArmy();
	}

	/**
	 * Attack phase.
	 *
	 * @return the string
	 */
	public String AttackPhase() {
		PlayerModel[] players = gameWindow.getPlayers();
		AttackPhaseWindow attackPhaseWindow = new AttackPhaseWindow(riskMapModel, players, this);
		attackPhaseWindow.setVisible(true);

		if (isGameWon(riskMapModel.getRiskGameModel().totalCountries)) {
			gameWindow.labelPhase.setText("Game Over");
			riskMapModel.setGameState(1);
			return this.playerName + " has won the game!";
		}
		FortificationPhase();
		return "success";
	}

	/**
	 * Fortification phase.
	 */
	public void FortificationPhase() {
	}

	/**
	 * calculateAndAddReinforcementArmy method Void Method to calculate and add
	 * Reinforcement armies according to the no of countries per player.
	 * 
	 */
	public void calculateAndAddReinforcementArmy() {
		int reinforcementArmyCount = this.getPlayerCountryList().size() / 3;
		if (reinforcementArmyCount < 3)
			reinforcementArmyCount = 3;
		for (int i = 0; i < reinforcementArmyCount; i++) {
			this.addArmyInPlayer();
		}
	}

	/**
	 * Checking method To check the number of armies.
	 *
	 * @param selectedCountryName type String {@link String}
	 */
	public void placeReinforcedArmy(String selectedCountryName) {
		int i = 0;

		while (true) {
			if (this.getPlayerCountryList().get(i).getCountryName().trim()
					.equalsIgnoreCase(selectedCountryName.trim())) {
				if (this.getnoOfArmyInPlayer() > 0) {
					this.getPlayerCountryList().get(i).addNoOfArmiesCountry();
					this.reduceArmyInPlayer();
					updateReinforcedArmiesUI();
				}
				if (this.getnoOfArmyInPlayer() == 0) {
					AttackPhase();
					break;
				}
				break;
			}
			i++;
		}
	}

	/**
	 * updateReinforcedArmiesUI method Void Method to update the window screen after
	 * any change has been made.
	 */
	public void updateReinforcedArmiesUI() {
		gameWindow.updatePlayerLabel(this.getPlayerName());
		gameWindow.updatePlayerArmies(this.getnoOfArmyInPlayer());
		gameWindow.updateComboBoxCountries(this.getPlayerCountryList());
		gameWindow.invalidate();
		gameWindow.revalidate();

	}

	/**
	 * Update changes.
	 */
	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}

}
