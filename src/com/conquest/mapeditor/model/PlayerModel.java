package com.conquest.mapeditor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class PlayerModel.
 * 
 * @author ROHIT GUPTA
 * @version 1.0.0
 */
public class PlayerModel {

	/** The player country list. */
	private List<CountryModel> playerCountryList;

	/** The player name. */
	private String playerName;

	/** The no of army for player. */
	private int noOfArmyinPlayer;

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 * 
	 * @param playerName the player name
	 */
	public PlayerModel(String playerName) {
		this.playerName = playerName;
		this.playerCountryList = new ArrayList<>();
	}

	/**
	 * PlayerModel Constructor Instantiates a new player model.
	 * 
	 * @param playerName            the player name
	 * @param countryModelArrayList array list of coutries of the player.
	 */

	public PlayerModel(String playerName, ArrayList<CountryModel> countryModelArrayList) {
		this.playerName = playerName;
		this.playerCountryList = new ArrayList<>();
		this.playerCountryList.addAll(countryModelArrayList);
	}

	/**
	 * noOfArmyinPlayer Method No of army for player.
	 * 
	 * @param number the number
	 */

	public void noOfArmyinPlayer(int number) {
		this.noOfArmyinPlayer = number;
	}

	/**
	 * Reduce army for player.
	 */
	public void reduceArmyinPlayer() {
		this.noOfArmyinPlayer--;
	}

	/**
	 * Gets the no of army for player.
	 * 
	 * @return the no of army for player
	 */
	public int getnoOfArmyinPlayer() {
		return noOfArmyinPlayer;
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
	}

	/**
	 * AddCountry Method Adds the country.
	 * 
	 * @param countryName the country name
	 */
	public void AddCountry(CountryModel countryName) {
		// CountryModel newCountry = new CountryModel(countryName);
		playerCountryList.add(countryName);

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

}
