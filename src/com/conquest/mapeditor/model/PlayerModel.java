package com.conquest.mapeditor.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel {

	private List<CountryModel> playerCountryList;
	private String playerName;

	public PlayerModel(String playerName) {
		this.playerName = playerName;
		this.playerCountryList = new ArrayList<>();
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void AddCountry(CountryModel countryName) {
		// CountryModel newCountry = new CountryModel(countryName);
		playerCountryList.add(countryName);

	}

	public List<CountryModel> getPlayerCountryList() {
		return playerCountryList;
	}

}
