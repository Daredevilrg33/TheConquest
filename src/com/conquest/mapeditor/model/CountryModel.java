/**
 * 
 */
package com.conquest.mapeditor.model;

import java.util.ArrayList;

/**
 * CountryModel Class Initializes country name String, a ContinentModel
 * {@link ContinentModel} as belongsTo and a ArrayList {@link ArrayList} as
 * listofNeighbours
 * 
 * @author Nancy Goyal
 */
public class CountryModel {

	private String countryName;
	private ContinentModel belongsTo;
	private int noOfArmiesCountry = 1;
	private ArrayList<String> listOfNeighbours;

	/**
	 * ContryModel Constructor Create a new empty ArrayList
	 */
	public CountryModel() {
		this.listOfNeighbours = new ArrayList<>();
	}

	/**
	 * CountryModel Parameterized Constructor
	 * 
	 * @param countryName Name of country
	 */
	public CountryModel(String countryName) {
		// this.countryName = countryName;
		this.setCountryName(countryName);
		this.listOfNeighbours = new ArrayList<>();
	}

	/**
	 * CountryModel Parameterized Constructor
	 * 
	 * @param countryName name of the new country
	 * @param continent   Continent object which contains this country
	 */
	public CountryModel(String countryName, ContinentModel continent) {
		this.setCountryName(countryName);
		this.setBelongsTo(continent);
		this.listOfNeighbours = new ArrayList<>();
	}

	/**
	 * CountryModel Parameterized Constructor
	 * 
	 * @param countryName      Name of country
	 * @param continentModel   Continent object which contains this country
	 * @param listOfNeighbours List of all the neighbours country names
	 */

	public CountryModel(String countryName, ContinentModel continentModel, ArrayList<String> listOfNeighbours) {
		this.countryName = countryName;
		this.belongsTo = continentModel;
		this.listOfNeighbours = listOfNeighbours;
	}

	/**
	 * getCountryName Method Getter Function to get String name
	 * 
	 * @return the countryName Name of country
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * setCountryName Method Setter Function to set name of country
	 * 
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * getBelongsTo Method Method of type ContinentModel
	 * 
	 * @return the belongsTo
	 */
	public ContinentModel getBelongsTo() {
		return belongsTo;
	}

	/**
	 * setBelongsTo Method
	 * 
	 * @param belongsTo the belongsTo to set
	 */
	public void setBelongsTo(ContinentModel belongsTo) {
		this.belongsTo = belongsTo;
	}

	/**
	 * addNeighbour Method Adds a new neighbour to a country countryModel a
	 * CountryModel{@link CountryModel} object
	 * 
	 * @param countryName adds a neighbour country
	 * @return boolean
	 */

	public boolean addNeighbour(String countryName) {
		return this.listOfNeighbours.add(countryName);
	}

	/**
	 * getListOfNeighbours Method
	 * 
	 * @return the listOfNeighbours of ArrayList type{@link ArrayList}
	 */
	public ArrayList<String> getListOfNeighbours() {
		return listOfNeighbours;
	}

	/**
	 * getNoOfArmiesCountry Method Getter Function to get noOfArmiesCountry
	 * 
	 * @return the noOfArmiesCountry Integer
	 */
	public int getNoOfArmiesCountry() {
		return noOfArmiesCountry;
	}

	/**
	 * setNoOfArmiesCountry Method
	 * 
	 * @param noOfArmiesCountry the noOfArmiesCountry to set
	 */
	public void setNoOfArmiesCountry(int noOfArmiesCountry) {
		this.noOfArmiesCountry = noOfArmiesCountry;
	}

	public void addNoOfArmiesCountry() {
		noOfArmiesCountry++;
	}

	public void removeNoOfArmiesCountry() {
		noOfArmiesCountry--;
	}

	/**
	 * searchNeighboursCountry Method A function to find neighbour country to a
	 * country
	 * 
	 * @param countryName name of country to search
	 * @return CountryModel object
	 */

	public String searchNeighboursCountry(String countryName) {
		countryName = countryName.toLowerCase();
		for (String country : listOfNeighbours) {
			if (country.equalsIgnoreCase(countryName)) {
				return country;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountryModel [countryName=" + countryName + ", belongsTo=" + belongsTo + ", noOfArmiesCountry="
				+ noOfArmiesCountry + ", listOfNeighbours=" + listOfNeighbours + "]";
	}

}
