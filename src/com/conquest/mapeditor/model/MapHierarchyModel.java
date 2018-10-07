package com.conquest.mapeditor.model;

import java.util.ArrayList;

/**
 * @author Nancy Goyal
 *
 */
public class MapHierarchyModel {

	private String conquestMapName = "Default";
	private ArrayList<ContinentModel> continentsList;

	public MapHierarchyModel() {
		this.continentsList = new ArrayList<ContinentModel>();
	}

	/**
	 * @return the conquestMapName
	 */
	public String getConquestMapName() {
		return conquestMapName;
	}

	/**
	 * @param conquestMapName
	 *            the conquestMapName to set
	 */
	public void setConquestMapName(String conquestMapName) {
		this.conquestMapName = conquestMapName;
	}

	/**
	 * @return the continentsList
	 */
	public ArrayList<ContinentModel> getContinentsList() {
		return continentsList;
	}

	/**
	 * @param continentsList
	 *            the continentsList to set
	 */
	public void setContinentsList(ArrayList<ContinentModel> continentsList) {
		this.continentsList = continentsList;
	}

	/**
	 * Method to search the continent according to the continent's name.
	 * 
	 * @param continentName
	 *            continent's name
	 * @return continent that found or null if not exits
	 */
	public ContinentModel searchContinent(String continentName) {
		continentName = continentName.toLowerCase();
		for (ContinentModel loopContinent : getContinentsList()) {
			if (loopContinent.getContinentName().equals(continentName)) {
				return loopContinent;
			}
		}
		return null;
	}

	/**
	 * Method to add a new continent.
	 * 
	 * @param continentName
	 *            name of the new continent
	 */
	public String addContinent(String continentName) {
		if (searchContinent(continentName) != null) {
			return "Continent <" + continentName + "> already exists";
		}
		if (continentName != null && !continentName.trim().isEmpty()) {
			ContinentModel newContinent = new ContinentModel(continentName);
			getContinentsList().add(newContinent);
		}
		return "";

	}
	
	/**
	 * Method to find the country according to the country's name.
	 * @param countryName country's name
	 * @return country that found or null if not exits
	 */
	public CountryModel searchCountry(String countryName) {
		CountryModel country = null;
		for (ContinentModel loopContinent : getContinentsList()) {
			country = loopContinent.findCountry(countryName);
			if (country!=null) return country; 
		}
		return null;
	}
	
	
	

	/**
	 * Method to add a new country to an existing continent.
	 * 
	 * @param countryName
	 *            name of the new country
	 * @param continentName
	 *            name of the existing continent that the new country adding in
	 * @return Error message
	 */
	public String addCountry(String countryName, String continentName) {
		ContinentModel targetContinent = searchContinent(continentName);
		if (targetContinent == null) {
			return "Continent <" + continentName + "> does not exists";
		}

		if (searchCountry(countryName) != null) {
			return "Country <" + countryName + "> already exists";
		}

		CountryModel newCountry = new CountryModel(countryName, targetContinent);
		targetContinent.addCountry(newCountry);

		return "";
	}

}
