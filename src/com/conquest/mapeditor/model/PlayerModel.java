package com.conquest.mapeditor.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel {
	
	private List<CountryModel> playerCountryList;
	
	public PlayerModel(){
		this.playerCountryList = new ArrayList<>();
	}
	
	private int i=0;
	public int getI() {
		return i;
	}
	
	public void AddCountry(CountryModel countryName) {
		// CountryModel newCountry = new CountryModel(countryName);
		playerCountryList.add(countryName);
		
	}
	
	public List<CountryModel> getPlayerCountryList() {
		return playerCountryList;
	}

}
