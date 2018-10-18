package com.conquest.mapeditor.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * The Class MapHierarchyModel.
 *
 * @author Nancy Goyal
 * @version 1.0.0
 */
public class MapHierarchyModel {

	/** The conquest map name. */
	private String conquestMapName = "Default";

	/** The total countries. */
	public int totalCountries;

	/** The continents list. */
	private ArrayList<ContinentModel> continentsList;

	/** The country list. */
	private ArrayList<CountryModel> countryList;

	/** The val error flag. */
	private boolean valErrorFlag = false;

	/** The error msg. */
	private String errorMsg = "Map is invalid";

	/**
	 * MapHierarchyModel Constructor Instantiates a new map hierarchy model.
	 */
	public MapHierarchyModel() {
		this.continentsList = new ArrayList<>();
		this.countryList = new ArrayList<>();
	}

	/**
	 * MapHierarchyModel Parametrized Constructor Instantiates a new map hierarchy
	 * model.
	 * 
	 * @param conquestMapName the conquest map name
	 * @param totalCountries  the total countries
	 */
	public MapHierarchyModel(String conquestMapName, int totalCountries) {
		this.conquestMapName = conquestMapName;
		this.totalCountries = totalCountries;
		this.continentsList = new ArrayList<ContinentModel>();

	}

	/**
	 * Gets the conquest map name. Getter function to get the map name
	 * 
	 * @return the conquestMapName
	 */
	public String getConquestMapName() {
		return conquestMapName;
	}

	/**
	 * Sets the conquest map name. Setter function to set the map name
	 * 
	 * @param conquestMapName the conquestMapName to set
	 */
	public void setConquestMapName(String conquestMapName) {
		this.conquestMapName = conquestMapName;
	}

	/**
	 * Gets the continents list.
	 * 
	 * @return the continentsList
	 */
	public ArrayList<ContinentModel> getContinentsList() {
		return continentsList;
	}

	/**
	 * Sets the continents list.
	 * 
	 * @param continentsList the continentsList to set
	 */
	public void setContinentsList(ArrayList<ContinentModel> continentsList) {
		this.continentsList = continentsList;
	}

	/**
	 * Gets the total countries.
	 * 
	 * @return the totalCountries
	 */
	public int getTotalCountries() {
		return totalCountries;
	}

	/**
	 * Sets the total countries.
	 * 
	 * @param totalCountries the totalCountries to set
	 */
	public void setTotalCountries(int totalCountries) {
		this.totalCountries = totalCountries;

	}

	/**
	 * Gets the country list.
	 * 
	 * @return the countryList
	 */
	public ArrayList<CountryModel> getCountryList() {
		return countryList;
	}

	/**
	 * Sets the country list.
	 * 
	 * @param countryModels the countryList to set
	 */
	public void setCountryList(ArrayList<CountryModel> countryModels) {
		this.countryList = countryModels;
	}

	/**
	 * Checks if is value error flag.
	 * 
	 * @return the valErrorFlag
	 */
	public boolean isValErrorFlag() {
		return valErrorFlag;
	}

	/**
	 * Sets the value error flag.
	 * 
	 * @param valErrorFlag the valErrorFlag to set
	 */
	public void setValErrorFlag(boolean valErrorFlag) {
		this.valErrorFlag = valErrorFlag;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Method to search the continent according to the continent's name.
	 * 
	 * @param continentName continent's name
	 * @return continent that found or null if not exits
	 */
	public ContinentModel searchContinent(String continentName) {
		for (ContinentModel loopContinent : getContinentsList()) {
			if (loopContinent.getContinentName().equalsIgnoreCase(continentName)) {
				return loopContinent;
			}
		}
		return null;
	}

	/**
	 * Method to add a new continent.
	 * 
	 * @param continentName name of the new continent
	 * @return the string
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
	 * 
	 * @param countryName country's name
	 * @return country that found or null if not exits
	 */
	public CountryModel searchCountry(String countryName) {
		CountryModel country = null;
		for (ContinentModel loopContinent : getContinentsList()) {
			country = loopContinent.searchCountry(countryName);
			if (country != null)
				return country;
		}
		return null;
	}

	/**
	 * Method to delete a continent.
	 * 
	 * @param continentName name of the continent want to delete
	 * @return succeed or failed error message
	 */
	public String deleteContinent(String continentName) {
		ContinentModel deleteContinent = searchContinent(continentName);
		if (deleteContinent != null) {
			if (deleteContinent.getCountriesList().size() > 0) {
				return "Continent '" + continentName + "' is not empty, Please delete or move all the countries in it.";
			}
			getContinentsList().remove(deleteContinent);
			deleteContinent = null;
		}
		return "";
	}
	
	/**
	 * Method to rename a continent.
	 * 
	 * @param continentName
	 *            name of the continent want to rename
	 * @param newContinentName
	 *            new name of the continent
	 * @return succeed or failed error message
	 */
	public String renameContinent(String continentName, String newContinentName) {
		ContinentModel continent = searchContinent(continentName);
		if(continent == null){
			return "Continent  '"+continentName+"'  you want to change does not exists";
		}
		if (searchContinent(newContinentName)!=null){
			return  "Continent '"+newContinentName+"' already exits";
		}
		else
		{
			continent.setContinentName(newContinentName);
		}
		return "";
	}
	
	/**
	 * Method to rename a continent.
	 * 
	 * @param continentName
	 *            name of the continent want to rename
	 * @param newContinentName
	 *            new name of the continent
	 * @return succeed or failed error message
	 */
	public String renameCountry(String countryName, String newCountrytName) {
		CountryModel country = searchCountry(countryName);
		if(country == null){
			return "Country  '"+countryName+"'  you want to change does not exists";
		}
		if (searchCountry(newCountrytName)!=null){
			return  "Country '"+newCountrytName+"' already exits";
		}
		else
		{
			country.setCountryName(newCountrytName);
		}
		return "";
	}

	/**
	 * Method to delete a country.
	 * 
	 * @param countryName name of the country want to delete
	 * @return succeed or failed error message
	 */
	public String deleteCountry(String countryName) {
		CountryModel deleteCountry = searchCountry(countryName);
		if (deleteCountry != null) {
			deleteCountry.getBelongsTo().deleteCountry(deleteCountry);
			getCountryList().remove(deleteCountry);
			totalCountries--;
			deleteCountry = null;
		} else {
			return "Can't find country with this name";
		}
		return "";
	}

	/**
	 * Method to add a new country to an existing continent.
	 * 
	 * @param countryName   name of the new country
	 * @param continentName name of the existing continent that the new country
	 *                      adding in
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

		totalCountries++;
		CountryModel newCountry = new CountryModel(countryName, targetContinent);
		targetContinent.addCountry(newCountry);
		countryList.add(newCountry);

		return "";
	}

	/**
	 * Method to add a new country to an existing continent with neighbours.
	 * 
	 * @param countryName      name of the new country
	 * @param continentName    name of the existing continent that the new country
	 *                         adding in
	 * @param listOfNeighbours list of neighboring countries.
	 * @return Error message
	 */
	public String addCountry(String countryName, String continentName, ArrayList<String> listOfNeighbours) {
		ContinentModel targetContinent = searchContinent(continentName);
		if (targetContinent == null) {
			return "Continent <" + continentName + "> does not exists";
		}
		if (searchCountry(countryName) != null) {
			return "Country <" + countryName + "> already exists";
		}

		totalCountries++;
		CountryModel newCountry = new CountryModel(countryName, targetContinent, listOfNeighbours);
		targetContinent.addCountry(newCountry);
		countryList.add(newCountry);

		return "";
	}
}
