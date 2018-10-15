/**
 * 
 */
package com.conquest.mapeditor.model;

import java.util.ArrayList;

/**
 * @author Nancy Goyal
 *
 */
public class CountryModel {

	private String countryName;
	private ContinentModel belongsTo;
	private int noOfArmiesCountry;
	private ArrayList<CountryModel> listOfNeighbours;

	public CountryModel() {
		this.listOfNeighbours = new ArrayList<>();
	}

	public CountryModel(String countryName) {
		this.countryName = countryName;
		this.listOfNeighbours = new ArrayList<>();
	}

	/**
	 * 
	 * Constructor of Country class.
	 * 
	 * @param countryName name of the new country
	 * @param continent   continent object which contains this country
	 */
	public CountryModel(String countryName, ContinentModel continent) {
		this.setCountryName(countryName);
		this.setBelongsTo(continent);
		this.listOfNeighbours = new ArrayList<>();
	}

	public CountryModel(String countryName, ContinentModel continentModel, ArrayList<CountryModel> listOfNeighbours) {
		this.countryName = countryName;
		this.belongsTo = continentModel;
		this.listOfNeighbours = listOfNeighbours;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the belongsTo
	 */
	public ContinentModel getBelongsTo() {
		return belongsTo;
	}

	/**
	 * @param belongsTo the belongsTo to set
	 */
	public void setBelongsTo(ContinentModel belongsTo) {
		this.belongsTo = belongsTo;
	}

	public boolean addNeighbour(CountryModel countryModel) {
		return this.listOfNeighbours.add(countryModel);
	}

	/**
	 * @return the listOfNeighbours
	 */
	public ArrayList<CountryModel> getListOfNeighbours() {
		return listOfNeighbours;
	}

	/**
	 * @return the noOfArmiesCountry
	 */
	public int getNoOfArmiesCountry() {
		return noOfArmiesCountry;
	}
	
	/**
	 * @param noOfArmiesCountry the noOfArmiesCountry to set
	 */
	public void setNoOfArmiesCountry(int noOfArmiesCountry) {
		this.noOfArmiesCountry = noOfArmiesCountry;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//		return "CountryModel [countryName=" + countryName + ", belongsTo=" + belongsTo + ", listOfNeighbours="
//				+ listOfNeighbours + "]";
//	}

}
