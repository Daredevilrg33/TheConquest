package com.conquest.mapeditor.model;

import java.util.ArrayList;

/**
 * @author Nancy Goyal
 *
 */
public class MapHierarchyModel {


	private String conquestMapName;
	private ArrayList<ContinentModel> continentsList;
	
	public MapHierarchyModel()
	{
		this.continentsList = new ArrayList<ContinentModel>();
	}
	/**
	 * @return the conquestMapName
	 */
	public String getConquestMapName() {
		return conquestMapName;
	}
	/**
	 * @param conquestMapName the conquestMapName to set
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
	 * @param continentsList the continentsList to set
	 */
	public void setContinentsList(ArrayList<ContinentModel> continentsList) {
		this.continentsList = continentsList;
	}
	
	
	/**
     * Method to add a new continent.
     * @param continentName name of the new continent
     */
	public void addContinent(String continentName){
    	if (continentName!=null && !continentName.trim().isEmpty())
    	{
    		ContinentModel newContinent = new ContinentModel(continentName);
    		getContinentsList().add(newContinent);
    		for (ContinentModel m :continentsList)
    		System.out.println("\n\n "+ m.getContinentName());
    	}
    	
    }
	
	

}
