package com.conquest.mapeditor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The Class ContinentModel.
 *
 * @author Nancy Goyal
 */
public class ContinentModel extends Observable implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4L;

	/** The continent name. */
	private String continentName;

	/** The countries list. */
	private ArrayList<CountryModel> countriesList;

	/** The control value. */
	private int controlValue;

	/**
	 * Constructor for Continent class.
	 * 
	 * @param continentName name of the new continent
	 */
	public ContinentModel(String continentName) {
		this.setContinentName(continentName);
		this.countriesList = new ArrayList<CountryModel>();
		updateChanges();
	}

	/**
	 * getContinentName method Gets the continent name. Getter function for
	 * continent name
	 * 
	 * @return the continentName
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * setContinentName Method Sets the continent name. Setter function for
	 * continent name
	 * 
	 * @param continentName the continentName to set
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
		updateChanges();
	}

	/**
	 * getCountriesList Method Gets the countries list. Getter function for country
	 * list
	 * 
	 * @return the countriesList
	 */
	public ArrayList<CountryModel> getCountriesList() {
		return countriesList;
	}

	/**
	 * setCountriesList method Sets the countries list. Setter function for country
	 * list
	 * 
	 * @param countriesList the countriesList to set
	 */
	public void setCountriesList(ArrayList<CountryModel> countriesList) {
		this.countriesList = countriesList;
		updateChanges();
	}

	/**
	 * addCountry Method Method to add a country to countryList.
	 * 
	 * @param country new country object
	 */
	public void addCountry(CountryModel country) {
		countriesList.add(country);
		updateChanges();
	}

	/**
	 * Method to delete a country from countryList.
	 * 
	 * @param country country object to delete
	 */
	public void deleteCountry(CountryModel country) {
		countriesList.remove(country);
		updateChanges();
	}

	/**
	 * searchCountry Method Function to search a country in this continent.
	 * 
	 * @param countryName the name of the country
	 * @return the country object found in this continent or null if can't find
	 */
	public CountryModel searchCountry(String countryName) {
		countryName = countryName.toLowerCase();
		for (CountryModel loopCountry : countriesList) {
			if (loopCountry.getCountryName().equalsIgnoreCase(countryName)) {
				return loopCountry;
			}
		}
		return null;
	}

	/**
	 * Searching country.
	 *
	 * @param countryName the country name
	 * @return true, if successful
	 */
	public boolean searchingCountry(String countryName) {
		countryName = countryName.toLowerCase();
		for (CountryModel loopCountry : countriesList) {
			if (loopCountry.getCountryName().equalsIgnoreCase(countryName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * setControlValue Method Sets the control value.
	 * 
	 * @param controlValue the controlValue to set
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
		updateChanges();
	}

	/**
	 * getControlValue Method Gets the control value.
	 * 
	 * @return the controlValue
	 */
	public int getControlValue() {
		return controlValue;
	}

	/**
	 * Update changes.
	 */
	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
