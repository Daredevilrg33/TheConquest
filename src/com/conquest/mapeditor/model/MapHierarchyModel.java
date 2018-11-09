package com.conquest.mapeditor.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * The Class MapHierarchyModel.
 *
 * @author Nancy Goyal
 * @version 1.0.0
 */
public class MapHierarchyModel extends Observable {

	/** The conquest map name. */
	private String conquestMapName = "Default";

	/** The total countries. */
	public int totalCountries;

	/** The continents list. */
	private ArrayList<ContinentModel> continentsList;

	/** The country list. */
	private ArrayList<CountryModel> countryList;

	/** The value error flag. */
	private boolean valErrorFlag = false;

	/** The error message. */

	public String errorMsg = "Map is invalid";

	/**
	 * MapHierarchyModel Constructor Instantiates a new map hierarchy model.
	 */
	public MapHierarchyModel() {
		this.continentsList = new ArrayList<>();
		this.countryList = new ArrayList<>();
	}

	/**
	 * MapHierarchyModel Parameterized Constructor Instantiates a new map hierarchy
	 * model.
	 * 
	 * @param conquestMapName the conquest map name
	 * @param totalCountries  the total countries
	 */
	public MapHierarchyModel(String conquestMapName, int totalCountries) {
		this.conquestMapName = conquestMapName;
		this.totalCountries = totalCountries;
		this.continentsList = new ArrayList<ContinentModel>();
		this.countryList = new ArrayList<>();
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
	 * @param continentName    name of the continent want to rename
	 * @param newContinentName new name of the continent
	 * @return succeed or failed error message
	 */
	public String renameContinent(String continentName, String newContinentName) {
		ContinentModel continent = searchContinent(continentName);
		if (continent == null) {
			return "Continent  '" + continentName + "'  you want to change does not exists";
		}
		if (searchContinent(newContinentName) != null) {
			return "Continent '" + newContinentName + "' already exits";
		} else {
			continent.setContinentName(newContinentName);
		}
		return "";
	}

	/**
	 * Method to rename a continent.
	 * 
	 * @param countryName     name of the continent want to rename
	 * @param newCountrytName new name of the continent
	 * @return succeed or failed error message
	 */
	public String renameCountry(String countryName, String newCountrytName) {
		CountryModel country = searchCountry(countryName);
		if (country == null) {
			return "Country  '" + countryName + "'  you want to change does not exists";
		}
		if (searchCountry(newCountrytName) != null) {
			return "Country '" + newCountrytName + "' already exits";
		} else {
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
			ContinentModel continentModel = deleteCountry.getBelongsTo();
			continentModel.deleteCountry(deleteCountry);
			getCountryList().remove(deleteCountry);
			for (ContinentModel loopContinent : getContinentsList()) {
				if (loopContinent.getContinentName().equalsIgnoreCase(continentModel.getContinentName())) {
					loopContinent.getCountriesList().remove(deleteCountry);
				}
			}

			totalCountries--;
			deleteCountry = null;
		} else {
			return "Can't find country with this name";
		}
		return "";
	}

	/**
	 * Method to move a country.
	 *
	 * @param countryName   name of the country you want to move
	 * @param continentName name of the continent to which you want to move
	 * @return succeed or failed error message
	 */
	public String moveCountry(String countryName, String continentName) {
		ContinentModel toContinent = searchContinent(continentName);
		CountryModel moveCountry = searchCountry(countryName);
		if (toContinent == null)
			return "Continent  '" + continentName + "'  you want to move does not exists";
		else if (continentName.equalsIgnoreCase(moveCountry.getBelongsTo().getContinentName()))
			return "Continent name cannot be same as existing one";
		else {
			moveCountry.getBelongsTo().getCountriesList().remove(moveCountry);
			toContinent.getCountriesList().add(moveCountry);
			moveCountry.setBelongsTo(toContinent);
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

	/**
	 * Update changes.
	 */
	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
