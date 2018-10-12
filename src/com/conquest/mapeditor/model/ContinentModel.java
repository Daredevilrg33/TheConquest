package com.conquest.mapeditor.model;

import java.util.ArrayList;

/**
 * @author Nancy Goyal
 *
 */
public class ContinentModel {

	private String continentName;
	private ArrayList<CountryModel> countriesList;
	private int noOfArmies;

	/**
	 * Constructor for Continent class.
	 * 
	 * @param continentName name of the new continent
	 */
	public ContinentModel(String continentName) {
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

	/**
	 * Method to add a country to countryList.
	 * 
	 * @param country new country object
	 */
	public void addCountry(CountryModel country) {
		countriesList.add(country);
	}

	/**
	 * Method to search a country in this continent.
	 * 
	 * @param countryName the name of the country
	 * @return the country object found in this continent or null if can't find
	 */
	public CountryModel searchCountry(String countryName) {
		countryName = countryName.toLowerCase();
		for (CountryModel loopCountry : countriesList) {
			if (loopCountry.getCountryName().equals(countryName)) {
				return loopCountry;
			}
		}
		return null;
	}

	/**
	 * @param noOfArmies the noOfArmies to set
	 */
	public void setNoOfArmies(int noOfArmies) {
		this.noOfArmies = noOfArmies;
	}

	/**
	 * @return the noOfArmies
	 */
	public int getNoOfArmies() {
		return noOfArmies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContinentModel [continentName=" + continentName + ", countriesList=" + countriesList + ", noOfArmies="
				+ noOfArmies + "]";
	}

}
