/**
 * 
 */
package com.conquest.mapeditor.model;

import java.util.ArrayList;
import java.util.List;


public class MapModel {

	private List<ContinentModel> continentModels;
	private List<CountryModel> countryModels;

	public MapModel() {
		this.continentModels = new ArrayList<>();
		this.countryModels = new ArrayList<>();
	}

	/**
	 * @return the continentModels
	 */
	public List<ContinentModel> getContinentModels() {
		return continentModels;
	}

	/**
	 * @param continentModels the continentModels to set
	 */
	public void setContinentModels(List<ContinentModel> continentModels) {
		this.continentModels = continentModels;
	}

	/**
	 * @return the countryModels
	 */
	public List<CountryModel> getCountryModels() {
		return countryModels;
	}

	/**
	 * @param countryModels the countryModels to set
	 */
	public void setCountryModels(List<CountryModel> countryModels) {
		this.countryModels = countryModels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MapModel [continentModels=" + continentModels + ", countryModels=" + countryModels + "]";
	}

}
