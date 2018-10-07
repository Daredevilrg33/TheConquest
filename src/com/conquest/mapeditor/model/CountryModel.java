/**
 * 
 */
package com.conquest.mapeditor.model;

/**
 * @author Nancy Goyal
 *
 */
public class CountryModel {
	
	private String countryName;
	private ContinentModel belongsTo;
	
	/**
	 * Constructor of Country class.
	 * @param countryName name of the new country
	 * @param continent	continent object which contains this country
	 */
	public CountryModel(String countryName, ContinentModel continent){
		this.setCountryName(countryName);
		this.setBelongsTo(continent);
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


	

}
