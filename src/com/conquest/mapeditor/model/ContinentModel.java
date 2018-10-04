package com.conquest.mapeditor.model;

import java.util.ArrayList;

/**
 * @author Nancy Goyal
 *
 */
public class ContinentModel {

	private String continentName;
	private ArrayList<CountryModel> countriesList;
	
	/**
	 * Constructor for Continent class.
	 * @param continentName	name of the new continent
	 */
	public ContinentModel(String continentName){
		this.setContinentName(continentName);
		this.countriesList = new ArrayList<CountryModel>();
	}

	/**
	 * @return the continentName
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * @param continentName the continentName to set
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * @return the countriesList
	 */
	public ArrayList<CountryModel> getCountriesList() {
		return countriesList;
	}

	/**
	 * @param countriesList the countriesList to set
	 */
	public void setCountriesList(ArrayList<CountryModel> countriesList) {
		this.countriesList = countriesList;
	}
	
	
	
	
	
}
